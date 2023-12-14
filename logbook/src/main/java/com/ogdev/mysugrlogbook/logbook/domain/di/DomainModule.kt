package com.ogdev.mysugrlogbook.logbook.domain.di

import com.ogdev.mysugrlogbook.logbook.domain.usecase.GetAverageBloodGlucose
import com.ogdev.mysugrlogbook.logbook.domain.usecase.GetAverageBloodGlucoseImpl
import com.ogdev.mysugrlogbook.logbook.domain.usecase.GetBloodGlucoseList
import com.ogdev.mysugrlogbook.logbook.domain.usecase.GetBloodGlucoseListImpl
import com.ogdev.mysugrlogbook.logbook.domain.usecase.SaveBloodGlucose
import com.ogdev.mysugrlogbook.logbook.domain.usecase.SaveBloodGlucoseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface DomainModule {

    @Binds
    fun bindGetAverageBloodGlucose(impl: GetAverageBloodGlucoseImpl): GetAverageBloodGlucose

    @Binds
    fun bindGetBloodGlucoseList(impl: GetBloodGlucoseListImpl): GetBloodGlucoseList

    @Binds
    fun bindSaveBloodGlucose(impl: SaveBloodGlucoseImpl): SaveBloodGlucose
}