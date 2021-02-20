package io.github.agimaulana.monity.ui

import android.os.Bundle
import io.github.agimaulana.monity.databinding.DailyRationInputActivityBinding
import io.github.agimaulana.monity.extensions.viewBinding

class InputDailyRationActivity: BaseActivity() {
    private val viewBinding: DailyRationInputActivityBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle
    }
}