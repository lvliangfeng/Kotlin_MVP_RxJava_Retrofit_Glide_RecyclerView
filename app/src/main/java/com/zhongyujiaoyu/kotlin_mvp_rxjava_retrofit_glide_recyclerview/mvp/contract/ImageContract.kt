package com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.mvp.contract

import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.base.IBaseView
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.base.IPresenter
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.mvp.model.bean.ImageResult

/**
 * @Author lf_lv
 * @date  2018/10/22
 * Description:
 */
interface ImageContract {

    interface View : IBaseView {
        //显示错误信息
        fun showError(errorMsg: String)
        //显示图片
        fun showIamgeData(imageSet: HashSet<ImageResult>)
    }

    interface Presenter : IPresenter<View> {
        //获取图片
        fun getImageList(url: String)
    }
}