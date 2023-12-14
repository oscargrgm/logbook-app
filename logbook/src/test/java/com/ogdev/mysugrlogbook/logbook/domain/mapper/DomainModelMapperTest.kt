package com.ogdev.mysugrlogbook.logbook.domain.mapper

import com.ogdev.mysugrlogbook.logbook.data.entity.BloodGlucoseEntity
import com.ogdev.mysugrlogbook.logbook.domain.model.BloodGlucose
import com.ogdev.mysugrlogbook.logbook.domain.model.UnitType
import org.junit.Assert.assertEquals
import org.junit.Test

class DomainModelMapperTest {

    private val mapper = DomainModelMapper()

    @Test
    fun `WHEN Mapper receives a list of Entities THEN it returns a list of Domain models`() {
        val input = listOf(
            BloodGlucoseEntity(value = 1F, unit = UnitType.MGDL.name),
            BloodGlucoseEntity(value = 2F, unit = UnitType.MMOL.name),
            BloodGlucoseEntity(value = 3F, unit = UnitType.MGDL.name),
        )

        val expectedOutput = listOf(
            BloodGlucose(1F, UnitType.MGDL),
            BloodGlucose(2F, UnitType.MMOL),
            BloodGlucose(3F, UnitType.MGDL),
        )

        val result = mapper.toDomainModel(input)

        assertEquals(expectedOutput, result)

    }
}