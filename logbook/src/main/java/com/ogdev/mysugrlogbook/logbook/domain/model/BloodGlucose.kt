package com.ogdev.mysugrlogbook.logbook.domain.model

private const val CONVERSION_FACTOR = 18.0182F

data class BloodGlucose(
    val value: Float,
    val unit: UnitType
) {
    fun convertTo(target: UnitType?): Float = when (target) {
        unit -> value
        UnitType.MMOL -> value / CONVERSION_FACTOR
        UnitType.MGDL -> value * CONVERSION_FACTOR
        null -> value
    }
}

enum class UnitType {
    MMOL,
    MGDL
}
