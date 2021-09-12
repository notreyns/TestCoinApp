package com.example.rei.testcoinapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rei.testcoinapp.databinding.CoinsFragmentBinding
import com.example.rei.testcoinapp.view.adapter.CoinsAdapter
import com.example.rei.testcoinapp.view.base.BaseFragment
import com.example.rei.testcoinapp.view.helper.viewModels.LoadingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinsFragment : BaseFragment() {

    private val viewModel: CoinsViewModel by viewModels()
    private lateinit var binding: CoinsFragmentBinding
    private lateinit var coinListAdapter: CoinsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CoinsFragmentBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        injectLoadingView()
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViewAdapter()
        setupLiveData()

        coinListAdapter.setOnItemClickListener {
            viewModel.onCoinClick(it.website)
        }
    }

    override fun provideLoadingViewModel(): LoadingViewModel? {
        return viewModel
    }

    private fun setupLiveData() {
        viewModel.coinsLiveData.observe(viewLifecycleOwner) {
            it?.let { response ->
                coinListAdapter.setData(response.coins.toList())
            }
        }
    }


    private fun setupRecyclerViewAdapter() {
        coinListAdapter = CoinsAdapter()
        binding.coinRecyclerView.apply {
            adapter = coinListAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}