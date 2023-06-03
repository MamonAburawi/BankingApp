package com.mamon.bankingapp.utils

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter("setDateFormat")
fun setDateFormat(tv: TextView, date: Date) {
    val nowTime = Calendar.getInstance()
    val neededTime = Calendar.getInstance()
    neededTime.timeInMillis = date.time

   tv.text = if (neededTime[Calendar.YEAR] == nowTime[Calendar.YEAR]) {
        if (neededTime[Calendar.MONTH] == nowTime[Calendar.MONTH]) {
            when {
                neededTime[Calendar.DATE] - nowTime[Calendar.DATE] == 1 -> {
                    //here return like "Tomorrow 12:00"
                    "Tomorrow " +  SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
                }
                nowTime[Calendar.DATE] == neededTime[Calendar.DATE] -> {
                    //here return like "Today 12:00"
                    "Today " +  SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
                }
                nowTime[Calendar.DATE] - neededTime[Calendar.DATE] == 1 -> {
                    //here return like "Yesterday 12:00"
                    "Yesterday " +  SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
                }
                else -> {
                    //here return like "May 31, 12:00"
                    SimpleDateFormat("MMMM d, HH:mm", Locale.getDefault()).format(Date())
                }
            }
        } else {
            //here return like "May 31, 12:00"
            SimpleDateFormat("MMMM d, HH:mm", Locale.getDefault()).format(Date())
        }
    } else {
        //here return like "May 31 2022, 12:00" - it's a different year we need to show it
        SimpleDateFormat("MMMM dd yyyy, HH:mm", Locale.getDefault()).format(Date())
    }
}


@SuppressLint("SetTextI18n")
@BindingAdapter("setPrice")
fun setPrice(tv: TextView, v: Int){
    tv.text = "-${v.toFloat()} €"
}

@BindingAdapter("setIcon")
fun setIcon(image: ImageView, ic: Int){
    image.setImageResource(ic)
}