package com.ileitelabs.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.ileitelabs.home.domain.model.Repository
import com.ileitelabs.home.domain.usecase.GetTrendingUseCase
import com.ileitelabs.home.ui.viewmodel.HomeViewAction
import com.ileitelabs.home.ui.viewmodel.HomeViewModel
import com.ileitelabs.home.ui.viewmodel.HomeViewState
import com.ileitelabs.navigation.deeplink.RepoTrendsDeepLink
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.times
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
internal class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    @Mock
    private lateinit var observerState: Observer<HomeViewState>

    @Mock
    private lateinit var observerAction: Observer<HomeViewAction?>

    @Mock
    private lateinit var useCaseMock: GetTrendingUseCase

    @Mock
    private lateinit var deepLinkMock: RepoTrendsDeepLink

    private lateinit var subject: HomeViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        subject = HomeViewModel(useCaseMock, testDispatcher, deepLinkMock)
        subject.state.observeForever(observerState)
        subject.action.observeForever(observerAction)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        subject.state.removeObserver(observerState)
        subject.action.removeObserver(observerAction)
    }

    @Test
    fun `obtainRepositories Should set SuccessViewState When useCase provide valid result `() =
        runTest {
            //Arrange
            val mockResult = PagingData.from(listOf<Repository>())

            Mockito.`when`(useCaseMock.invoke()).thenReturn(
                flow {
                    emit(mockResult)
                }
            )

            //Act
            subject.obtainRepositories()

            //Assert
            val loadingCaptor = argumentCaptor<HomeViewState>()
            Mockito.verify(observerState, times(2)).onChanged(loadingCaptor.capture())
            Assert.assertNotNull(loadingCaptor.lastValue.data)
            Mockito.verifyNoMoreInteractions(observerState)
        }

    @Test
    fun `manageAdapterLoadStates Should set emptyDataError`() =
        runTest {
            //Arrange
            val currentLoadState = LoadState.Error(RuntimeException("mock"))

            //Act
            subject.manageAdapterLoadStates(currentLoadState, true)

            //Assert
            val loadingCaptor = argumentCaptor<HomeViewState>()
            Mockito.verify(observerState, times(2)).onChanged(loadingCaptor.capture())
            Mockito.verify(observerState).onChanged(
                HomeViewState(
                    emptyDataError = true,
                    refreshDataError = false,
                    hasLoading = false
                )
            )
            Mockito.verifyNoMoreInteractions(observerState)
        }

    @Test
    fun `manageAdapterLoadStates Should set refreshDataError`() =
        runTest {
            //Arrange
            val currentLoadState = LoadState.Error(RuntimeException("mock"))

            //Act
            subject.manageAdapterLoadStates(currentLoadState, false)

            //Assert
            val loadingCaptor = argumentCaptor<HomeViewState>()
            Mockito.verify(observerState, times(2)).onChanged(loadingCaptor.capture())
            Mockito.verify(observerState).onChanged(
                HomeViewState(
                    emptyDataError = false,
                    refreshDataError = true,
                    hasLoading = false
                )
            )
            Mockito.verifyNoMoreInteractions(observerState)
        }

    @Test
    fun `manageAdapterLoadStates Should set loading`() =
        runTest {
            //Arrange
            val currentLoadState = LoadState.Loading

            //Act
            subject.manageAdapterLoadStates(currentLoadState, false)

            //Assert
            val loadingCaptor = argumentCaptor<HomeViewState>()
            Mockito.verify(observerState, times(2)).onChanged(loadingCaptor.capture())
            Mockito.verify(observerState).onChanged(
                HomeViewState(
                    emptyDataError = false,
                    refreshDataError = false,
                    hasLoading = true
                )
            )
            Mockito.verifyNoMoreInteractions(observerState)
        }

    @Test
    fun `onTryAgainClicked Should set FetchData Action`() =
        runTest {
            //Act
            subject.onTryAgainClicked()

            //Assert
            Mockito.verify(observerAction).onChanged(HomeViewAction.FetchData)
            Mockito.verify(observerAction).onChanged(null)
            Mockito.verifyNoMoreInteractions(observerAction)
        }
}