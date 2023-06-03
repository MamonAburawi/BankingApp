package com.mamon.bankingapp.screens.phone_number

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.mamon.bankingapp.R
import com.mamon.bankingapp.data.User
import com.mamon.bankingapp.databinding.PhoneNumberBinding
import com.mamon.bankingapp.utils.*

class PhoneNumber : Fragment() {

    private lateinit var viewModel: PhoneNumberViewModel
    private lateinit var binding: PhoneNumberBinding
    var mUser: User? = null

    private lateinit var keyBoardNum: KeyBoardNum

    private var mPhone = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        binding = PhoneNumberBinding.inflate(inflater,container,false)
        keyBoardNum = KeyBoardNum(binding.keyboardNumLayout,binding.phone)

        mUser = getUserData() // get data from previous screen

        setViews()


        return binding.root
    }


    private fun setViews() {

        setButtons()
    }

    private fun setButtons() {
        binding.apply {

            /** button back **/
            btnBack.setOnClickListener {
               back()
            }

            /** button verify **/
            btnVerify.setOnClickListener {
                mPhone = try{ Integer.parseInt(phone.text.trim().toString()) }catch (ex: Exception){0}

                if (mPhone == 0){
                    phone.error = "required!"
                    phone.requestFocus()
                }else {
                    mUser?.phone = mPhone
                    Log.d("PhoneNumber","user: ${mUser}")
                    val data = bundleOf(Constant.KEY_USER to mUser)
                    navigatePhoneNumberToOtp(data)
//                    findNavController().navigate(R.id.action_phoneNumber_to_otp,data)
                }

            }
        }
    }


}