package com.ogdev.mysugrlogbook.logbook.domain.model.params

import com.ogdev.mysugrlogbook.logbook.domain.model.UnitType

data class SaveBloodGlucoseParams(
    val value: Float,
    val unit: UnitType
)
