package com.mamon.bankingapp.screens.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mamon.bankingapp.data.Transaction
import com.mamon.bankingapp.databinding.ItemTransactionBinding

class TransactionsAdapter: ListAdapter<Transaction, TransactionsAdapter.CategoryViewHolder>(NoteDiffUtil()) {

    lateinit var listener: TransactionListener

    inner class CategoryViewHolder(private val binding: ItemTransactionBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(data: Transaction){
            binding.transaction = data
            binding.btnItem.setOnClickListener {
                listener.onClick(data)
            }

        }
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val note = getItem(position)!!
        holder.bind(note)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTransactionBinding.inflate(inflater,parent,false)
        return CategoryViewHolder(binding)
    }


    class NoteDiffUtil : DiffUtil.ItemCallback<Transaction>() {
        override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction) = oldItem == newItem
    }


    interface TransactionListener {
        fun onClick(data: Transaction)
    }


}
