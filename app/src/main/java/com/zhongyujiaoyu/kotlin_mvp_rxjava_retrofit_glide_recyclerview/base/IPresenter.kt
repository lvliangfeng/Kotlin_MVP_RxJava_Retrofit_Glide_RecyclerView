package com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.base

/**
 * @Author lf_lv
 * @date  2018/10/22
 * Description:
 */
interface IPresenter<in V : IBaseView> {
    fun attachView(mRootView: V)

    fun deleteView()
}