package io.github.agimaulana.monity.extensions

import android.view.LayoutInflater
import androidx.core.app.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlin.reflect.KClass

fun <T: ViewBinding> inflateViewBinding(layoutInflater: LayoutInflater, clazz: KClass<T>): T {
    val inflater = clazz.java.getMethod("inflate", LayoutInflater::class.java)
    return inflater.invoke(null, layoutInflater) as T
}

inline fun <reified T: ViewBinding> ComponentActivity.viewBinding(): Lazy<T> {
    lateinit var viewBinding: T
    lifecycleScope.launchWhenCreated {
        viewBinding = inflateViewBinding(layoutInflater, T::class)
        setContentView(viewBinding.root)
    }
    return lazy { viewBinding }
}

inline fun <reified T: ViewBinding> Fragment.viewBinding(): Lazy<T> = lazy {
    inflateViewBinding(layoutInflater, T::class)
}