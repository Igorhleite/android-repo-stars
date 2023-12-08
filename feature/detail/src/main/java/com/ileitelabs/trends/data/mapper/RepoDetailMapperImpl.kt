package com.ileitelabs.trends.data.mapper

import com.ileitelabs.trends.data.datasource.dto.RepositoryDetailsResponseDto
import com.ileitelabs.trends.domain.model.RepositoryDetail
import com.ileitelabs.trends.domain.model.RepositoryDetailOwner
import javax.inject.Inject

class RepoDetailMapperImpl @Inject constructor(): RepoDetailMapper {
    override fun fromDtoToModel(response: RepositoryDetailsResponseDto): RepositoryDetail {
        return RepositoryDetail(
            name = response.name.orEmpty(),
            fullName = response.fullName.orEmpty(),
            owner = mapOwner(response.owner),
            htmlUrl = response.htmlUrl.orEmpty(),
            description = response.description.orEmpty(),
            stars = response.stars.toString(),
            watchers = response.watchers.toString(),
            issues = response.issues.toString()
        )
    }

    private fun mapOwner(owner: RepositoryDetailsResponseDto.Owner?): RepositoryDetailOwner {
        return RepositoryDetailOwner(
            login = owner?.login.orEmpty(),
            avatarUrl = owner?.avatarUrl.orEmpty(),
            htmlUrl = owner?.htmlUrl.orEmpty()
        )
    }
}