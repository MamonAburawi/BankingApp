package com.mamon.bankingapp.screens.login_create_account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.mamon.bankingapp.R
import com.mamon.bankingapp.databinding.LoginCreateAccountBinding
import com.mamon.bankingapp.utils.Constant
import com.mamon.bankingapp.utils.navigateLoginCreateAccountToLogin

class LoginCreateAccount : Fragment() {

    private lateinit var binding: LoginCreateAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = LoginCreateAccountBinding.inflate(inflater,container,false)


        setViews()


        return binding.root
    }

    private fun setViews() {

        setButtons()
    }

    private fun setButtons() {
        binding.apply {

            btnCreateAccount.setOnClickListener {
                val data = bundleOf(Constant.KEY_IS_LOGIN to false)
                navigateLoginCreateAccountToLogin(data)
//                navigateToCreateAccount()
            }

            btnLogin.setOnClickListener {
                val data = bundleOf(Constant.KEY_IS_LOGIN to true)
                navigateLoginCreateAccountToLogin(data)
//                navigateToLogin()
            }

        }
    }

    private fun navigateToCreateAccount(){
        val data = bundleOf(Constant.KEY_IS_LOGIN to false)
        findNavController().navigate(R.id.action_login_create_account_to_login, data)
    }

    private fun navigateToLogin(){
        val data = bundleOf(Constant.KEY_IS_LOGIN to true)
        findNavController().navigate(R.id.action_login_create_account_to_login, data)
    }


}