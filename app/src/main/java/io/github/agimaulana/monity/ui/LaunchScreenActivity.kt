package io.github.agimaulana.monity.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.suspendCoroutine

class LaunchScreenActivity: AppCompatActivity() {

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            delay(1200)
            suspendCancellableCoroutine {
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finishAffinity()
            }
        }
    }

}