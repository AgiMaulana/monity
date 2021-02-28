package io.github.agimaulana.monity.extensions

import android.view.LayoutInflater
import androidx.core.app.ComponentActivity
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

open class ViewBindingDelegate<T: ViewBinding>(protected val clazz: KClass<T>): ReadOnlyProperty<ComponentActivity, T> {
    private var viewBinding: T? = null

    override fun getValue(thisRef: ComponentActivity, property: KProperty<*>): T {
        viewBinding?.let {
            return it
        }
        viewBinding = inflateViewBinding(thisRef.layoutInflater, clazz)
        thisRef.setContentView(viewBinding?.root)

        return viewBinding as T
    }

    fun <T: ViewBinding> inflateViewBinding(layoutInflater: LayoutInflater, clazz: KClass<T>): T {
        val inflater = clazz.java.getMethod("inflate", LayoutInflater::class.java)
        return inflater.invoke(null, layoutInflater) as T
    }

}