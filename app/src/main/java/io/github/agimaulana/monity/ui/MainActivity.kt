
package io.github.agimaulana.monity.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import io.github.agimaulana.monity.R
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_daily_ration) {
            startActivity(Intent(this, InputDailyRationActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}