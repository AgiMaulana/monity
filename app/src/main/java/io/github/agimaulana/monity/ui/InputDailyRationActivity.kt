package io.github.agimaulana.monity.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.github.agimaulana.monity.R
import io.github.agimaulana.monity.databinding.DailyRationInputActivityBinding
import io.github.agimaulana.monity.extensions.viewBinding
import io.github.agimaulana.monity.viewmodel.InputDailyRationViewModel

@AndroidEntryPoint
class InputDailyRationActivity: BaseActivity() {
    override val viewBinding: DailyRationInputActivityBinding by viewBinding()
    override val viewModel: InputDailyRationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding.dailyRationAmountEditText.doOnTextChanged { text, _, _, _ ->
            if (!viewModel.amountSetByViewModel) text?.toString()?.let(viewModel::formatInputToCurrency)
        }
        viewBinding.saveDailyRationAmountButton.setOnClickListener { saveDailyRation() }

        viewModel.dailyRationAmount.observe(this) {
            viewModel.amountSetByViewModel = true
            viewBinding.dailyRationAmountEditText.run {
                setText(it)
                setSelection(length())
            }
            viewModel.amountSetByViewModel = false
        }
    }

    private fun saveDailyRation() {

        viewModel.save()
        Snackbar.make(
            viewBinding.root,
            R.string.input_daily_ration_saved_message,
            Snackbar.LENGTH_LONG
        ).show()
    }
}