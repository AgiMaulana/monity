package io.github.agimaulana.monity.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import dagger.hilt.android.AndroidEntryPoint
import io.github.agimaulana.monity.R
import io.github.agimaulana.monity.databinding.DailyExpenseAddDialogBinding
import io.github.agimaulana.monity.viewmodel.AddDailyExpenseViewModel

@AndroidEntryPoint
class AddDailyExpenseDialog: BottomSheetDialogFragment() {
    private lateinit var _viewBinding: DailyExpenseAddDialogBinding
    private val viewBinding: DailyExpenseAddDialogBinding by lazy { _viewBinding }
    private val viewModel: AddDailyExpenseViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?, savedInstanceState: Bundle?
    ): View = DailyExpenseAddDialogBinding.inflate(inflater, container, false).also {
        _viewBinding = it
    }.root

    private var amountSetByViewModel = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.toolbar.setOnMenuItemClickListener(this::onMenuItemClicked)
        viewBinding.addDailyExpenseAmountEditText.doOnTextChanged { text, _, _, _ ->
            if (!amountSetByViewModel) {
                viewModel.amount = text?.toString() ?: ""
            }
        }
        viewBinding.addDailyExpenseTimeEditText.setOnClickListener {
            showTimePicker()
        }

        viewModel.dailyExpense.observe(viewLifecycleOwner) {
            viewBinding.addDailyExpenseTimeEditText.setText(it.time)
            viewBinding.addDailyExpenseActivityNameEditText.setText(it.activityName)
            amountSetByViewModel = true
            viewBinding.addDailyExpenseAmountEditText.run {
                setText(it.amountAsCurrency)
                if (hasFocus()) {
                    setSelection(length())
                }
            }
            amountSetByViewModel = false
        }
    }

    private fun onMenuItemClicked(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_close) {
            dismiss()
            true
        } else {
            false
        }
    }

    private fun showTimePicker() {
        MaterialTimePicker.Builder()
                .setInputMode(MaterialTimePicker.INPUT_MODE_CLOCK)
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(viewModel.time.first)
                .setMinute(viewModel.time.second)
                .build()
                .apply {
                    addOnPositiveButtonClickListener {
                        viewModel.setTime(hour, minute)
                    }
                }
                .show(childFragmentManager, "timePicker")
    }
}