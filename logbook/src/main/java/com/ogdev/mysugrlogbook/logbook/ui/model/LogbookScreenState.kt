package com.ogdev.mysugrlogbook.logbook.ui.model

import com.ogdev.mysugrlogbook.logbook.domain.model.BloodGlucose
import com.ogdev.mysugrlogbook.logbook.domain.model.UnitType

data class LogbookScreenState(
    val inputTextValue: String? = null,
    val bloodGlucoseAverage: Float = 0F,
    val bloodGlucoseValue: Float = 0F,
    val bloodGlucoseUnit: UnitType = UnitType.MMOL,
    val bloodGlucoseList: List<BloodGlucose> = emptyList(),
    val inputErrorMessage: String = "",
    val clearEnteredValue: Boolean = false
) {
    companion object {
        val EMPTY = LogbookScreenState()
    }
}
