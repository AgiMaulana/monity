package io.github.agimaulana.monity.dao

import androidx.room.*
import io.github.agimaulana.monity.model.DailyExpense
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyExpenseDao {
    @Query("SELECT * FROM DailyExpense")
    fun getAll(): Flow<List<DailyExpense>>

    @Insert
    suspend fun insert(dailyExpense: DailyExpense)

    @Delete
    suspend fun delete(dailyExpense: DailyExpense)

    @Update
    suspend fun update(dailyExpense: DailyExpense)
}