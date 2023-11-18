package com.ileitelabs.trends.data.mapper

import com.ileitelabs.trends.data.datasource.dto.RepositoryDetailsResponseDto
import com.ileitelabs.trends.domain.model.RepositoryDetail

interface RepoDetailMapper {
    fun fromDtoToModel(response: RepositoryDetailsResponseDto): RepositoryDetail
}