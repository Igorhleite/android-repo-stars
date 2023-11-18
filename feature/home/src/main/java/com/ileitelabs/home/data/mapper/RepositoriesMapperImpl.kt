package com.ileitelabs.home.data.mapper

import com.ileitelabs.home.data.datasource.local.entity.RepositoryEntity
import com.ileitelabs.home.data.datasource.remote.dto.RepoTrendsResponseDto
import com.ileitelabs.home.data.datasource.remote.dto.RepositoryResponseDto
import com.ileitelabs.home.domain.model.Owner
import com.ileitelabs.home.domain.model.RepoTrends
import com.ileitelabs.home.domain.model.Repository
import javax.inject.Inject

class RepositoriesMapperImpl @Inject constructor() : RepositoriesMapper {
    override fun fromDtoToModel(response: RepoTrendsResponseDto): RepoTrends {
        return RepoTrends(response.repositories?.map {
            it.mapDtoToDomain()
        })
    }

    override fun fromModelToEntity(model: Repository): RepositoryEntity {
        return RepositoryEntity(
            id = model.id.toString(),
            name = model.name.orEmpty(),
            repoUrl = model.repoUrl.orEmpty(),
            stars = model.stars.toString(),
            forks = model.forks.toString(),
            ownerName = model.owner.name.orEmpty(),
            ownerPhotoUrl = model.owner.img.orEmpty()
        )
    }

    override fun fromEntityToModel(entity: RepositoryEntity): Repository {
        return Repository(
            entity.id.toInt(),
            entity.name,
            entity.repoUrl,
            entity.forks.toInt(),
            entity.stars.toInt(),
            Owner(
                entity.ownerName,
                entity.ownerPhotoUrl
            )
        )
    }

    private fun RepositoryResponseDto.mapDtoToDomain(): Repository {
        return Repository(
            id = id,
            name = name,
            repoUrl = repoUrl,
            stars = stars,
            forks = forks,
            owner = Owner(
                name = owner.name,
                img = owner.img
            )
        )
    }
}