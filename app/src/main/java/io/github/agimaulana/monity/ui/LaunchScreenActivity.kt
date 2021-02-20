package io.github.agimaulana.monity.ui

import android.content.Intent
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.suspendCoroutine

class LaunchScreenActivity: BaseActivity() {

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            delay(1200)
            suspendCoroutine {
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finishAffinity()
            }
        }
    }

}