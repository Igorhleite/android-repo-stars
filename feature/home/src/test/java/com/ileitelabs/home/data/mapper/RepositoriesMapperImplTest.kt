package com.ileitelabs.home.data.mapper

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.ileitelabs.home.data.datasource.local.entity.RepositoryEntity
import com.ileitelabs.home.data.datasource.remote.dto.OwnerResponseDto
import com.ileitelabs.home.data.datasource.remote.dto.RepoTrendsResponseDto
import com.ileitelabs.home.data.datasource.remote.dto.RepositoryResponseDto
import com.ileitelabs.home.domain.model.Owner
import com.ileitelabs.home.domain.model.RepoTrends
import com.ileitelabs.home.domain.model.Repository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class RepositoriesMapperImplTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var subject: RepositoriesMapper

    @Before
    fun setup() {
        subject = RepositoriesMapperImpl()
    }

    @Test
    fun `fromDtoToModel Should convert Dto to Model`() = runTest {
        //Arrange
        val dummyDto = RepoTrendsResponseDto(createDummiesReposDto())
        val expectedResult = RepoTrends(createDummiesReposModel())

        //Act
        val result = subject.fromDtoToModel(dummyDto)

        //Assert
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `fromModelToEntity Should convert Model to Entity`() = runTest {
        //Arrange
        val dummyModel = createDummiesReposModel().first()

        val expectedResult = createDummiesReposEntity().first()

        //Act
        val result = subject.fromModelToEntity(dummyModel)

        //Assert
        Assert.assertEquals(
            expectedResult.toJsonForCheckFieldsAndIgnoreMemoryRef(),
            result.toJsonForCheckFieldsAndIgnoreMemoryRef()
        )
    }

    @Test
    fun `fromEntityToModel Should convert Entity to Model`() = runTest {
        //Arrange
        val dummyModel = createDummiesReposEntity().first()

        val expectedResult = createDummiesReposModel().first()

        //Act
        val result = subject.fromEntityToModel(dummyModel)

        //Assert
        Assert.assertEquals(
            expectedResult.toJsonForCheckFieldsAndIgnoreMemoryRef(),
            result.toJsonForCheckFieldsAndIgnoreMemoryRef()
        )
    }

    private fun createDummiesReposDto(): MutableList<RepositoryResponseDto> {
        val dummyList = mutableListOf<RepositoryResponseDto>()
        for (i in 0..10) {
            dummyList.add(
                RepositoryResponseDto(
                    id = i,
                    name = "FakeRepo$i",
                    repoUrl = "https://github.com/fakeuser/FakeRepo",
                    stars = i,
                    forks = i,
                    owner = OwnerResponseDto(
                        name = "fakeuser",
                        img = "https://avatars.githubusercontent.com/u/123456?v=4",
                    )
                )
            )
        }
        return dummyList
    }

    private fun createDummiesReposModel(): MutableList<Repository> {
        val dummyList = mutableListOf<Repository>()
        for (i in 0..10) {
            dummyList.add(
                Repository(
                    id = i,
                    name = "FakeRepo$i",
                    repoUrl = "https://github.com/fakeuser/FakeRepo",
                    stars = i,
                    forks = i,
                    owner = Owner(
                        name = "fakeuser",
                        img = "https://avatars.githubusercontent.com/u/123456?v=4",
                    )
                )
            )
        }
        return dummyList
    }

    private fun createDummiesReposEntity(): MutableList<RepositoryEntity> {
        val dummyList = mutableListOf<RepositoryEntity>()
        for (i in 0..10) {
            dummyList.add(
                RepositoryEntity(
                    id = i.toString(),
                    name = "FakeRepo$i",
                    repoUrl = "https://github.com/fakeuser/FakeRepo",
                    stars = i.toString(),
                    forks = i.toString(),
                    ownerName = "fakeuser",
                    ownerPhotoUrl = "https://avatars.githubusercontent.com/u/123456?v=4"
                )
            )
        }
        return dummyList
    }

    private fun Any.toJsonForCheckFieldsAndIgnoreMemoryRef(): String = Gson().toJson(this)
}