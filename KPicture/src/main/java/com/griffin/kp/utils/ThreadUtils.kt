package com.griffin.kp.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * 在子线程执行耗时任务的扩展函数
 *
 * @param dispatcher 线程调度器，默认为IO线程
 * @param block 任务
 */
fun runOnBackgroundThread(dispatcher: CoroutineDispatcher = Dispatchers.IO, block: () -> Unit) {
    val coroutineScope = CoroutineScope(dispatcher)
    coroutineScope.launch {
        block.invoke()
    }
}

/**
 * 在子线程执行耗时任务的扩展函数，并且在主线程回调
 *
 * @param dispatcher 线程调度器，默认为IO线程
 * @param callback 回调函数
 */
fun <T> (() -> T).runOnBackgroundThread(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    callback: (T) -> Unit
) {
    val coroutineScope = CoroutineScope(dispatcher)
    coroutineScope.launch {
        val result = this@runOnBackgroundThread.invoke()
        withContext(Dispatchers.Main) {
            callback(result)
        }
    }
}

/**
 * 在主线程执行任务的扩展函数
 */
fun runOnUIThread(block: () -> Unit) {
    val coroutineScope = CoroutineScope(Dispatchers.Main)
    coroutineScope.launch {
        block.invoke()
    }
}