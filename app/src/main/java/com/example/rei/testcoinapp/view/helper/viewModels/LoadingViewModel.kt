package com.example.rei.testcoinapp.view.helper.viewModels

import android.content.Context
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import com.example.cointestapp.view.model.fragment.FragmentTransaction
import com.example.cointestapp.view.model.loading.LoadingViewParams
import com.example.rei.data.exceptions.factory.ErrorMessageFactory
import com.example.rei.domain.interactors.result.Result
import com.example.rei.testcoinapp.R
import com.example.rei.testcoinapp.view.helper.livedata.SingleLiveEvent
import com.example.rei.testcoinapp.view.model.toast.ToastDuration

abstract class LoadingViewModel : LifeCycleObserverViewModel() {

    lateinit var context : () -> Context
    lateinit var requiredArgument: () -> Bundle

    val showToast = SingleLiveEvent<Pair<String, ToastDuration>>()
    val navigateToFragment = SingleLiveEvent<FragmentTransaction>()
    val showLoadingView = SingleLiveEvent<LoadingViewParams>()

    fun showToast(message: String, duration: ToastDuration) {
        showToast.startEvent(Pair(message, duration))
    }

    fun showLoadingView(@StringRes message: Int = R.string.progress_bar_status) {
        showLoadingView.startEvent(LoadingViewParams(isVisible = true))
    }

    fun hideLoadingView() {
        showLoadingView.startEvent(LoadingViewParams(isVisible = false))
    }

    fun navigateToFragment(@IdRes navigationId: Int, bundle: Bundle? = null) {
        navigateToFragment.startEvent(FragmentTransaction(navigationId, bundle))
    }


    protected fun <T> handleResult(result: Result<T>, onSuccess: (T) -> Unit) {
        result.perform(onSuccess, { throwable ->
            onCoroutinesFailed(throwable)
        })
    }

    private fun onCoroutinesFailed(
        throwable: Throwable,
        defaultErrorMessage: String = "Ой, что-то пошло не так!"
    ) {
        throwable.printStackTrace()

        val errorMessage = ErrorMessageFactory.create(throwable)
        showToast(errorMessage ?: defaultErrorMessage, ToastDuration.LONG)
    }
}