package com.ogdev.mysugrlogbook.logbook.domain.usecase

import com.ogdev.mysugrlogbook.logbook.domain.model.params.GetAverageBloodGlucoseParams
import kotlinx.coroutines.flow.Flow

interface GetAverageBloodGlucose {
    operator fun invoke(params: GetAverageBloodGlucoseParams): Flow<Float>
}