package com.ogdev.mysugrlogbook.logbook.domain.model.result

sealed class SaveBloodGlucoseResult {
    data class Error(val message: String) : SaveBloodGlucoseResult()
    data object Success : SaveBloodGlucoseResult()
}