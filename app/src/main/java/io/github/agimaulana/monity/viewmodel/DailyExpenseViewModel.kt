package io.github.agimaulana.monity.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.agimaulana.monity.model.DailyExpense
import io.github.agimaulana.monity.model.TotalDailyExpense
import io.github.agimaulana.monity.repository.DailyExpenseRepository
import io.github.agimaulana.monity.repository.DailyRationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class DailyExpenseViewModel @Inject constructor(
        private val dailyRationRepository: DailyRationRepository,
        private val dailyExpenseRepository: DailyExpenseRepository
): BaseViewModel() {
    val dailyExpenses: LiveData<List<DailyExpense>> by lazy {
        dailyExpenseRepository.getAll()
                .asLiveData(Dispatchers.IO)
    }

    @FlowPreview
    val totalRemaining: LiveData<TotalDailyExpense> by lazy {
        collectTotalDailyExpense().asLiveData(Dispatchers.IO)
    }

//    private fun collectTotalDailyExpense() = dailyRationRepository.get()
//            .distinctUntilChanged()
//            .zip(dailyExpenseRepository.getAll().distinctUntilChanged()) { dailyRation, expenses ->
//                val totalExpenses = expenses.sumOf { it.amount }
//                val remaining = (dailyRation?.amount ?: 0) - expenses.sumOf { it.amount }
//                TotalDailyExpense(totalExpenses, remaining)
//            }

    @FlowPreview
    private fun collectTotalDailyExpense() = dailyExpenseRepository.getAll()
            .flatMapMerge { expenses ->
                dailyRationRepository.get().map { dailyRation ->
                    val totalExpenses = expenses.sumOf { it.amount }
                    val remaining = (dailyRation?.amount ?: 0) - expenses.sumOf { it.amount }
                    TotalDailyExpense(totalExpenses, remaining)
                }
            }
}