package com.ileitelabs.core.data.local.di

import android.content.Context
import androidx.room.Room
import com.ileitelabs.core.data.local.database.RepositoriesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideUserDataBase(
        @ApplicationContext context: Context,
    ): RepositoriesDatabase {
        return Room.databaseBuilder(
            context, RepositoriesDatabase::class.java,
            ""
        ).fallbackToDestructiveMigration().build()
    }
}