package com.mamon.bankingapp.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mamon.bankingapp.databinding.TransactionsBottomSheetBinding

class TransactionsBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: TransactionsBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View{
        binding = TransactionsBottomSheetBinding.inflate(inflater,container,false)


        setViews()


        return binding.root
    }

    private fun setViews() {
        binding.apply {

        }
    }


}
