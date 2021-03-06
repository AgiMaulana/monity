package io.github.agimaulana.monity.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
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

    private var setByViewModel = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.toolbar.setOnMenuItemClickListener(this::onMenuItemClicked)
        viewBinding.addDailyExpenseTimeEditText.setOnClickListener {
            showTimePicker()
        }
        viewBinding.addDailyExpenseActivityNameEditText.doAfterTextChanged {
            if (!setByViewModel) {
                viewModel.activityName = it?.toString() ?: ""
            }
            invalidateAddButton()
        }
        viewBinding.addDailyExpenseAmountEditText.doOnTextChanged { text, _, _, _ ->
            if (!setByViewModel) {
                viewModel.amount = text?.toString() ?: ""
            }
            invalidateAddButton()
        }
        viewBinding.addDailyExpenseButton.setOnClickListener {
            save()
        }

        viewModel.dailyExpense.observe(viewLifecycleOwner) {
            setByViewModel = true
            viewBinding.addDailyExpenseTimeEditText.setText(it.time)
            viewBinding.addDailyExpenseActivityNameEditText.run {
                setText(it.activityName)
                if (hasFocus()) {
                    setSelection(length())
                }
            }
            viewBinding.addDailyExpenseAmountEditText.run {
                setText(it.amountAsCurrency)
                if (hasFocus()) {
                    setSelection(length())
                }
            }
            setByViewModel = false
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

    private fun invalidateAddButton() {
        viewBinding.addDailyExpenseButton.isEnabled =
                viewModel.activityName.isNotBlank() && viewModel.userInputAmount > 0
    }

    private fun save() {
        viewModel.save().observe(viewLifecycleOwner) { isSaved ->
            if (isSaved) {
                dismiss()
            }
        }
    }
}