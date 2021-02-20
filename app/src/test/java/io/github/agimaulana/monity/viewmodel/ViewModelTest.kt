package io.github.agimaulana.monity.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.github.agimaulana.monity.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule

@ExperimentalCoroutinesApi
open class ViewModelTest {
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    open fun setup() {
    }

    @After
    open fun tearDown() {
    }
}