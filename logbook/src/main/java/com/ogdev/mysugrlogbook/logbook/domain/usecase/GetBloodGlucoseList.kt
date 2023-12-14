package com.ogdev.mysugrlogbook.logbook.domain.usecase

import com.ogdev.mysugrlogbook.logbook.domain.model.BloodGlucose
import com.ogdev.mysugrlogbook.logbook.domain.model.params.GetBloodGlucoseListParams
import kotlinx.coroutines.flow.Flow

interface GetBloodGlucoseList {
    operator fun invoke(params: GetBloodGlucoseListParams): Flow<List<BloodGlucose>>
}