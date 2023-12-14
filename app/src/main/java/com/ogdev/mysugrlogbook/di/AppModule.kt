package com.ogdev.mysugrlogbook.di

import android.content.Context
import com.ogdev.mysugrlogbook.domain.AppDatabase
import com.ogdev.mysugrlogbook.logbook.data.dao.BloodGlucoseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = AppDatabase.getInstance(context)

    @Provides
    @Singleton
    fun provideBloodGlucoseDao(
        appDatabase: AppDatabase
    ): BloodGlucoseDao = appDatabase.bloodGlucoseDao()
}