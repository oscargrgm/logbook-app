package com.ogdev.mysugrlogbook.logbook.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ogdev.mysugrlogbook.logbook.data.entity.BloodGlucoseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BloodGlucoseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bloodGlucose: BloodGlucoseEntity)

    @Query("SELECT * FROM blood_glucose")
    fun getAll(): Flow<List<BloodGlucoseEntity>>
}