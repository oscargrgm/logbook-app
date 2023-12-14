package com.ogdev.mysugrlogbook.logbook.domain.usecase

import com.ogdev.mysugrlogbook.logbook.domain.model.params.SaveBloodGlucoseParams
import com.ogdev.mysugrlogbook.logbook.domain.model.result.SaveBloodGlucoseResult

interface SaveBloodGlucose {
    suspend operator fun invoke(params: SaveBloodGlucoseParams): SaveBloodGlucoseResult
}