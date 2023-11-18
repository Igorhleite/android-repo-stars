package com.ileitelabs.home.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repositories")
class RepositoryEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "repoUrl")
    val repoUrl: String,
    @ColumnInfo(name = "stars")
    val stars: String,
    @ColumnInfo(name = "forks")
    val forks: String,
    @ColumnInfo(name = "ownerName")
    val ownerName: String,
    @ColumnInfo(name = "ownerPhotoUrl")
    val ownerPhotoUrl: String,
)