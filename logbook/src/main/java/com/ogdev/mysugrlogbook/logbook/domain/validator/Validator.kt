package com.ogdev.mysugrlogbook.logbook.domain.validator

interface Validator<PARAM : ValidatorParams> {
    fun validate(params: PARAM): Boolean
}

abstract class ValidatorParams