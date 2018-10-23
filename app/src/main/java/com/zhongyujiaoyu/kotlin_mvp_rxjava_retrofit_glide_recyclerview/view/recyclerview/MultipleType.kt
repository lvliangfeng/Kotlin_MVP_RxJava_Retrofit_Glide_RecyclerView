package com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.view.recyclerview

/**
 * @Author lf_lv
 * @date  2018/10/23
 * Description:
 */
interface MultipleType<in T> {
    fun getLayoutId(item: T, position: Int): Int
}