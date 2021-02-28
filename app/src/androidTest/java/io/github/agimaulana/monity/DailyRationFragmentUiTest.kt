package io.github.agimaulana.monity

import androidx.fragment.app.testing.launchFragmentInContainer
import io.github.agimaulana.monity.ui.DailyRationFragment
import io.github.agimaulana.monity.viewmodel.DailyRationViewModel
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class DailyRationFragmentUiTest: UiTest() {

    @Inject
    lateinit var viewModel: DailyRationViewModel

    @Before
    override fun setup() {
        super.setup()
    }

    @Test
    fun test_showDate() {
        launchFragmentInContainer<DailyRationFragment>()
    }

}