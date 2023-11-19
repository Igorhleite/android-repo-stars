package com.ileitelabs.trends.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ileitelabs.trends.domain.model.RepositoryDetail
import com.ileitelabs.trends.domain.model.RepositoryDetailOwner
import com.ileitelabs.trends.domain.repository.RepoDetailRepository
import com.ileitelabs.trends.domain.usecase.GetRepositoryDetail
import com.ileitelabs.trends.domain.usecase.GetRepositoryDetailImpl
import com.nhaarman.mockitokotlin2.any
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
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
internal class GetRepositoryDetailImplTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    @Mock
    private lateinit var repositoryMock: RepoDetailRepository

    private lateinit var subject: GetRepositoryDetail

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        subject = GetRepositoryDetailImpl(repositoryMock)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `useCase Should emit data When repository result`() = runTest {
        //Arrange
        val dummyModel = RepositoryDetail(
            name = "mock",
            fullName = "mock",
            description = "mock",
            htmlUrl = "mock",
            stars = "0",
            owner = RepositoryDetailOwner(
                login = "mock",
                avatarUrl = "mock",
                htmlUrl = "mock"
            ),
        )

        Mockito.`when`(
            repositoryMock.fetchRepoDetail(any(), any())
        ).thenReturn(
            flow {
                emit(
                    dummyModel
                )
            }
        )

        //Act
        val result = subject("mock", "mock")

        //Assert
        Assert.assertEquals(dummyModel, result.first())
    }
}
