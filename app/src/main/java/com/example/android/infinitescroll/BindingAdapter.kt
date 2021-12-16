package com.example.android.infinitescroll

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@SuppressLint("CheckResult")
@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        if (imgUrl.isNotEmpty()) {
            imageView.visibility = View.VISIBLE
            val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
            Glide.with(imageView.context)
                .load(imgUri)
                .apply(
                    RequestOptions
                        .circleCropTransform()
                        .placeholder(R.drawable.ic_baseline_child_care_24)
                        .error(R.drawable.ic_baseline_block_24)
                )
                .into(imageView)
        } else {
            imageView.visibility = View.GONE
        }
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
    }
}