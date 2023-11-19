package com.ileitelabs.trends.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ileitelabs.trends.data.datasource.RemoteRepoDetailDataSource
import com.ileitelabs.trends.data.datasource.dto.RepositoryDetailsResponseDto
import com.ileitelabs.trends.data.mapper.RepoDetailMapper
import com.ileitelabs.trends.domain.model.RepositoryDetail
import com.ileitelabs.trends.domain.model.RepositoryDetailOwner
import com.ileitelabs.trends.domain.repository.RepoDetailRepository
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
internal class RepoDetailRepositoryImplTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    @Mock
    private lateinit var dataSourceMock: RemoteRepoDetailDataSource

    @Mock
    private lateinit var mapperMock: RepoDetailMapper

    private lateinit var subject: RepoDetailRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        subject = RepoDetailRepositoryImpl(dataSourceMock, mapperMock)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchRepoDetail Should emit data When data source result`() = runTest {
        //Arrange
        val dummyDto = RepositoryDetailsResponseDto(
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
            dataSourceMock.fetchRepoDetails(any(), any())
        ).thenReturn(
            flow {
                emit(
                    dummyDto
                )
            }
        )

        Mockito.`when`(
            mapperMock.fromDtoToModel(any())
        ).thenReturn(
            dummyModel
        )

        //Act
        val result = subject.fetchRepoDetail("mock", "mock")

        //Assert
        Assert.assertEquals(dummyModel, result.first())
    }
}
