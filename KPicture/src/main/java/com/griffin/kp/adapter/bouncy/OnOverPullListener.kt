package com.griffin.kp.adapter.bouncy

/**
 * 用于监听过度拉动的监听器。
 */
interface OnOverPullListener {

    /**
     * 当从顶部过度拉动时调用。
     *
     * @param deltaDistance 拉动距离的增量
     */
    fun onOverPulledTop(deltaDistance: Float)

    /**
     * 当从底部过度拉动时调用。
     *
     * @param deltaDistance 拉动距离的增量
     */
    fun onOverPulledBottom(deltaDistance: Float)

    /**
     * 当释放拉动时调用。
     */
    fun onRelease()
}
