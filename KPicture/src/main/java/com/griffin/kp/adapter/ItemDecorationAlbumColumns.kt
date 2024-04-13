package com.griffin.kp.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * 自定义 RecyclerView 的 ItemDecoration，用于在网格布局中添加间距和偏移量。
 *
 * @param gridSpacingPx 网格间距（单位：像素）
 * @param columns 列数
 */
class ItemDecorationAlbumColumns(private val gridSpacingPx: Int, private val columns: Int) : RecyclerView.ItemDecoration() {

    // 是否需要左侧间距
    private var needLeftSpacing = false

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        // 计算网格框的宽度
        val frameWidth = (parent.width.toFloat() - (gridSpacingPx.toFloat() * (columns - 1))) / columns
        // 计算每个项目的内边距
        val padding = parent.width / columns - frameWidth.toInt()
        // 获取当前项目的位置
        val itemPosition = (view.layoutParams as RecyclerView.LayoutParams).viewAdapterPosition

        // 根据位置设置上边距
        if (itemPosition < columns) {
            outRect.top = 0
        } else {
            outRect.top = gridSpacingPx
        }

        // 根据位置设置左边距和右边距
        if (itemPosition % columns == 0) {
            outRect.left = 0
            outRect.right = padding
            needLeftSpacing = true
        } else if ((itemPosition + 1) % columns == 0) {
            needLeftSpacing = false
            outRect.right = 0
            outRect.left = padding
        } else if (needLeftSpacing) {
            needLeftSpacing = false
            outRect.left = gridSpacingPx - padding
            outRect.right = if ((itemPosition + 2) % columns == 0) {
                gridSpacingPx - padding
            } else {
                gridSpacingPx / 2
            }
        } else if ((itemPosition + 2) % columns == 0) {
            needLeftSpacing = false
            outRect.left = gridSpacingPx / 2
            outRect.right = gridSpacingPx - padding
        } else {
            needLeftSpacing = false
            outRect.left = gridSpacingPx / 2
            outRect.right = gridSpacingPx / 2
        }

        outRect.bottom = 0
    }
}