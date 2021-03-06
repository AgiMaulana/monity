package io.github.agimaulana.monity.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.agimaulana.monity.LocaleUtils
import io.github.agimaulana.monity.model.DailyExpense
import java.text.ParseException
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddDailyExpenseViewModel @Inject constructor(): BaseViewModel() {
    private val _dailyExpense = MutableLiveData(
            DailyExpense(activityTime = LocaleUtils.localCalendar().time, activityName = "", amount = 0)
    )
    val dailyExpense: LiveData<DailyExpense> get() = _dailyExpense
    val time: Pair<Int, Int> get() {
        val time = LocaleUtils.localCalendar().apply {
            dailyExpense.value?.activityTime?.let {
                time = it
            }
        }
        return time[Calendar.HOUR_OF_DAY] to time[Calendar.MINUTE]
    }

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

    fun setTime(hour: Int, minute: Int) {
        val time = LocaleUtils.localCalendar().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
        }
        val dailyExpense = _dailyExpense.value?.copy(activityTime = time.time)
        _dailyExpense.postValue(dailyExpense)
    }
}