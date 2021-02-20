
package io.github.agimaulana.monity.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.agimaulana.monity.databinding.ActivityMainBinding
import io.github.agimaulana.monity.extensions.viewBinding

class MainActivity : AppCompatActivity() {
    private val viewBinding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}