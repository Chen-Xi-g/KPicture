package com.griffin.kp.core

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager.LayoutParams
import android.view.animation.Animation
import android.widget.PopupWindow
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.griffin.kp.KPicture
import com.griffin.kp.R
import com.griffin.kp.adapter.MediaFolderPopupAdapter
import com.griffin.kp.extend.IAlbumPopupConfig
import com.griffin.kp.extend.IMediaFolderAdapter
import com.griffin.kp.utils.asColor

class AlbumPopup(private val context: Context) : PopupWindow(context) {

    /**
     * 弹窗视图
     */
    private val popupView: View by lazy {
        LayoutInflater.from(context).inflate(R.layout.kp_popup_layout, null, false)
    }

    /**
     * 自定义相册列表属性
     */
    private val iMediaAdapter: IMediaFolderAdapter = KPicture.mediaFolderAdapter

    /**
     * 自定义Popup属性
     */
    private val iAlbumPopupConfig: IAlbumPopupConfig = KPicture.albumPopupConfig

    private val rootView by lazy {
        popupView.findViewById<RelativeLayout>(R.id.root_view)
    }

    private val rv by lazy {
        popupView.findViewById<RecyclerView>(R.id.kp_popup_recycler_view)
    }

    private val vClick by lazy {
        popupView.findViewById<View>(R.id.kp_v_click)
    }

    /**
     * 媒体列表Adapter
     */
    val adapter: MediaFolderPopupAdapter by lazy {
        MediaFolderPopupAdapter(iMediaAdapter)
    }

    private val screenHeight: Int by lazy {
        val displayMetrics = context.resources.displayMetrics
        displayMetrics.heightPixels
    }

    private val maxHeight: Int by lazy {
        (screenHeight * iAlbumPopupConfig.popupMaxHeightRatio()).toInt()
    }

    private var dismissListener: (() -> Unit)? = null

    init {
        contentView = popupView
        rv.layoutManager = iMediaAdapter.getLayoutManager(context)
        rv.adapter = adapter
        rv.post {
            val layoutParams = rv.layoutParams
            layoutParams.height =
                if (rv.measuredHeight < maxHeight) rv.measuredHeight else maxHeight
            rv.layoutParams = layoutParams
        }
        initPopupWindow()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initPopupWindow() {
        isFocusable = true
        isOutsideTouchable = true
        width = LayoutParams.MATCH_PARENT
        height = LayoutParams.WRAP_CONTENT
        animationStyle = iAlbumPopupConfig.popupAnimationStyle()
        rv.background = iAlbumPopupConfig.popupFolderBackgroundColor()
        setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        vClick.setOnClickListener {
            dismiss()
        }
    }

    /**
     * 显示RecyclerView的动画
     */
    private fun showRvWithAnimation() {
        rv.visibility = View.VISIBLE
        rv.startAnimation(iAlbumPopupConfig.showFolderAnimation())
    }

    /**
     * 隐藏 RecyclerView 的动画
     */
    private fun hideRvAnim() {
        val translateAnimation = iAlbumPopupConfig.hideFolderAnimation()
        translateAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                // 动画开始时执行的操作
            }

            override fun onAnimationEnd(animation: Animation?) {
                // 动画结束时执行的操作
                rv.visibility = View.GONE
                dismissListener?.invoke()
            }

            override fun onAnimationRepeat(animation: Animation?) {
                // 动画重复时执行的操作
            }
        })
        rv.startAnimation(translateAnimation) // 启动动画
    }

    override fun showAsDropDown(anchor: View?) {
        super.showAsDropDown(anchor)
        rootView.post {
            rootView.setBackgroundColor(context.asColor(iAlbumPopupConfig.popupBackgroundColor()))
            showRvWithAnimation()
        }
    }

    override fun dismiss() {
        dismissListener = {
            super.dismiss()
        }
        rootView.post {
            rootView.setBackgroundColor(Color.TRANSPARENT)
            hideRvAnim()
        }
    }

}