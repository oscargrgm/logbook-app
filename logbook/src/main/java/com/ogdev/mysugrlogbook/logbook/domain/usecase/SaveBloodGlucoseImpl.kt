package com.ogdev.mysugrlogbook.logbook.domain.usecase

import com.ogdev.mysugrlogbook.logbook.data.entity.BloodGlucoseEntity
import com.ogdev.mysugrlogbook.logbook.domain.model.params.SaveBloodGlucoseParams
import com.ogdev.mysugrlogbook.logbook.domain.model.result.SaveBloodGlucoseResult
import com.ogdev.mysugrlogbook.logbook.domain.repository.BloodGlucoseRepository
import com.ogdev.mysugrlogbook.logbook.domain.validator.BloodGlucoseValueValidator
import com.ogdev.mysugrlogbook.logbook.domain.validator.BloodGlucoseValueValidatorParams
import javax.inject.Inject

class SaveBloodGlucoseImpl @Inject constructor(
    private val bloodGlucoseValidator: BloodGlucoseValueValidator,
    private val bloodGlucoseRepository: BloodGlucoseRepository
) : SaveBloodGlucose {

    override suspend operator fun invoke(params: SaveBloodGlucoseParams): SaveBloodGlucoseResult {
        val bloodGlucoseValueValidatorParams = BloodGlucoseValueValidatorParams(params.value)

        return if (bloodGlucoseValidator.validate(bloodGlucoseValueValidatorParams)) {
            val bloodGlucoseEntity = BloodGlucoseEntity(
                value = params.value,
                unit = params.unit.name
            )

            bloodGlucoseRepository.setBloodGlucose(bloodGlucoseEntity)
            SaveBloodGlucoseResult.Success
        } else {
            SaveBloodGlucoseResult.Error("Value entered is not correct: <${params.value}>")
        }
    }
}