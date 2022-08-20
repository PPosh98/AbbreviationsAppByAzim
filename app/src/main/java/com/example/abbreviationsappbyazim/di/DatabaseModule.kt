package com.example.abbreviationsappbyazim.di

import android.content.Context
import androidx.room.Room
import com.example.abbreviationsappbyazim.roomdb.AbbreviationsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideRoomInstance(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AbbreviationsDatabase::class.java,
        "AbbreviationsDatabase"
    ).build()

    @Provides
    fun provideAbbreviationsDao(database: AbbreviationsDatabase) = database.abbreviationsDAO()
}