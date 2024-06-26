package com.example.moviecatalogue.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.core.util.getColorFromAttr
import com.example.moviecatalogue.R


class BasicSwipeRefreshLayout : SwipeRefreshLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        setColorSchemeColors(context.getColorFromAttr(R.attr.colorPrimary))
    }
}