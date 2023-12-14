package com.ogdev.mysugrlogbook.logbook.domain.mapper

import com.ogdev.mysugrlogbook.logbook.domain.model.BloodGlucose
import com.ogdev.mysugrlogbook.logbook.domain.model.UnitType
import javax.inject.Inject

class ValueMapper @Inject constructor() {

    fun toUnit(bloodGlucose: BloodGlucose, desiredUnit: UnitType): BloodGlucose =
        if (bloodGlucose.unit == desiredUnit) {
            bloodGlucose
        } else {
            val value = bloodGlucose.convertTo(desiredUnit)
            BloodGlucose(value, desiredUnit)
        }
}