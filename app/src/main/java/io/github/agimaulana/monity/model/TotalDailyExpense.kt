package io.github.agimaulana.monity.model

import io.github.agimaulana.monity.LocaleUtils

data class TotalDailyExpense(val totalExpenses: Long, val totalRemaining: Long) {

    private val numberFormat = LocaleUtils.numberFormat()

    val totalExpensesAsCurrency: String get() = numberFormat.format(totalExpenses)

    val totalRemainingAsCurrency: String get() = numberFormat.format(totalRemaining)
}