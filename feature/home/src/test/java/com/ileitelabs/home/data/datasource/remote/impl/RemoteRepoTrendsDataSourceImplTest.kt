package com.ileitelabs.home.data.datasource.remote.impl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ileitelabs.home.data.datasource.remote.RemoteRepoTrendsDataSource
import com.ileitelabs.home.data.datasource.remote.dto.RepoTrendsResponseDto
import com.ileitelabs.home.data.service.HomeService
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
internal class RemoteRepoTrendsDataSourceImplTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    @Mock
    private lateinit var serviceMock: HomeService

    private lateinit var subject: RemoteRepoTrendsDataSource

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        subject = RemoteRepoTrendsDataSourceImpl(serviceMock)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getRepositories Should emit data When service provide valid result`() = runTest {
        //Arrange
        val mockResponse = RepoTrendsResponseDto(listOf())
        val expectedResult = RepoTrendsResponseDto(listOf())

        Mockito.`when`(
            serviceMock.getRepositories(
                q = any(),
                sort = any(),
                page = any()
            )
        ).thenReturn(
            mockResponse
        )

        //Act
        val result = subject.fetchRepoTrends(page = 1)

        //Assert
        Assert.assertEquals(expectedResult, result)
    }
}