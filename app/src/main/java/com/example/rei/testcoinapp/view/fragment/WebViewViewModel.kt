package com.example.rei.testcoinapp.view.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rei.testcoinapp.view.helper.viewModels.LoadingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WebViewViewModel @Inject constructor() : LoadingViewModel() {

    private val _urlLiveData = MutableLiveData<String>()
    val urlLiveData: LiveData<String> = _urlLiveData

    override fun onCreate() {
        super.onCreate()
        val arguments = WebViewFragmentArgs.fromBundle(requiredArgument())
        _urlLiveData.postValue(arguments.url)
    }

}