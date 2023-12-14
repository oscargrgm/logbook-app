package com.ogdev.mysugrlogbook.logbook.domain.usecase

import com.ogdev.mysugrlogbook.logbook.domain.model.params.GetAverageBloodGlucoseParams
import com.ogdev.mysugrlogbook.logbook.domain.model.params.GetBloodGlucoseListParams
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val DEFAULT_VALUE = 0F

class GetAverageBloodGlucoseImpl @Inject constructor(
    private val getBloodGlucoseList: GetBloodGlucoseList
) : GetAverageBloodGlucose {

    override operator fun invoke(params: GetAverageBloodGlucoseParams): Flow<Float> {
        val getBloodGlucoseListParams = GetBloodGlucoseListParams(params.unit)
        return getBloodGlucoseList.invoke(getBloodGlucoseListParams)
            .map {
                if (it.isEmpty()) {
                    DEFAULT_VALUE
                } else {
                    it.map { bloodGlucose -> bloodGlucose.value }.average().toFloat()
                }
            }
    }
}