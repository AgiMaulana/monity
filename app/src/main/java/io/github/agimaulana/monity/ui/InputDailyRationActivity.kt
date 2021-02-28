package io.github.agimaulana.monity.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import dagger.hilt.android.AndroidEntryPoint
import io.github.agimaulana.monity.databinding.DailyRationInputActivityBinding
import io.github.agimaulana.monity.extensions.viewBinding
import io.github.agimaulana.monity.viewmodel.InputDailyRationViewModel

@AndroidEntryPoint
class InputDailyRationActivity: BaseActivity() {
    override val viewBinding: DailyRationInputActivityBinding by viewBinding()
    override val viewModel: InputDailyRationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding.dailyRationAmountEditText.doOnTextChanged { text, start, before, count ->
            if (!viewModel.amountSetByViewModel) text?.toString()?.let(viewModel::formatInputToCurrency)
        }

        viewModel.dailyRationAmount.observe(this) {
            viewModel.amountSetByViewModel = true
            viewBinding.dailyRationAmountEditText.run {
                setText(it)
                setSelection(length())
            }
            viewModel.amountSetByViewModel = false
        }
    }
}