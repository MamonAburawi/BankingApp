package com.mamon.bankingapp.utils

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.widget.EditText
import com.mamon.bankingapp.databinding.KeyboardNumBinding

// this is custom keyboard class

@SuppressLint("ClickableViewAccessibility", "SuspiciousIndentation")
class KeyBoardNum {

    constructor(binding: KeyboardNumBinding, phoneInput: EditText) {
        phoneInput.disableKeyBoard()
        updateButtonListeners(binding, phoneInput)
    }

    constructor(binding: KeyboardNumBinding, vararg editTexts: EditText, action: () -> Unit) { // after fill all the OTP the action will be apply

         var focusedEditText: EditText? = null

            editTexts.forEach { editText ->

                editText.disableKeyBoard()
                val watcher = GenericTextWatcher(editText, editTexts) { action() }
                editText.addTextChangedListener(watcher)
                editText.setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) {
                        focusedEditText = editText
                        updateButtonListeners(binding, focusedEditText)
                    }
                }
            }
            focusedEditText = editTexts.firstOrNull()
        updateButtonListeners(binding,focusedEditText)

    }

    private fun updateButtonListeners(binding: KeyboardNumBinding, phoneInput: EditText?) {
        binding.apply {

            btn0.setOnClickListener {
                phoneInput?.append("0")
            }

            btn1.setOnClickListener {
                phoneInput?.append("1")
            }

            btn2.setOnClickListener {
                phoneInput?.append("2")
            }

            btn3.setOnClickListener {
                phoneInput?.append("3")
            }

            btn4.setOnClickListener {
                phoneInput?.append("4")
            }

            btn5.setOnClickListener {
                phoneInput?.append("5")
            }

            btn6.setOnClickListener {
                phoneInput?.append("6")
            }

            btn7.setOnClickListener {
                phoneInput?.append("7")
            }

            btn8.setOnClickListener {
                phoneInput?.append("8")
            }

            btn9.setOnClickListener {
                phoneInput?.append("9")
            }

            btnDelete.setOnClickListener {
                if (phoneInput?.text!!.isNotEmpty()) {
                    phoneInput.text?.delete(phoneInput.text.length - 1, phoneInput.text.length)
                }
            }
        }
    }


}

