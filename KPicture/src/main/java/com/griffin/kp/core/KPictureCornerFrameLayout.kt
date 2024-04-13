package com.griffin.kp.core

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.widget.FrameLayout
import com.griffin.kp.R

/**
 * 只给底部添加圆角的 FrameLayout
 */
internal class KPictureCornerFrameLayout(context: Context, attrs: AttributeSet?) :
    FrameLayout(context, attrs) {

    private var cornerRadius: Float = 0f
    private val rect = RectF()
    private val path by lazy {
        Path()
    }

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.KPictureCornerFrameLayout)
        cornerRadius = a.getDimension(R.styleable.KPictureCornerFrameLayout_corner_radius, 0f)
        a.recycle()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        path.reset()
        rect.right = w.toFloat()
        rect.bottom = h.toFloat()
        val cornerRadii = floatArrayOf(
            0f, 0f,
            0f, 0f,
            cornerRadius, cornerRadius,
            cornerRadius, cornerRadius
        )
        path.addRoundRect(rect, cornerRadii, Path.Direction.CW)
    }

    override fun dispatchDraw(canvas: Canvas) {
        canvas.save();
        canvas.clipPath(path);
        super.dispatchDraw(canvas)
        canvas.restore();
    }

}