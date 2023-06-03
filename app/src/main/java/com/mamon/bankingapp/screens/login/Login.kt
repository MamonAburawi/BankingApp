package com.mamon.bankingapp.screens.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.mamon.bankingapp.R
import com.mamon.bankingapp.databinding.LoginBinding
import com.mamon.bankingapp.utils.Constant
import com.mamon.bankingapp.utils.navigateLoginToCreateAccount

class Login : Fragment() {


    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: LoginBinding
    private var isLogin = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = LoginBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        isLogin = arguments?.getBoolean(Constant.KEY_IS_LOGIN) == true

        setViews()


        return binding.root
    }

    private fun setViews() {
        binding.apply {

            updateBtn()

            btnCreateLoginAccount.setOnClickListener {
                if (isLogin){
                    findNavController().navigate(R.id.action_login_to_home)
                }else{
                    val email = email.text.toString()
                    val password = password.text.toString()
                    val data = bundleOf(Constant.KEY_EMAIL to email, Constant.KEY_PASSWORD to password)
//                    findNavController().navigate(R.id.action_login_to_createAccount,data)
                    navigateLoginToCreateAccount(data)
                }

            }

        }
    }

    private fun updateBtn(){
        if (isLogin){
            binding.btnCreateLoginAccount.text = getString(R.string.login)
        }else{
            binding.btnCreateLoginAccount.text = getString(R.string.create_account)
        }
    }


}