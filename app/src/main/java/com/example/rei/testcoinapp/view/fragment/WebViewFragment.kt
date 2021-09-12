package com.example.rei.testcoinapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import com.example.rei.testcoinapp.databinding.WebviewFragmentBinding
import com.example.rei.testcoinapp.view.base.BaseFragment
import com.example.rei.testcoinapp.view.helper.viewModels.LoadingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewFragment : BaseFragment() {

    private val viewModel: WebViewViewModel by viewModels()
    private lateinit var binding: WebviewFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WebviewFragmentBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLiveData()
    }

    override fun provideLoadingViewModel(): LoadingViewModel? {
        return viewModel
    }

    private fun setupLiveData() {
        viewModel.urlLiveData.observe(viewLifecycleOwner) {
            binding.webView.apply {
                webViewClient = WebViewClient()
                loadUrl(it)
            }
        }
    }
}