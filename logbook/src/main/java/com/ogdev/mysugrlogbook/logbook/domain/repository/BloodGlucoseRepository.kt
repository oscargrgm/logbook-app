package com.ogdev.mysugrlogbook.logbook.domain.repository

import com.ogdev.mysugrlogbook.logbook.data.entity.BloodGlucoseEntity
import kotlinx.coroutines.flow.Flow

interface BloodGlucoseRepository {
    suspend fun setBloodGlucose(bloodGlucose: BloodGlucoseEntity)
    fun getBloodGlucoseList(): Flow<List<BloodGlucoseEntity>>
}