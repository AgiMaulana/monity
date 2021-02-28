package io.github.agimaulana.monity.repository

import io.github.agimaulana.monity.dao.DailyRationDao
import io.github.agimaulana.monity.model.DailyRation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

class DailyRationRepository(private val dailyRationDao: DailyRationDao) {

    fun get(): Flow<DailyRation?> = dailyRationDao.get()

    suspend fun update(amount: Long) {
        get().firstOrNull()
                ?.let { it.copy(amount = amount) }
                ?.let { dailyRationDao.update(it) }
                ?: DailyRation(amount).let { dailyRationDao.store(it) }
    }

}