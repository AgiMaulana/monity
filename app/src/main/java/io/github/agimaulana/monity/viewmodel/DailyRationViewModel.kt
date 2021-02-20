package io.github.agimaulana.monity.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.github.agimaulana.monity.LocaleUtils
import kotlinx.coroutines.launch
import java.util.*

class DailyRationViewModel: BaseViewModel() {
    private val dateFormat = LocaleUtils.dateFormat("EEEE, d MMMM yyyy")
    private val _date = MutableLiveData<String>()
    val date: LiveData<String> get() = _date

    @VisibleForTesting
    fun formatCurrentDate(date: Date = LocaleUtils.localCalendar().time): String =
        dateFormat.format(date)

    fun fetchDate() {
        viewModelScope.launch {
            _date.postValue(formatCurrentDate())
        }
    }
}