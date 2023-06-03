package com.mamon.bankingapp.screens.otp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mamon.bankingapp.R
import com.mamon.bankingapp.databinding.OtpBinding
import com.mamon.bankingapp.utils.KeyBoardNum
import com.mamon.bankingapp.utils.back
import com.mamon.bankingapp.utils.navigateOtpToUploadDocument
import com.mamon.bankingapp.utils.showMessage

class Otp : Fragment() {

    private lateinit var viewModel: OtpViewModel
    private lateinit var binding: OtpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = OtpBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this)[OtpViewModel::class.java]


        setViews()

        return binding.root
    }

    private fun setViews() {
        binding.apply {


            setUpCustomKeyBoard()

            /** button back **/
            btnBack.setOnClickListener {
                back()
//                findNavController().navigateUp()
            }

            btnResetCode.setOnClickListener {
                showMessage("reset code")
            }

        }
    }



    private fun setUpCustomKeyBoard(){
        KeyBoardNum(binding.keyboardNumLayout,
            binding.otpOne,
            binding.otpTwo,
            binding.otpThree,
            binding.otpFour){
            /**
            after all otp filled do something
             **/

            navigateOtpToUploadDocument()
//            navigateToUploadDocument()
        }
    }


    private fun navigateToUploadDocument(){
        findNavController().navigate(R.id.action_otp_to_uploadDocument)
    }




}