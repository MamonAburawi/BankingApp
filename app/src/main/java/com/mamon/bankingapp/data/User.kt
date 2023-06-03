package com.mamon.bankingapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String = "",
    val name: String = "",
    val country: String = "",
    val city: String = "",
    var address: String = "",
    var phone: Int = 0,
    val password: String = "",
    var balance: Float = 0f
): Parcelable
