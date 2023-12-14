package com.ogdev.mysugrlogbook.logbook.domain.mapper

import com.ogdev.mysugrlogbook.logbook.domain.model.BloodGlucose
import com.ogdev.mysugrlogbook.logbook.domain.model.UnitType
import org.junit.Assert.assertEquals
import org.junit.Test

class ValueMapperTest {

    private val mapper = ValueMapper()

    @Test
    fun `WHEN Mapper receives a Model in MMOL it returns the proper value in MGDL`() {
        val bloodGlucose = BloodGlucose(1F, UnitType.MMOL)

        val result = mapper.toUnit(bloodGlucose, UnitType.MGDL)

        assertEquals(18.0182F, result.value)
        assertEquals(UnitType.MGDL, result.unit)
    }

    @Test
    fun `WHEN Mapper receives a Model in MGDL it returns the proper value in MMOL`() {
        val bloodGlucose = BloodGlucose(18.0182F, UnitType.MGDL)

        val result = mapper.toUnit(bloodGlucose, UnitType.MMOL)

        assertEquals(1F, result.value)
        assertEquals(UnitType.MMOL, result.unit)
    }

    @Test
    fun `WHEN Mapper receives the same unit THEN it returns the same value`() {
        val bloodGlucose = BloodGlucose(1F, UnitType.MMOL)

        val result = mapper.toUnit(bloodGlucose, UnitType.MMOL)

        assertEquals(1F, result.value)
        assertEquals(UnitType.MMOL, result.unit)
    }
}