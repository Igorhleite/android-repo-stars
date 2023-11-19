package com.ileitelabs.trends.data.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ileitelabs.trends.data.datasource.dto.RepositoryDetailsResponseDto
import com.ileitelabs.trends.data.service.DetailService
import com.nhaarman.mockitokotlin2.any
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
internal class RemoteRepoDetailDataSourceImplTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    @Mock
    private lateinit var serviceMock: DetailService

    private lateinit var subject: RemoteRepoDetailDataSource

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        subject = RemoteRepoDetailDataSourceImpl(serviceMock)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchRepoDetails Should emit data When service provide valid result`() = runTest {
        //Arrange
        val mockResponse = RepositoryDetailsResponseDto(
            name = "mock",
            fullName = "mock",
            description = "mock",
            htmlUrl = "mock",
            stars = 0,
            owner = RepositoryDetailsResponseDto.Owner(
                login = "mock",
                avatarUrl = "mock",
                htmlUrl = "mock"
            ),
        )

        Mockito.`when`(
            serviceMock.getRepositoryDetails(
                owner = any(),
                repo = any()
            )
        ).thenReturn(
            mockResponse
        )

        //Act
        val result = subject.fetchRepoDetails("mock", "mock")

        //Assert
        Assert.assertNotNull(result)
    }
}