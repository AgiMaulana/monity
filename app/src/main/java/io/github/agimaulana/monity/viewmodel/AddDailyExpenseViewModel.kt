package io.github.agimaulana.monity.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.agimaulana.monity.LocaleUtils
import io.github.agimaulana.monity.model.DailyExpense
import java.text.ParseException
import javax.inject.Inject

@HiltViewModel
class AddDailyExpenseViewModel @Inject constructor(): BaseViewModel() {
    private val _dailyExpense = MutableLiveData(
            DailyExpense(activityTime = LocaleUtils.localCalendar().time, activityName = "", amount = 0)
    )
    val dailyExpense: LiveData<DailyExpense> get() = _dailyExpense

    private val numberFormat = LocaleUtils.numberFormat()
    private var userInputAmount = 0L
    var amount: String = ""
        set(value) {
            field = value
            userInputAmount = try {
                numberFormat.parse(value)?.toLong() ?: userInputAmount
            } catch (e: ParseException) {
                0
            }
            val dailyExpense = _dailyExpense.value?.copy(amount = userInputAmount)
            _dailyExpense.postValue(dailyExpense)
        }
}