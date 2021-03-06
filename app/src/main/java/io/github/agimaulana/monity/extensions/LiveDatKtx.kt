package io.github.agimaulana.monity.extensions

import androidx.lifecycle.MutableLiveData

fun <T> mutableLiveData(block: MutableLiveData<T>.() -> Unit): MutableLiveData<T> {
    val liveData = MutableLiveData<T>()
    block.invoke(liveData)
    return liveData
}