package com.ileitelabs.core.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ileitelabs.core.data.local.database.dao.RemoteKeyDao
import com.ileitelabs.core.data.local.database.dao.RepositoriesDao
import com.ileitelabs.core.data.local.database.entity.GitRepositoryEntity
import com.ileitelabs.core.data.local.database.entity.RemoteKeyEntity

@Database(
    entities = [GitRepositoryEntity::class, RemoteKeyEntity::class],
    version = 1
)
abstract class RepositoriesDatabase : RoomDatabase() {

    abstract fun repositoriesDao(): RepositoriesDao

    abstract fun remoteKeyDao(): RemoteKeyDao

}