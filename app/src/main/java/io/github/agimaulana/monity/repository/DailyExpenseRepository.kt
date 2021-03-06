package io.github.agimaulana.monity.repository

import io.github.agimaulana.monity.dao.DailyExpenseDao
import io.github.agimaulana.monity.model.DailyExpense

class DailyExpenseRepository(private val dailyExpenseDao: DailyExpenseDao) {

    fun getAll() = dailyExpenseDao.getAll()

    suspend fun insert(dailyExpense: DailyExpense) = dailyExpenseDao.insert(dailyExpense)

    suspend fun update(dailyExpense: DailyExpense) = dailyExpenseDao.update(dailyExpense)

    suspend fun delete(dailyExpense: DailyExpense) = dailyExpenseDao.delete(dailyExpense)

}