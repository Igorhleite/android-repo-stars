package com.ileitelabs.home.data.mapper

import com.ileitelabs.home.data.datasource.local.entity.RepositoryEntity
import com.ileitelabs.home.data.datasource.remote.dto.RepoTrendsResponseDto
import com.ileitelabs.home.domain.model.RepoTrends
import com.ileitelabs.home.domain.model.Repository

interface RepositoriesMapper {
    fun fromDtoToModel(response: RepoTrendsResponseDto): RepoTrends
    fun fromModelToEntity(model: Repository): RepositoryEntity
    fun fromEntityToModel(entity: RepositoryEntity): Repository
}