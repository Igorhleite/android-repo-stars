package com.ileitelabs.trends.data.mapper

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.ileitelabs.trends.data.datasource.dto.RepositoryDetailsResponseDto
import com.ileitelabs.trends.data.mapper.RepoDetailMapper
import com.ileitelabs.trends.data.mapper.RepoDetailMapperImpl
import com.ileitelabs.trends.domain.model.RepositoryDetail
import com.ileitelabs.trends.domain.model.RepositoryDetailOwner
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class RepoDetailMapperImplTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var subject: RepoDetailMapper

    @Before
    fun setup() {
        subject = RepoDetailMapperImpl()
    }

    @Test
    fun `fromDtoToModel Should convert Dto to Model`() = runTest {
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

        val expectedResult = RepositoryDetail(
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

        //Act
        val result = subject.fromDtoToModel(dummyDto)

        //Assert
        Assert.assertEquals(expectedResult, result)
    }
}