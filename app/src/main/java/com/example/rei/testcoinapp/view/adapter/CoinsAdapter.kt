package com.example.rei.testcoinapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rei.domain.models.coins.CoinItemModel
import com.example.rei.testcoinapp.databinding.CoinItemBinding
import com.example.rei.testcoinapp.view.extension.loadImage

class CoinsAdapter : RecyclerView.Adapter<CoinsAdapter.CoinViewHolder>() {

    private val coinList = mutableListOf<CoinItemModel>()
    private var onItemClickListener: ((CoinItemModel) -> Unit)? = null

    fun setOnItemClickListener(listener: ((CoinItemModel) -> Unit)) {
        onItemClickListener = listener
    }

    inner class CoinViewHolder(
        private val binding: CoinItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: CoinItemModel) {
            with(binding) {
                icon.loadImage(itemView.context, model.icon)
                name.text = model.name
                rank.text = "Ранг: ${model.rank}"
                price.text = "Стоимость: ${model.price}$"
                symbol.text = model.symbol
            }
            itemView.setOnClickListener {
                onItemClickListener?.invoke(model)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding = CoinItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return coinList.size
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val model = coinList[position]
        holder.bind(model)
    }

    fun setData(list: List<CoinItemModel>) {
        this.coinList.clear()
        this.coinList.addAll(list)
        this.notifyDataSetChanged()
    }

}