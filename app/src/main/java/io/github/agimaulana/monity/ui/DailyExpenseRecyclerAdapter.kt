package io.github.agimaulana.monity.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.agimaulana.monity.databinding.ExpsenseItemBinding
import io.github.agimaulana.monity.model.DailyExpense

class DailyExpenseRecyclerAdapter: RecyclerView.Adapter<DailyExpenseRecyclerAdapter.DailyExpenseViewHolder>() {
    inner class DailyExpenseViewHolder(
            private val viewBinding: ExpsenseItemBinding
    ): RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(expense: DailyExpense) {
            with(viewBinding) {
                with(expense) {
                    expenseActivityTimeTextView.text = time
                    expenseActivityNameTextView.text = activityName
                    expenseAmountTextView.text = amountAsCurrency
                }
            }
        }
    }

    private val expenses = mutableListOf<DailyExpense>()

    fun putAll(dailyExpenses: List<DailyExpense>) {
        val startIndex = expenses.lastIndex
        expenses.addAll(dailyExpenses)
        val endIndex = expenses.lastIndex
        notifyItemRangeInserted(startIndex, endIndex)
    }

    fun put(dailyExpense: DailyExpense) {
        expenses.add(dailyExpense)
        notifyItemInserted(expenses.lastIndex)
    }

    fun set(dailyExpenses: List<DailyExpense>) {
        expenses.clear()
        expenses.addAll(dailyExpenses)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyExpenseViewHolder {
        return LayoutInflater.from(parent.context)
                .let { ExpsenseItemBinding.inflate(it, parent, false) }
                .let(::DailyExpenseViewHolder)
    }

    override fun onBindViewHolder(holder: DailyExpenseViewHolder, position: Int) =
            holder.bind(expenses[position])

    override fun getItemCount(): Int = expenses.size
}