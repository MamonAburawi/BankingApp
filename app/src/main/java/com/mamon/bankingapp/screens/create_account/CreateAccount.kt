package com.mamon.bankingapp.screens.create_account

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mamon.bankingapp.R
import com.mamon.bankingapp.data.User
import com.mamon.bankingapp.databinding.CreateAccountBinding
import com.mamon.bankingapp.screens.profile.ProfileViewModel
import com.mamon.bankingapp.utils.Constant
import com.mamon.bankingapp.utils.navigateCreateAccountToPhoneNumber
import com.mamon.bankingapp.utils.showMessage

class CreateAccount : Fragment() {

    private lateinit var binding: CreateAccountBinding
    private lateinit var viewModel: CreateAccountViewModel
    private val profileViewModel by activityViewModels<ProfileViewModel>()

    var mName = ""
    var mCountry = ""
    var mCity = ""
    var mPassword = ""
    var mEmail = ""
    var mAddress = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        viewModel = ViewModelProvider(this)[CreateAccountViewModel::class.java]
        binding = CreateAccountBinding.inflate(inflater,container,false)

        mEmail = arguments?.getString(Constant.KEY_EMAIL) as String
        mPassword = arguments?.getString(Constant.KEY_PASSWORD) as String


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
                findNavController().navigateUp()
            }


            /** button next **/
            btnNext.setOnClickListener {

                mName = name.text.trim().toString()
                mCountry = country.selectedItem.toString()
                mCity = city.selectedItem.toString()
                mAddress = address.text.trim().toString()


                if (mName.isEmpty()){
                    name.error = "required"
                    name.requestFocus()
                }

                if (mCountry.isEmpty()){
                    showMessage("country is required!")
                }

                if(mCity.isEmpty()){
                    showMessage("city is required!")
                }

                if (mAddress.isEmpty()){
                    address.error = "required"
                    address.requestFocus()
                }else{
                    val user = User("1",mName,mCountry,mCity,mAddress,0,mPassword,3200f)
                    val data = bundleOf(Constant.KEY_USER to user)
                    profileViewModel.updateUser(user)
                    Log.d("CreateAccount","user: ${user}")
//                    findNavController().navigate(R.id.action_createAccount_to_phoneNumber,data)
                    navigateCreateAccountToPhoneNumber(data)
                }

            }

            /** button need support **/
            btnNeedSupport.setOnClickListener {
                Toast.makeText(requireContext(),"support",Toast.LENGTH_SHORT).show()
            }
        }
    }


}