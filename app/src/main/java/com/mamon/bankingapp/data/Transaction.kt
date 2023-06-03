package com.mamon.bankingapp.data

import java.util.Date

data class Transaction(
    val id: String = "",
    val name: String = "",
    val date: Date = Date(),
    val ic: Int = 0,
    val price: Int = 0
)
