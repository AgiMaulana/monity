package io.github.agimaulana.monity.viewmodel

import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class InputDailyRationViewModelTest: ViewModelTest() {

    private lateinit var viewModel: InputDailyRationViewModel

    @Before
    override fun setup() {
        viewModel = InputDailyRationViewModel(mockk())
    }

    @Test
    fun test_formatInputToCurrency() {
        viewModel.formatInputToCurrency("Rp100.000")
        val parsed1 = viewModel._dailyRationAmount.value

        viewModel.formatInputToCurrency("Rp")
        val parsed2 = viewModel._dailyRationAmount.value

        assertEquals(100_000L, parsed1)
        assertEquals(0L, parsed2)
    }

}