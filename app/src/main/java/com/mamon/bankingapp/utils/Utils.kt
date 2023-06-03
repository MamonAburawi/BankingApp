package com.mamon.bankingapp.utils

import android.widget.EditText
import androidx.fragment.app.Fragment
import com.mamon.bankingapp.R
import com.mamon.bankingapp.data.Transaction
import com.mamon.bankingapp.data.User
import java.util.*

val daysList = listOf("MON", "TUE", "WED", "THU", "FRI", "SAT","SUN")


// fake date
fun transactionsData(): List<Transaction> {
    return listOf(
        Transaction("", "SubWay", Calendar.getInstance().time, R.drawable.ic_train,50),
        Transaction("", "Restaurant", Calendar.getInstance().time, R.drawable.ic_resturant,15),
        Transaction("", "Coffee", Calendar.getInstance().time, R.drawable.ic_coffee,4),
        Transaction("", "Shopping", Calendar.getInstance().time, R.drawable.ic_shop,9),
        Transaction("", "FOOD", Calendar.getInstance().time, R.drawable.ic_fast_food,23),
        Transaction("", "SubWay", Calendar.getInstance().time, R.drawable.ic_train,50),
        Transaction("", "Restaurant", Calendar.getInstance().time, R.drawable.ic_resturant,150),
        Transaction("", "Coffee", Calendar.getInstance().time, R.drawable.ic_coffee,20),
        Transaction("", "Shopping", Calendar.getInstance().time, R.drawable.ic_shop,80),
        Transaction("", "FOOD", Calendar.getInstance().time, R.drawable.ic_fast_food,17)
    ).shuffled()
}


fun EditText.showErrorIfEmpty(): String {
    var v = text.trim().toString()
    if (v.isEmpty()){
        error = "required"
        requestFocus()
        v = ""
    }
    return v
}


object Constant {
    const val KEY_USER = "user"
    const val KEY_IS_LOGIN = "isLogin"
    const val KEY_EMAIL = "email"
    const val KEY_PASSWORD = "password"
}


fun Fragment.getUserData() = try { arguments?.get(Constant.KEY_USER) as User } catch (ex: Exception){ null }



