package io.github.agimaulana.monity

import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.After
import org.junit.Before
import org.junit.Rule

open class UiTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    open fun setup() {
        hiltRule.inject()
    }

    @After
    open fun tearDown() {

    }
}