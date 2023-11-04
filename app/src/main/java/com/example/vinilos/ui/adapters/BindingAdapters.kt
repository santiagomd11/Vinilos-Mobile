package com.example.vinilos.ui.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    Glide.with(this.context)
        .load(url)
        .into(this)
}

@BindingAdapter("formattedDate")
fun TextView.setFormattedDate(dateString: String?) {
    dateString?.let {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        try {
            val date = inputFormat.parse(it)
            text = date?.let { d -> outputFormat.format(d) } ?: "Unknown Date"
        } catch (e: Exception) {
            text = "Invalid Date"
        }
    }
}