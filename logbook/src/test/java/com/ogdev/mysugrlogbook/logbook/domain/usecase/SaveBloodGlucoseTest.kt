package com.ogdev.mysugrlogbook.logbook.domain.usecase

import com.ogdev.mysugrlogbook.logbook.data.entity.BloodGlucoseEntity
import com.ogdev.mysugrlogbook.logbook.domain.model.UnitType
import com.ogdev.mysugrlogbook.logbook.domain.model.params.SaveBloodGlucoseParams
import com.ogdev.mysugrlogbook.logbook.domain.model.result.SaveBloodGlucoseResult
import com.ogdev.mysugrlogbook.logbook.domain.repository.BloodGlucoseRepository
import com.ogdev.mysugrlogbook.logbook.domain.validator.BloodGlucoseValueValidator
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class SaveBloodGlucoseTest {

    private val validator: BloodGlucoseValueValidator = mock()
    private val bloodGlucoseRepository: BloodGlucoseRepository = mock()

    private val useCase: SaveBloodGlucose = SaveBloodGlucoseImpl(
        validator,
        bloodGlucoseRepository
    )

    @Test
    fun `WHEN UseCase is invoked THEN Validator validates the Parameters`() = runTest {
        val params = SaveBloodGlucoseParams(value = 1F, unit = UnitType.MGDL)
        useCase.invoke(params)

        verify(validator).validate(any())
    }

    @Test
    fun `WHEN UseCase is invoked AND Validator validates THEN Repository inserts the Entity AND Result is Success`() =
        runTest {
            whenever(validator.validate(any())).thenReturn(true)

            val params = SaveBloodGlucoseParams(value = 1F, unit = UnitType.MGDL)
            val result = useCase.invoke(params)

            val entity = BloodGlucoseEntity(value = 1F, unit = UnitType.MGDL.name)
            verify(bloodGlucoseRepository).setBloodGlucose(entity)

            assert(result is SaveBloodGlucoseResult.Success)
        }

    @Test
    fun `WHEN UseCase is invoked AND Validator does not validate THEN Result is Error`() = runTest {
            whenever(validator.validate(any())).thenReturn(false)

            val params = SaveBloodGlucoseParams(value = 1F, unit = UnitType.MGDL)
            val result = useCase.invoke(params)

            verify(validator).validate(any())
            verify(bloodGlucoseRepository, never()).setBloodGlucose(any())

            assert(result is SaveBloodGlucoseResult.Error)
        }

}