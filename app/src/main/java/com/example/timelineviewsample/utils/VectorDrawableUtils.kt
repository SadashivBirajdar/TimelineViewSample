package com.example.timelineviewsample.utils

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.support.graphics.drawable.VectorDrawableCompat


/**
 * Created by Vipul Asri on 28/12/16.
 */

object VectorDrawableUtils {

    private fun getDrawable(context: Context, drawableResId: Int): Drawable? {
        return VectorDrawableCompat.create(context.resources, drawableResId, context.theme)
    }

    fun getDrawable(context: Context, drawableResId: Int, colorFilter: Int): Drawable {
        val drawable = getDrawable(context, drawableResId)
        drawable!!.setColorFilter(colorFilter, PorterDuff.Mode.SRC_IN)
        return drawable
    }

    fun getTextMarker(context: Context, mText: String): Drawable {
        val resources = context.resources
        val scale = resources.displayMetrics.density
        val bitmap = createBitmapImage()
        val canvas = Canvas(bitmap)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = Color.GRAY
        paint.textSize = (8 * scale)
        // draw text to the Canvas center
        val bounds = Rect()
        paint.getTextBounds(mText, 0, mText.length, bounds)
        val x = (bitmap.width - bounds.width()) / 4
        val y = (bitmap.height + bounds.height()) / 4
        canvas.drawText(mText, x * scale, y * scale, paint)
        return BitmapDrawable(resources, bitmap)
    }

    private fun createBitmapImage(): Bitmap {
        // The drawable to use for the circle
        val d = GradientDrawable()
        d.shape = GradientDrawable.OVAL
        d.setSize(25, 25)
        d.setColor(Color.TRANSPARENT)
        d.setStroke(2, Color.GRAY)
        val bitmap = Bitmap.createBitmap(d.intrinsicWidth, d.intrinsicHeight, Bitmap.Config.ARGB_8888)
        // Convert the drawable to bitmap
        val canvas = Canvas(bitmap)
        d.setBounds(0, 0, canvas.width, canvas.height)
        d.draw(canvas)
        return bitmap
    }
}
