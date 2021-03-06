package io.github.agimaulana.monity.dao

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import io.github.agimaulana.monity.data.AppDatabase
import io.github.agimaulana.monity.model.DailyRation
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest=Config.NONE)
class DailyRationDaoTest {
    private lateinit var appDatabase: AppDatabase
    private lateinit var dailyRationDao: DailyRationDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = AppDatabase.create(context)
        dailyRationDao = appDatabase.dailyRationDao
    }

    @Test
    fun test_appFirstRun_get_returnNull() {
        val dailyRation = runBlocking { dailyRationDao.get().first() }
        assertNull(dailyRation)
    }

    @Test
    fun test_store_get_getAll() {
        val dailyRation = DailyRation(100_000)
        val added = runBlocking {
            dailyRationDao.insert(dailyRation)
            dailyRationDao.get()
                    .first()
        }

        val getAll = runBlocking {
            dailyRationDao.getAll()
                    .first()
        }

        assertEquals(1, getAll.size)
        assertEquals(dailyRation.amount, getAll.first().amount)
        assertEquals(dailyRation.amount, added.amount)
    }

    @Test
    fun test_softDelete_getDeleted() {
        val dailyRation1 = runBlocking {
            dailyRationDao.insert(DailyRation(100_000))
            dailyRationDao.get().first()
        }

        val dailyRation2 = runBlocking {
            dailyRation1.let { dailyRationDao.softDelete(it) }
            dailyRationDao.insert(DailyRation(50_000))
            dailyRationDao.get().first()
        }

        val dailyRation3 = runBlocking {
            dailyRation2.let { dailyRationDao.softDelete(it) }
            dailyRationDao.insert(DailyRation(10_000))
            dailyRationDao.get().first()
        }

        val deleted = runBlocking { dailyRationDao.getDeleted().first() }
        assertTrue(deleted.any { dailyRation1.id == it.id })
        assertTrue(deleted.any { dailyRation2.id == it.id })
        assertFalse(deleted.any { dailyRation3.id == it.id })
    }
}