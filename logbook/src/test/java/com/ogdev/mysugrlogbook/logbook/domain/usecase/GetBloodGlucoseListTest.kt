package com.ogdev.mysugrlogbook.logbook.domain.usecase

import com.ogdev.mysugrlogbook.logbook.data.entity.BloodGlucoseEntity
import com.ogdev.mysugrlogbook.logbook.domain.mapper.DomainModelMapper
import com.ogdev.mysugrlogbook.logbook.domain.mapper.ValueMapper
import com.ogdev.mysugrlogbook.logbook.domain.model.BloodGlucose
import com.ogdev.mysugrlogbook.logbook.domain.model.UnitType
import com.ogdev.mysugrlogbook.logbook.domain.model.params.GetBloodGlucoseListParams
import com.ogdev.mysugrlogbook.logbook.domain.repository.BloodGlucoseRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetBloodGlucoseListTest {

    private val repository: BloodGlucoseRepository = mock()
    private val domainModelMapper = DomainModelMapper()
    private val valueMapper = ValueMapper()

    private val useCase: GetBloodGlucoseList = GetBloodGlucoseListImpl(
        repository,
        domainModelMapper,
        valueMapper
    )

    @Test
    fun `WHEN UseCase is invoked THEN Repository retrieves the Blood Glucose list`() {
        val params = GetBloodGlucoseListParams(UnitType.MGDL)
        useCase.invoke(params)

        verify(repository).getBloodGlucoseList()
    }

    @Test
    fun `WHEN UseCase is invoked AND Repository retrieves an empty list THEN result is empty`() =
        runTest {
            whenever(repository.getBloodGlucoseList()).thenReturn(flowOf(emptyList()))

            val params = GetBloodGlucoseListParams(UnitType.MGDL)
            val result = useCase.invoke(params).first()

            assertEquals(emptyList<BloodGlucose>(), result)
        }

    @Test
    fun `WHEN UseCase is invoked AND Repository retrieves a list THEN result is a mapped list`() =
        runTest {
            val entityList = listOf(
                BloodGlucoseEntity(value = 1F, unit = UnitType.MGDL.name),
                BloodGlucoseEntity(value = 2F, unit = UnitType.MGDL.name),
                BloodGlucoseEntity(value = 3F, unit = UnitType.MGDL.name)
            )
            whenever(repository.getBloodGlucoseList()).thenReturn(flowOf(entityList))

            val params = GetBloodGlucoseListParams(UnitType.MGDL)
            val result = useCase.invoke(params).first()

            val expected = listOf(
                BloodGlucose(1F, UnitType.MGDL),
                BloodGlucose(2F, UnitType.MGDL),
                BloodGlucose(3F, UnitType.MGDL),
            )

            assertEquals(expected, result)
        }
}