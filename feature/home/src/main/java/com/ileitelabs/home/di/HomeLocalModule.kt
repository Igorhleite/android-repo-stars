package com.ileitelabs.home.di

import android.content.Context
import androidx.room.Room
import com.ileitelabs.home.data.datasource.local.RepositoriesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeLocalModule {

    @Singleton
    @Provides
    fun provideUserDataBase(
        @ApplicationContext context: Context,
    ): RepositoriesDatabase {
        return Room.databaseBuilder(
            context, RepositoriesDatabase::class.java,
            "repositories.db"
        ).fallbackToDestructiveMigration().build()
    }
}