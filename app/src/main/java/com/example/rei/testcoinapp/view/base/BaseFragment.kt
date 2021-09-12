package com.example.rei.testcoinapp.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.cointestapp.view.model.fragment.FragmentTransaction
import com.example.rei.testcoinapp.R
import com.example.rei.testcoinapp.view.helper.viewModels.LoadingViewModel
import com.example.rei.testcoinapp.view.model.toast.ToastDuration

abstract class BaseFragment : Fragment() {

    private var loadingViewLayout: ConstraintLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupLoadingViewModel()
        provideLoadingViewModel()?.let { observableViewModel ->
            lifecycle.addObserver(observableViewModel)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeLoadingView()
    }

    protected fun showToast(message: String, duration: ToastDuration) {
        Toast.makeText(requireContext(), message, duration.value).show()
    }

    protected fun navigateToFragment(fragmentTransaction: FragmentTransaction) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).navigateToFragment(fragmentTransaction)
        }
    }

    protected fun injectLoadingView(@LayoutRes layoutId: Int = R.layout.loading_view_layout) {
        val view = LayoutInflater.from(context).inflate(layoutId, (view as ViewGroup))

        loadingViewLayout = view.findViewById(R.id.progress_overlay)
    }

    private fun initializeLoadingView() {
        loadingViewLayout = view?.findViewById(R.id.progress_overlay)
    }

    private fun setupLoadingViewModel() {
        provideLoadingViewModel()?.let {
            it.context = {
                requireContext()
            }

            it.requiredArgument = {
                requireArguments()
            }

            it.showToast.observe(this) {
                showToast(it.first, it.second)
            }

            it.showLoadingView.observe(this) {
                when (it.isVisible) {
                    true -> showLoading()
                    false -> hideLoading()
                }
            }

            it.navigateToFragment.observe(this) {
                navigateToFragment(it)
            }
        }
    }

    private fun showLoading() {
        loadingViewLayout?.let { loadingView ->
            loadingView.visibility = View.VISIBLE
        }
    }

    private fun hideLoading() {
        loadingViewLayout?.let { loadingView ->
            loadingView.visibility = View.GONE
        }
    }

    abstract fun provideLoadingViewModel(): LoadingViewModel?
}