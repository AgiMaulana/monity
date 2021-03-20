package io.github.agimaulana.monity.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.github.agimaulana.monity.databinding.DailyExpensesFragmentBinding
import io.github.agimaulana.monity.viewmodel.DailyExpenseViewModel

@AndroidEntryPoint
class DailyExpenseFragment: BaseFragment() {
    override val viewBinding: DailyExpensesFragmentBinding by lazy {
        DailyExpensesFragmentBinding.inflate(layoutInflater)
    }
    override val viewModel: DailyExpenseViewModel by viewModels()
    private val dailyExpenseRecyclerAdapter = DailyExpenseRecyclerAdapter()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = viewBinding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.dailyExpensesRecyclerView.run {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            adapter = dailyExpenseRecyclerAdapter
        }

        viewModel.dailyExpenses.observe(viewLifecycleOwner) {
            dailyExpenseRecyclerAdapter.set(it)
        }
        viewModel.totalRemaining.observe(viewLifecycleOwner) {
            viewBinding.totalExpensesBodyTextView.text = it.totalExpensesAsCurrency
            viewBinding.totalRemainingBodyTextView.text = it.totalRemainingAsCurrency
        }
    }
}