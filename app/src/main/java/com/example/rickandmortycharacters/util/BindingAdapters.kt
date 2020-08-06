package com.example.rickandmortycharacters.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.rickandmortycharacters.R

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun ImageView.imageUrl(url: String) {
        Glide.with(this.context)
            .load(url)
            .error(R.drawable.ic_baseline_error_outline_200)
            .into(this)
    }
}