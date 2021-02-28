package io.github.agimaulana.monity.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import io.github.agimaulana.monity.viewmodel.DailyRationViewModel

abstract class BaseFragment: Fragment() {
    protected abstract val viewBinding: ViewBinding
    protected abstract val viewModel: ViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return viewBinding.root
    }
}