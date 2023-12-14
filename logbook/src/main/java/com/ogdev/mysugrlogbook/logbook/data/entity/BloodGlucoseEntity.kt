package com.ogdev.mysugrlogbook.logbook.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "blood_glucose")
data class BloodGlucoseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val value: Float,
    val unit: String
)
