package com.ileitelabs.home.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.ileitelabs.home.data.datasource.local.RepositoriesDatabase
import com.ileitelabs.home.data.datasource.remote.RemoteRepoTrendsDataSource
import com.ileitelabs.home.data.mapper.RepositoriesMapper
import com.ileitelabs.home.data.paging.RepositoriesRemoteMediator
import com.ileitelabs.home.domain.model.Repository
import com.ileitelabs.home.domain.repository.RepoTrendsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class RepoTrendsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteRepoTrendsDataSource,
    private val db: RepositoriesDatabase,
    private val mapper: RepositoriesMapper
) : RepoTrendsRepository {

    override suspend fun fetchCachedRepositories(): Flow<PagingData<Repository>> {
        return Pager(
            config = PagingConfig(
                pageSize = 15,
                enablePlaceholders = false,
            ),
            remoteMediator = RepositoriesRemoteMediator(
                remoteDataSource = remoteDataSource,
                db = db,
                mapper = mapper
            ),
            pagingSourceFactory = { db.repositoriesDao().getAll() }
        ).flow.map { pagingData ->
            pagingData.map {
                mapper.fromEntityToModel(it)
            }
        }
    }
}