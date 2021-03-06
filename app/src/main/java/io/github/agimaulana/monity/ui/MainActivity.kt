
package io.github.agimaulana.monity.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import io.github.agimaulana.monity.databinding.ActivityMainBinding
import io.github.agimaulana.monity.extensions.viewBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewBinding: ActivityMainBinding by viewBinding()
    private val dailyRationFragment: DailyRationFragment by lazy { DailyRationFragment() }
    private val dailyExpenseFragment: DailyExpenseFragment by lazy { DailyExpenseFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction()
                .replace(viewBinding.dailyRationFragment.id, dailyRationFragment)
                .commit()
        supportFragmentManager.beginTransaction()
                .replace(viewBinding.dailyExpensesFragment.id, dailyExpenseFragment)
                .commit()
    }
}