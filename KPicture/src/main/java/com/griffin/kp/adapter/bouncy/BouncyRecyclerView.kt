package com.griffin.kp.adapter.bouncy

import android.content.Context
import android.graphics.Canvas
import android.view.MotionEvent
import android.widget.EdgeEffect
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * 自定义的具有弹性效果的 RecyclerView。
 */
class BouncyRecyclerView(context: Context) : RecyclerView(context) {

    // 用于监听过度拉动的监听器
    var onOverPullListener: OnOverPullListener? = null

    // 过度拉动动画的大小
    var overscrollAnimationSize = 0.5f

    // 抛动动画的大小
    var flingAnimationSize = 0.5f

    // 布局方向
    var orientation: Int? = 1
        set(value) {
            field = value
            setupDirection(value)
        }

    // 弹簧动画的阻尼比
    var dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
        set(value) {
            field = value
            updateSpring()
        }

    // 弹簧动画的刚度
    var stiffness = SpringForce.STIFFNESS_LOW
        set(value) {
            field = value
            updateSpring()
        }

    // 弹簧动画
    var spring: SpringAnimation = SpringAnimation(this, SpringAnimation.TRANSLATION_Y)
        .setSpring(SpringForce()
            .setFinalPosition(0f)
            .setDampingRatio(dampingRatio)
            .setStiffness(stiffness)
        )

    // 是否触摸
    var touched: Boolean = false

    init {
        // 创建边缘效果
        this.edgeEffectFactory = object : EdgeEffectFactory() {
            override fun createEdgeEffect(recyclerView: RecyclerView, direction: Int): EdgeEffect {
                return object : EdgeEffect(recyclerView.context) {
                    override fun onPull(deltaDistance: Float) {
                        super.onPull(deltaDistance)
                        onPullAnimation(deltaDistance)
                    }

                    override fun onPull(deltaDistance: Float, displacement: Float) {
                        super.onPull(deltaDistance, displacement)
                        onPullAnimation(deltaDistance)
                        if (direction == DIRECTION_BOTTOM)
                            onOverPullListener?.onOverPulledBottom(deltaDistance)
                        else if (direction == DIRECTION_TOP)
                            onOverPullListener?.onOverPulledTop(deltaDistance)
                    }

                    private fun onPullAnimation(deltaDistance: Float) {
                        val delta: Float = if (direction == DIRECTION_BOTTOM || direction == DIRECTION_RIGHT)
                            -1 * recyclerView.width * deltaDistance * overscrollAnimationSize
                        else
                            1 * recyclerView.width * deltaDistance * overscrollAnimationSize

                        if (orientation == VERTICAL) {
                            translationY += delta
                        } else {
                            translationX += delta
                        }
                        spring.cancel()
                    }

                    override fun onRelease() {
                        super.onRelease()

                        if (touched) return

                        onOverPullListener?.onRelease()
                        spring.start()
                    }

                    override fun onAbsorb(velocity: Int) {
                        super.onAbsorb(velocity)

                        val v: Float = if (orientation == VERTICAL)
                            if (direction == DIRECTION_BOTTOM) -1 * velocity * flingAnimationSize else 1 * velocity * flingAnimationSize
                        else
                            if (direction == DIRECTION_RIGHT) -1 * velocity * flingAnimationSize else 1 * velocity * flingAnimationSize

                        spring.setStartVelocity(v).start()
                    }

                    override fun draw(canvas: Canvas?): Boolean {
                        setSize(0, 0)
                        return super.draw(canvas)
                    }
                }
            }
        }
    }

    /**
     * 更新 Spring 动画参数。
     */
    private fun updateSpring() {
        this.spring.spring = SpringForce()
            .setFinalPosition(0f)
            .setDampingRatio(dampingRatio)
            .setStiffness(stiffness)
    }

    override fun onTouchEvent(e: MotionEvent?): Boolean {
        touched = when (e?.actionMasked) {
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> false
            else -> true
        }
        return super.onTouchEvent(e)
    }

    override fun setLayoutManager(layout: LayoutManager?) {
        super.setLayoutManager(layout)
        if (layout is LinearLayoutManager) {
            orientation = layout.orientation
            setupDirection(orientation)
        }
    }

    /**
     * 设置弹簧动画的方向。
     */
    private fun setupDirection(orientation: Int?) {
        if (stiffness > 0) {
            when (orientation) {
                HORIZONTAL -> spring = SpringAnimation(this, SpringAnimation.TRANSLATION_X)
                    .setSpring(SpringForce()
                        .setFinalPosition(0f)
                        .setDampingRatio(dampingRatio)
                        .setStiffness(stiffness))

                VERTICAL -> spring = SpringAnimation(this, SpringAnimation.TRANSLATION_Y)
                    .setSpring(SpringForce()
                        .setFinalPosition(0f)
                        .setDampingRatio(dampingRatio)
                        .setStiffness(stiffness))
            }
        }
    }

}
