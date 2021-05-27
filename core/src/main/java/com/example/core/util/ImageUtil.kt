package com.example.core.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide

object ImageUtil {
    fun getDrawable(context: Context, id: Int): Drawable? {
        return ResourcesCompat.getDrawable(context.resources, id, null)
    }

    fun loadImage(
        imageUrl: String?,
        @DrawableRes defaultResourceId: Int,
        imageView: ImageView,
    ) {
        Glide.with(imageView.context)
            .load(imageUrl)
            .error(defaultResourceId)
            .fallback(defaultResourceId)
            .centerCrop()
            .into(imageView)
    }
}