package com.example.rei.testcoinapp.view.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rei.domain.interactors.usecase.coins.GetConsUseCase
import com.example.rei.domain.models.coins.CoinsModel
import com.example.rei.testcoinapp.R
import com.example.rei.testcoinapp.view.helper.viewModels.LoadingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoinsViewModel @Inject constructor(
    private val getConsUseCase: GetConsUseCase
) : LoadingViewModel() {

    private val _coinsLiveData = MutableLiveData<CoinsModel>()
    val coinsLiveData: LiveData<CoinsModel> = _coinsLiveData

    override fun onResume() {
        super.onCreate()
        getCoins()
    }

    fun getCoins() {
        showLoadingView()
        getConsUseCase.execute(viewModelScope) {
            hideLoadingView()
            handleResult(it) { response ->
                _coinsLiveData.postValue(response)
            }
        }
    }


    fun onCoinClick(url: String?) {
        val argument = CoinsFragmentDirections.actionCoinsFragmentToWebViewFragment(
            url = url
        ).arguments
        navigateToFragment(navigationId = R.id.action_coinsFragment_to_webViewFragment, argument)
    }

}