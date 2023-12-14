package com.ogdev.mysugrlogbook.logbook.data.di

import com.ogdev.mysugrlogbook.logbook.data.dao.BloodGlucoseDao
import com.ogdev.mysugrlogbook.logbook.data.repository.BloodGlucoseRepositoryImpl
import com.ogdev.mysugrlogbook.logbook.domain.repository.BloodGlucoseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideBloodGlucoseRepository(
        bloodGlucoseDao: BloodGlucoseDao
    ): BloodGlucoseRepository = BloodGlucoseRepositoryImpl(bloodGlucoseDao)
}