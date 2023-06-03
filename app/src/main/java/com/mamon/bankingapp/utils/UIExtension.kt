package com.mamon.bankingapp.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.mamon.bankingapp.R
import com.tapadoo.alerter.Alerter


fun Fragment.showMessage(message: String){
    Snackbar.make(this.requireView(), message, Snackbar.LENGTH_SHORT).show()
}

fun Activity.setColorStatusBar(color: Int){
    window.statusBarColor = ContextCompat.getColor(this, color)
}


@SuppressLint("ClickableViewAccessibility")
fun EditText.disableKeyBoard(){
    setOnTouchListener { _, event ->
        if (event.actionMasked == MotionEvent.ACTION_UP) {

            // Disable the keyboard
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(windowToken, 0)

            // Show the cursor
            showCursor()
        }
        true
    }
}


@SuppressLint("SuspiciousIndentation")
fun Activity.showAlerter(title: String, description: String, ic: Int? = null){
    val alerter = Alerter.create(this)
        .setTitle(title)
        .setText(description)
        .setBackgroundColorRes(R.color.color_bg_screen)
        if (ic != null) alerter.setIcon(ic)
    alerter.show()
}

fun Fragment.showAlerter(title: String,description: String,ic:Int?){
    requireActivity().showAlerter(title,description,ic)
}

private fun EditText.showCursor(){
    isFocusable = true
    isFocusableInTouchMode = true
    requestFocus()
    setSelection(text.length)
}

fun checkAllNotEmpty(vararg et: EditText): Boolean{
    var x = true
    et.forEach {
        if (it.text.isEmpty()){
            x = false
        }
    }
    return x
}

class GenericTextWatcher(
    private val view: View,
    private val editText: Array<out EditText>,
    private val action: () -> Unit
) : TextWatcher {

    override fun afterTextChanged(editable: Editable) {
        val text = editable.toString()
        when (view.id) {
            R.id.otp_one -> if (text.length == 1) editText[1].requestFocus()
            R.id.otp_two -> if (text.length == 1) editText[2].requestFocus() else if (text.isEmpty()) editText[0].requestFocus()
            R.id.otp_three -> if (text.length == 1) editText[3].requestFocus() else if (text.isEmpty()) editText[1].requestFocus()
            R.id.otp_four -> if (text.isEmpty()) editText[2].requestFocus()
        }

        if (editText[0].text.isNotEmpty() && editText[1].text.isNotEmpty() &&editText[2].text.isNotEmpty() &&editText[3].text.isNotEmpty()){
            removeWatcher()
        }
    }

    override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
    override fun onTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}

    private fun removeWatcher() {
        for (et in editText) {
            et.removeTextChangedListener(this)
        }
        action()
    }

}

fun Fragment.back(){ findNavController().navigateUp() }

fun Fragment.navigateLogoToLoginCreateAccount(){
    findNavController().navigate(R.id.action_logo_to_login_create_account)
}

fun Fragment.navigateHomeToProfile(){
    findNavController().navigate(R.id.action_home_to_profile)
}

fun Fragment.navigateProfileToCardSettings(){
    findNavController().navigate(R.id.action_profile_to_cardSettings)
}

fun Fragment.navigateLoginCreateAccountToLogin(data: Bundle){
    findNavController().navigate(R.id.action_login_create_account_to_login,data)
}

fun Fragment.navigateCardSettingsToResetPin(){
    findNavController().navigate(R.id.action_cardSettings_to_resetPin)
}

fun Fragment.navigateUploadDocumentToHome(){
    findNavController().navigate(R.id.action_uploadDocument_to_home)
}

fun Fragment.navigateCreateAccountToPhoneNumber(data: Bundle) {
    findNavController().navigate(R.id.action_createAccount_to_phoneNumber,data)
}

fun Fragment.navigatePhoneNumberToOtp(data: Bundle) {
    findNavController().navigate(R.id.action_phoneNumber_to_otp,data)
}

fun Fragment.navigateOtpToUploadDocument(){
    findNavController().navigate(R.id.action_otp_to_uploadDocument)
}

fun Fragment.navigateLoginToCreateAccount(data: Bundle) {
    findNavController().navigate(R.id.action_login_to_createAccount,data)
}