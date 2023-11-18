package com.ileitelabs.trends.data.mapper

import com.ileitelabs.trends.data.datasource.dto.RepositoryDetailsResponseDto
import com.ileitelabs.trends.domain.model.RepositoryDetail
import com.ileitelabs.trends.domain.model.RepositoryDetailOwner
import javax.inject.Inject

class RepoDetailMapperImpl @Inject constructor(): RepoDetailMapper {
    override fun fromDtoToModel(response: RepositoryDetailsResponseDto): RepositoryDetail {
        return RepositoryDetail(
            name = response.name,
            fullName = response.fullName,
            owner = mapOwner(response.owner),
            htmlUrl = response.htmlUrl,
            description = response.description,
            stars = response.stars.toString()
        )
    }

    private fun mapOwner(owner: RepositoryDetailsResponseDto.Owner): RepositoryDetailOwner {
        return RepositoryDetailOwner(
            login = owner.login,
            avatarUrl = owner.avatarUrl,
            htmlUrl = owner.htmlUrl
        )
    }
}