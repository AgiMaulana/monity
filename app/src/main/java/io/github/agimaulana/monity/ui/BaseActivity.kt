package io.github.agimaulana.monity.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseActivity: AppCompatActivity() {
    protected abstract val viewBinding: ViewBinding
    protected abstract val viewModel: ViewModel
}