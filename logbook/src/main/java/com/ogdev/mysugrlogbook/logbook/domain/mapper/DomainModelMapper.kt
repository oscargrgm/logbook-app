package com.ogdev.mysugrlogbook.logbook.domain.mapper

import com.ogdev.mysugrlogbook.logbook.data.entity.BloodGlucoseEntity
import com.ogdev.mysugrlogbook.logbook.domain.model.BloodGlucose
import com.ogdev.mysugrlogbook.logbook.domain.model.UnitType
import javax.inject.Inject

class DomainModelMapper @Inject constructor() {

    fun toDomainModel(
        list: List<BloodGlucoseEntity>
    ): List<BloodGlucose> = with(list) {
        map { entity -> toDomainModel(entity) }
    }

    private fun toDomainModel(
        entity: BloodGlucoseEntity
    ): BloodGlucose = with(entity) {
        BloodGlucose(value, UnitType.valueOf(entity.unit))
    }
}