package com.ogdev.mysugrlogbook.logbook.data.repository

import com.ogdev.mysugrlogbook.logbook.data.dao.BloodGlucoseDao
import com.ogdev.mysugrlogbook.logbook.data.entity.BloodGlucoseEntity
import com.ogdev.mysugrlogbook.logbook.domain.repository.BloodGlucoseRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

@Singleton
class BloodGlucoseRepositoryImpl @Inject constructor(
    private val bloodGlucoseDao: BloodGlucoseDao
) : BloodGlucoseRepository {

    override suspend fun setBloodGlucose(bloodGlucose: BloodGlucoseEntity) {
        withContext(Dispatchers.IO) {
            bloodGlucoseDao.insert(bloodGlucose)
        }
    }

    override fun getBloodGlucoseList(): Flow<List<BloodGlucoseEntity>> = bloodGlucoseDao.getAll()
}