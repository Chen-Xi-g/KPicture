package com.griffin.kp.animation

/**
 * 列表加载动画
 */
interface IListAnimation {

    /**
     * 列表加载动画
     *
     * @param position 当前位置
     */
    fun onListAnim(position: Int)
}