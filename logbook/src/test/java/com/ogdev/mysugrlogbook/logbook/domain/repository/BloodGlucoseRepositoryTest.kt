package com.ogdev.mysugrlogbook.logbook.domain.repository

import com.ogdev.mysugrlogbook.logbook.data.dao.BloodGlucoseDao
import com.ogdev.mysugrlogbook.logbook.data.entity.BloodGlucoseEntity
import com.ogdev.mysugrlogbook.logbook.data.repository.BloodGlucoseRepositoryImpl
import com.ogdev.mysugrlogbook.logbook.domain.model.UnitType
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class BloodGlucoseRepositoryTest {

    private val bloodGlucoseDao: BloodGlucoseDao = mock()

    private val repository: BloodGlucoseRepository = BloodGlucoseRepositoryImpl(bloodGlucoseDao)

    @Test
    fun `WHEN Repository saves BloodGlucose THEN DAO inserts it`() = runTest {
        val bloodGlucoseEntity = BloodGlucoseEntity(value = 12F, unit = UnitType.MMOL.name)
        repository.setBloodGlucose(bloodGlucose = bloodGlucoseEntity)

        verify(bloodGlucoseDao).insert(bloodGlucoseEntity)
    }

    @Test
    fun `WHEN Repository fetches BloodGlucose list THEN DAO retrieves it`() {
        repository.getBloodGlucoseList()

        verify(bloodGlucoseDao).getAll()
    }

    @Test
    fun `WHEN Repository has set multiple BloodGlucose AND it fetches them THEN DAO retrieves them`() = runTest {
        val entities = listOf(
            BloodGlucoseEntity(value = 1F, unit = UnitType.MMOL.name),
            BloodGlucoseEntity(value = 2F, unit = UnitType.MGDL.name),
            BloodGlucoseEntity(value = 3F, unit = UnitType.MMOL.name),
            BloodGlucoseEntity(value = 4F, unit = UnitType.MGDL.name),
            BloodGlucoseEntity(value = 5F, unit = UnitType.MMOL.name)
        )
        entities.forEach { repository.setBloodGlucose(it) }

        repository.getBloodGlucoseList()

        verify(bloodGlucoseDao, times(5)).insert(any())
        verify(bloodGlucoseDao).getAll()
    }
}