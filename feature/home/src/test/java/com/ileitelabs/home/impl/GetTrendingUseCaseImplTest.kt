package com.ileitelabs.home.impl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.ileitelabs.home.domain.model.Repository
import com.ileitelabs.home.domain.repository.RepoTrendsRepository
import com.ileitelabs.home.domain.usecase.GetTrendingUseCase
import com.ileitelabs.home.domain.usecase.impl.GetTrendingUseCaseImpl
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
internal class GetTrendingUseCaseImplTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    @Mock
    private lateinit var repositoryMock: RepoTrendsRepository

    private lateinit var subject: GetTrendingUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        subject = GetTrendingUseCaseImpl(repositoryMock)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `useCase Should emit data When repository result`() = runTest {
        //Arrange
        val mockResponse = PagingData.from(listOf<Repository>())

        Mockito.`when`(
            repositoryMock.fetchCachedRepositories()
        ).thenReturn(
            flow {
                emit(
                    mockResponse
                )
            }
        )

        //Act
        val result = subject.invoke()

        //Assert
        Assert.assertNotNull(result)
    }
}
