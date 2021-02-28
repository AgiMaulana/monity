package io.github.agimaulana.monity.viewmodel

import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.*

@ExperimentalCoroutinesApi
class DailyRationViewModelTest: ViewModelTest() {
    private lateinit var viewModel: DailyRationViewModel

    @Before
    override fun setup() {
        viewModel = DailyRationViewModel(mockk())
    }

    @Test
    fun test_formatCurrentDate() {
        val calendar = Calendar.getInstance().apply {
            set(2021, 1, 1)
        }
        val formatted = viewModel.formatCurrentDate(calendar.time)

        val expected = "Senin, 1 Februari 2021"
        assertEquals(expected, formatted)
    }
}