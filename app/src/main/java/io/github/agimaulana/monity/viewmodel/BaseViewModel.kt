package io.github.agimaulana.monity.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class BaseViewModel: ViewModel() {

    fun CoroutineScope.launchIO(
        block: suspend CoroutineScope.() -> Unit
    ) = launch(Dispatchers.IO, block = block)

}