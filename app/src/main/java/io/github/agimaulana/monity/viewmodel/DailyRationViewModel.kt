package io.github.agimaulana.monity.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.agimaulana.monity.LocaleUtils
import io.github.agimaulana.monity.model.DailyRation
import io.github.agimaulana.monity.repository.DailyRationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DailyRationViewModel @Inject constructor(
    private val dailyRationRepository: DailyRationRepository
): BaseViewModel() {
    private val dateFormat = LocaleUtils.dateFormat("EEEE, d MMMM yyyy")
    private val _date = MutableLiveData<String>()
    val date: LiveData<String> get() = _date

    private val _dailyRationAmount: LiveData<DailyRation?> by lazy {
        dailyRationRepository.get()
            .asLiveData(Dispatchers.IO)
    }
    val dailyRationAmount: LiveData<String> get() = _dailyRationAmount
            .map { formatCurrency(it?.amount ?: 0) }

    @VisibleForTesting
    fun formatCurrentDate(date: Date = LocaleUtils.localCalendar().time): String =
        dateFormat.format(date)

    fun fetchDate() {
        viewModelScope.launch(Dispatchers.IO) {
            _date.postValue(formatCurrentDate())
        }
    }

    @VisibleForTesting
    fun formatCurrency(amount: Long): String = LocaleUtils.numberFormat().format(amount)
}