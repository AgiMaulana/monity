package io.github.agimaulana.monity.viewmodel

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.agimaulana.monity.LocaleUtils
import io.github.agimaulana.monity.model.DailyExpense
import io.github.agimaulana.monity.model.TotalDailyExpense
import io.github.agimaulana.monity.repository.DailyExpenseRepository
import io.github.agimaulana.monity.repository.DailyRationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
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

    val totalRemaining: LiveData<TotalDailyExpense> by lazy {
        val mediatorLiveData = MediatorLiveData<TotalDailyExpense>()
        mediatorLiveData.addSource(MutableLiveData(TotalDailyExpense(0, 0))) {
            mediatorLiveData.postValue(it)
        }

        mediatorLiveData.addSource(
                dailyExpenses
                        .map {
                            Log.d("DailyExpense", "1. Model: $it")
                            it.sumBy { expense -> expense.amount.toInt() }
                        }
        ) {
            val total = mediatorLiveData.value?.copy(totalExpenses = it.toLong())
            total?.let(mediatorLiveData::postValue)
        }

        mediatorLiveData.addSource(
                dailyRationRepository.get()
                        .asLiveData(Dispatchers.IO)
                        .map {
                            Log.d("DailyExpense", "2. Model: $it")
//                            it.amount - (mediatorLiveData.value?.totalExpenses ?: 0)
                            it?.amount ?: 0
                        }.map {
                            it - (mediatorLiveData.value?.totalExpenses ?: 0)
                        }
        ) {
            val total = mediatorLiveData.value?.copy(totalRemaining = it.toLong())
            total?.let(mediatorLiveData::postValue)
        }

        mediatorLiveData
    }
}