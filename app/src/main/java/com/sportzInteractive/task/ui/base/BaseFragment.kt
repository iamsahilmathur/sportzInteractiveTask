package com.sportzInteractive.task.ui.base
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.sportzInteractive.task.utils.autoCleaned

abstract class BaseFragment<V : ViewDataBinding, VM : ViewModel> : Fragment() {
    private var mBinding: V by autoCleaned()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        observeViewModel()
        return DataBindingUtil.inflate<V>(layoutInflater, getLayoutId(), container, false).also {
            it.setVariable(getBindingVariable(), getViewModel())
            mBinding = it
        }.root
    }
    @LayoutRes
    abstract fun getLayoutId(): Int
    fun getBinding(): V {
        return mBinding
    }
    abstract fun getViewModel(): VM
    abstract fun getBindingVariable(): Int
    abstract fun observeViewModel()
}