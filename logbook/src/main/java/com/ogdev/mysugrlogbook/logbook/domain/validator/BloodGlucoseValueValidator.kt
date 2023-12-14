package com.ogdev.mysugrlogbook.logbook.domain.validator

import javax.inject.Inject

class BloodGlucoseValueValidator @Inject constructor() : Validator<BloodGlucoseValueValidatorParams> {

    override fun validate(params: BloodGlucoseValueValidatorParams): Boolean = params.value >= 0
}

data class BloodGlucoseValueValidatorParams(val value: Float) : ValidatorParams()