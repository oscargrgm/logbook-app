package com.ogdev.mysugrlogbook.logbook.domain.validator

import org.junit.Assert.assertEquals
import org.junit.Test

class BloodGlucoseValueValidatorTest {

    private val validator = BloodGlucoseValueValidator()

    @Test
    fun `WHEN Validator receives a negative value THEN it returns false`() {
        val params = BloodGlucoseValueValidatorParams(-1F)
        val result = validator.validate(params)

        assertEquals(result, false)
    }

    @Test
    fun `WHEN Validator receives a positive value THEN it returns true`() {
        val params = BloodGlucoseValueValidatorParams(1F)
        val result = validator.validate(params)

        assertEquals(result, true)
    }

    @Test
    fun `WHEN Validator receives zero as value THEN it returns true`() {
        val params = BloodGlucoseValueValidatorParams(0F)
        val result = validator.validate(params)

        assertEquals(result, true)
    }
}