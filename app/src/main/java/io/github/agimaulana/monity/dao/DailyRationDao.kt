package io.github.agimaulana.monity.dao

import androidx.room.*
import io.github.agimaulana.monity.LocaleUtils
import io.github.agimaulana.monity.model.DailyRation

@Dao
interface DailyRationDao {
    @Query("SELECT * FROM DailyRation ORDER BY id DESC")
    suspend fun getAll(): List<DailyRation>

    @Query("SELECT * FROM DailyRation WHERE deletedAt IS NOT NULL ORDER BY id DESC")
    suspend fun getDeleted(): List<DailyRation>

    @Query("SELECT * FROM DailyRation WHERE deletedAt IS NULL LIMIT 1")
    suspend fun get(): DailyRation?

    @Insert
    suspend fun store(dailyRation: DailyRation)

    @Update
    suspend fun update(dailyRation: DailyRation)

    @Transaction
    suspend fun softDelete(dailyRation: DailyRation) =
        update(dailyRation.copy(deletedAt = LocaleUtils.localCalendar().time))
}