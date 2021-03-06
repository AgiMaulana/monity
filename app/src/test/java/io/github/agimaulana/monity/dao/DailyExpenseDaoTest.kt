package io.github.agimaulana.monity.dao

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import io.github.agimaulana.monity.LocaleUtils
import io.github.agimaulana.monity.data.AppDatabase
import io.github.agimaulana.monity.model.DailyExpense
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(manifest= Config.NONE)
class DailyExpenseDaoTest {
    private lateinit var appDatabase: AppDatabase
    private lateinit var dailyExpenseDao: DailyExpenseDao


    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = AppDatabase.create(context)
        dailyExpenseDao = appDatabase.dailyExpenseDao
    }

    @Test
    fun test_add() = runBlocking {
        val dailyExpense = DailyExpense(
                activityTime = LocaleUtils.localCalendar().time,
                activityName = "Breakfast",
                amount = 40_000
        )

        dailyExpenseDao.insert(dailyExpense)

        val expense = dailyExpenseDao.getAll().first().first()
        assertEquals(dailyExpense.activityTime, expense.activityTime)
        assertEquals(dailyExpense.activityName, expense.activityName)
        assertEquals(dailyExpense.amount, expense.amount)
    }

    @Test
    fun test_update() = runBlocking {
        val dailyExpense = DailyExpense(
                activityTime = LocaleUtils.localCalendar().time,
                activityName = "Breakfast",
                amount = 40_000
        )

        dailyExpenseDao.insert(dailyExpense)
        val expense = dailyExpenseDao.getAll().first().first()
        val modifiedExpense = expense.copy(amount = 50_000)
        dailyExpenseDao.update(modifiedExpense)

        val updated = dailyExpenseDao.getAll().first().first()
        assertEquals(modifiedExpense.amount, updated.amount)
    }

    @Test
    fun test_delete() = runBlocking {
        val dailyExpense = DailyExpense(
                activityTime = LocaleUtils.localCalendar().time,
                activityName = "Breakfast",
                amount = 40_000
        )

        dailyExpenseDao.insert(dailyExpense)
        val expense = dailyExpenseDao.getAll().first().first()
        dailyExpenseDao.delete(expense)

        val expenses = dailyExpenseDao.getAll().first()
        assertTrue(expenses.isEmpty())
    }

}