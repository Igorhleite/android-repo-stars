package com.ileitelabs.home.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ileitelabs.home.data.datasource.local.entity.RepositoryEntity
import com.ileitelabs.home.data.datasource.local.entity.RemoteKeyEntity
import com.ileitelabs.home.data.datasource.local.dao.RemoteKeyDao
import com.ileitelabs.home.data.datasource.local.dao.RepositoriesDao

@Database(
    entities = [RepositoryEntity::class, RemoteKeyEntity::class],
    version = 1
)
abstract class RepositoriesDatabase : RoomDatabase() {

    abstract fun repositoriesDao(): RepositoriesDao

    abstract fun remoteKeyDao(): RemoteKeyDao
}