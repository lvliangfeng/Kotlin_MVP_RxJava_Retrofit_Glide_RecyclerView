package com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.rx.scheduler

/**
 * @Author lf_lv
 * @date  2018/10/22
 * Description:
 */
object SchedulerUtils {
    fun <T> ioToMain(): IoMainScheduler<T> {
        return IoMainScheduler()
    }
}