package io.github.agimaulana.monity.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.agimaulana.monity.databinding.DailyRationFragmentBinding
import io.github.agimaulana.monity.viewmodel.DailyRationViewModel

@AndroidEntryPoint
class DailyRationFragment: BaseFragment() {
    override val viewBinding: DailyRationFragmentBinding by lazy {
        DailyRationFragmentBinding.inflate(layoutInflater)
    }
    override val viewModel: DailyRationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.date.observe(viewLifecycleOwner, viewBinding.currentDateTextView::setText)
        viewModel.dailyRationAmount.observe(viewLifecycleOwner,
                viewBinding.dailyRationAmountTextView::setText)

        viewBinding.addDailyExpenseButton.setOnClickListener {
            AddDailyExpenseDialog().show(childFragmentManager, "addDailyExpense")
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchDate()
    }
}