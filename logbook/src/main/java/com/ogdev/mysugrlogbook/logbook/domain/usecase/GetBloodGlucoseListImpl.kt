package com.ogdev.mysugrlogbook.logbook.domain.usecase

import com.ogdev.mysugrlogbook.logbook.domain.mapper.DomainModelMapper
import com.ogdev.mysugrlogbook.logbook.domain.mapper.ValueMapper
import com.ogdev.mysugrlogbook.logbook.domain.model.BloodGlucose
import com.ogdev.mysugrlogbook.logbook.domain.model.params.GetBloodGlucoseListParams
import com.ogdev.mysugrlogbook.logbook.domain.repository.BloodGlucoseRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetBloodGlucoseListImpl @Inject constructor(
    private val bloodGlucoseRepository: BloodGlucoseRepository,
    private val domainModelMapper: DomainModelMapper,
    private val valueMapper: ValueMapper
) : GetBloodGlucoseList {

    override operator fun invoke(params: GetBloodGlucoseListParams): Flow<List<BloodGlucose>> =
        bloodGlucoseRepository.getBloodGlucoseList()
            .map(domainModelMapper::toDomainModel)
            .map { list ->
                list.map { bloodGlucose -> valueMapper.toUnit(bloodGlucose, params.unit) }
            }
}