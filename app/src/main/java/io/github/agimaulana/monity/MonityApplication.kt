package io.github.agimaulana.monity

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MonityApplication: Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

}