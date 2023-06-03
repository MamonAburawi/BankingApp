package com.mamon.bankingapp.screens.logo

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mamon.bankingapp.R
import com.mamon.bankingapp.databinding.LogoBinding
import com.mamon.bankingapp.utils.navigateLogoToLoginCreateAccount


class Logo : Fragment() {
    private lateinit var binding: LogoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = LogoBinding.inflate(inflater,container,false)


        object: CountDownTimer(3000L,1000L){
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                navigateLogoToLoginCreateAccount()
//                findNavController().navigate(R.id.action_logo_to_login_create_account)
            }

        }.start()



        return binding.root
    }

}