package io.github.agimaulana.monity.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.agimaulana.monity.LocaleUtils
import io.github.agimaulana.monity.model.DailyRation
import io.github.agimaulana.monity.repository.DailyRationRepository
import kotlinx.coroutines.flow.firstOrNull
import java.text.ParseException
import javax.inject.Inject

@HiltViewModel
class InputDailyRationViewModel @Inject constructor(
        private val dailyRationRepository: DailyRationRepository
): BaseViewModel() {
    @VisibleForTesting
    val _dailyRationAmount = MutableLiveData<Long>()

    val dailyRationAmount: LiveData<String> get() = _dailyRationAmount.map {
        DailyRation(it).amountAsCurrency
    }

    init {
        viewModelScope.launchIO {
            _dailyRationAmount.postValue(dailyRationRepository.get().firstOrNull()?.amount ?: 0L)
        }
    }

    var amountSetByViewModel = false
    private var userInputAmount = 0L
    private val numberFormat = LocaleUtils.numberFormat()

    fun formatInputToCurrency(userInput: String) {
        userInputAmount = try {
            numberFormat.parse(userInput)?.toLong() ?: userInputAmount
        } catch (e: ParseException) {
            0
        }
        _dailyRationAmount.postValue(userInputAmount)
    }

    fun save() {
        viewModelScope.launchIO {
            dailyRationRepository.update(userInputAmount)
        }
    }
}