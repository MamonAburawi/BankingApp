package com.mamon.bankingapp.screens.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mamon.bankingapp.R
import com.mamon.bankingapp.databinding.ProfileBinding
import com.mamon.bankingapp.utils.back
import com.mamon.bankingapp.utils.navigateProfileToCardSettings
import com.mamon.bankingapp.utils.showMessage

class Profile : Fragment() {

    private lateinit var viewModel: ProfileViewModel
    private val profileViewModel by activityViewModels<ProfileViewModel>()

    private lateinit var binding: ProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = ProfileBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        setViews()

        return binding.root
    }

    private fun setViews() {
        binding.apply {
            profileVM = profileViewModel
            lifecycleOwner = this@Profile
        }
        setButtons()
    }

    private fun setButtons() {
        binding.apply {
            btnInbox.setOnClickListener { showMessage("Inbox") }
            btnDocument.setOnClickListener {  showMessage("Document")}
            btnCardSettings.setOnClickListener { navigateProfileToCardSettings() }
            btnSettings.setOnClickListener { showMessage("Settings") }
            btnSecurity.setOnClickListener { showMessage("Security") }
            btnNotification.setOnClickListener { showMessage("Notification") }
            btnHelpCenter.setOnClickListener { showMessage("Help Center") }
            btnInfo.setOnClickListener { showMessage("Info") }
            btnBack.setOnClickListener { back() }
        }
    }


}