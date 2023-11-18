package com.ileitelabs.home.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.ileitelabs.home.data.datasource.local.RepositoriesDatabase
import com.ileitelabs.home.data.datasource.local.entity.RemoteKeyEntity
import com.ileitelabs.home.data.datasource.local.entity.RepositoryEntity
import com.ileitelabs.home.data.datasource.remote.RemoteRepoTrendsDataSource
import com.ileitelabs.home.data.mapper.RepositoriesMapper
import com.ileitelabs.home.domain.model.Repository
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class RepositoriesRemoteMediator @Inject constructor(
    private val remoteDataSource: RemoteRepoTrendsDataSource,
    private val db: RepositoriesDatabase,
    private val mapper: RepositoriesMapper
) : RemoteMediator<Int, RepositoryEntity>() {

    private val repositoriesDao = db.repositoriesDao()
    private val remoteKeyDao = db.remoteKeyDao()

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RepositoryEntity>,
    ): MediatorResult {
        val currentPage = when (val pageKeyData = getKeyPageData(loadType, state)) {
            is MediatorResult.Success -> return pageKeyData
            else -> pageKeyData as Int
        }

        try {
            val repositories = mutableListOf<Repository>()

            remoteDataSource.fetchRepoTrends(currentPage).let { it ->
                mapper.fromDtoToModel(it).repositories?.forEach {
                    repositories.add(it)
                }
            }

            val isEndOfList = repositories.isEmpty()
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    repositoriesDao.clearAll()
                    remoteKeyDao.clearAll()
                }

                val prevKey = if (currentPage == 1) null else currentPage - 1
                val nextKey = if (isEndOfList) null else currentPage + 1

                val keys = repositories.map {
                    RemoteKeyEntity(
                        it.id.toString(),
                        prevKey = prevKey,
                        nextKey = nextKey
                    )
                }

                repositoriesDao.insertAll(
                    repositories = repositories.map {
                        mapper.fromModelToEntity(it)
                    }
                )

                remoteKeyDao.insertAll(keys)
            }
            return MediatorResult.Success(endOfPaginationReached = isEndOfList)
        } catch (exception: Exception) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getKeyPageData(
        loadType: LoadType,
        state: PagingState<Int, RepositoryEntity>,
    ): Any {
        return when (loadType) {

            LoadType.REFRESH -> {
                val remoteKeys = getClosestRemoteKey(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }

            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                val nextKey = remoteKeys?.nextKey
                return nextKey ?: MediatorResult.Success(endOfPaginationReached = false)
            }

            LoadType.PREPEND -> {
                val remoteKeys = getFirstRemoteKey(state)
                return remoteKeys?.prevKey ?: return MediatorResult.Success(
                    endOfPaginationReached = false
                )
            }
        }
    }

    private suspend fun getClosestRemoteKey(state: PagingState<Int, RepositoryEntity>): RemoteKeyEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { repoId ->
                remoteKeyDao.getRemoteKey(repoId)
            }
        }
    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, RepositoryEntity>): RemoteKeyEntity? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { user -> remoteKeyDao.getRemoteKey(user.id) }
    }

    private suspend fun getFirstRemoteKey(state: PagingState<Int, RepositoryEntity>): RemoteKeyEntity? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { user -> remoteKeyDao.getRemoteKey(user.id) }
    }
}