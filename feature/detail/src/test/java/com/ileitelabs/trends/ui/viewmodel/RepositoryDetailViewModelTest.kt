package com.ileitelabs.trends.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ileitelabs.trends.domain.model.RepositoryDetail
import com.ileitelabs.trends.domain.model.RepositoryDetailOwner
import com.ileitelabs.trends.domain.usecase.GetRepositoryDetail
import com.ileitelabs.trends.ui.viewmodel.RepositoryDetailViewState.RepositoryDetailState
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
internal class RepositoryDetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    @Mock
    private lateinit var observerState: Observer<RepositoryDetailViewState>

    @Mock
    private lateinit var observerAction: Observer<RepositoryDetailViewAction?>

    @Mock
    private lateinit var useCaseMock: GetRepositoryDetail

    private lateinit var subject: RepositoryDetailViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        subject = RepositoryDetailViewModel(testDispatcher, useCaseMock)
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
    fun `obtainRepositoryDetail Should set SuccessViewState When useCase provide valid result `() =
        runTest {
            //Arrange
            val mockResult = RepositoryDetail(
                name = "mock",
                fullName = "mock",
                description = "mock",
                stars = "1",
                htmlUrl = "mock",
                owner = RepositoryDetailOwner("mock", "mock", "mock")
            )

            Mockito.`when`(useCaseMock.invoke(any(), any())).thenReturn(
                flow {
                    emit(mockResult)
                }
            )

            //Act
            subject.obtainRepositoryDetail("mock", "mock")

            //Assert
            Mockito.verify(observerState, times(3)).onChanged(any())
            Mockito.verify(observerState, times(2))
                .onChanged(RepositoryDetailViewState(state = RepositoryDetailState.LOADING))
            Mockito.verify(observerState)
                .onChanged(
                    RepositoryDetailViewState(
                        data = mockResult,
                        state = RepositoryDetailState.SUCCESS
                    )
                )
            Mockito.verifyNoMoreInteractions(observerState)
        }

    @Test
    fun `obtainRepositoryDetail Should set ErrorViewState When useCase exception `() =
        runTest {
            //Arrange
            val errorCause = "error cause"

            Mockito.`when`(useCaseMock.invoke(any(), any())).thenReturn(
                flow {
                    throw RuntimeException(errorCause)
                }
            )

            //Act
            subject.obtainRepositoryDetail("mock", "mock")

            //Assert
            Mockito.verify(observerState, times(3)).onChanged(any())
            Mockito.verify(observerState, times(2))
                .onChanged(RepositoryDetailViewState(state = RepositoryDetailState.LOADING))
            Mockito.verify(observerState)
                .onChanged(
                    RepositoryDetailViewState(
                        state = RepositoryDetailState.ERROR
                    )
                )
            Mockito.verifyNoMoreInteractions(observerState)
        }

    @Test
    fun `onTryAgainClicked Should set FetchData action`() =
        runTest {
            //Act
            subject.onTryAgainClicked()

            //Assert
            Mockito.verify(observerAction, times(1)).onChanged(any())
            Mockito.verify(observerAction).onChanged(RepositoryDetailViewAction.FetchData)
            Mockito.verify(observerAction).onChanged(null)
            Mockito.verifyNoMoreInteractions(observerAction)
        }
}