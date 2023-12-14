package com.ogdev.mysugrlogbook.logbook.domain.usecase

import com.ogdev.mysugrlogbook.logbook.domain.model.BloodGlucose
import com.ogdev.mysugrlogbook.logbook.domain.model.UnitType
import com.ogdev.mysugrlogbook.logbook.domain.model.params.GetAverageBloodGlucoseParams
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetAverageBloodGlucoseTest {

    private val getBloodGlucoseList: GetBloodGlucoseList = mock()

    private val useCase: GetAverageBloodGlucose = GetAverageBloodGlucoseImpl(getBloodGlucoseList)

    @Test
    fun `WHEN UseCase is invoked THEN GetBloodGlucoseList is invoked`() {
        whenever(getBloodGlucoseList.invoke(any())).thenReturn(any())

        val params = GetAverageBloodGlucoseParams(UnitType.MGDL)
        useCase.invoke(params)

        verify(getBloodGlucoseList).invoke(any())
    }

    @Test
    fun `WHEN UseCase is invoked AND Repository retrieves and EMPTY list THEN result is 0`() =
        runTest {
            whenever(getBloodGlucoseList.invoke(any())).thenReturn(flowOf(emptyList()))

            val params = GetAverageBloodGlucoseParams(UnitType.MGDL)
            val result = useCase.invoke(params).first()

            assertEquals(0F, result)
        }

    @Test
    fun `WHEN UseCase is invoked AND Repository retrieves multiple values THEN it returns the average`() =
        runTest {
            val bloodGlucoseList = listOf(
                BloodGlucose(value = 10F, unit = UnitType.MGDL),
                BloodGlucose(value = 5F, unit = UnitType.MGDL),
                BloodGlucose(value = 15F, unit = UnitType.MGDL)
            )
            whenever(getBloodGlucoseList.invoke(any())).thenReturn(flowOf(bloodGlucoseList))

            val params = GetAverageBloodGlucoseParams(UnitType.MGDL)
            val result = useCase.invoke(params).first()

            assertEquals(10F, result)
        }
}