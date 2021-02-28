package io.github.agimaulana.monity.model

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DailyRationTest {

    private lateinit var dailyRation: DailyRation

    @Before
    fun setup() {
        dailyRation = DailyRation(100_000)
    }

    @Test
    fun test_amountAsCurrency() {
        assertEquals("Rp100.000", dailyRation.amountAsCurrency)
    }

}