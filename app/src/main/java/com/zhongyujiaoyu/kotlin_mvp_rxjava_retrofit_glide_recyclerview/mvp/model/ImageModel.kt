package com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.mvp.model

import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.mvp.model.bean.parser.ImageListParser
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.net.RetrofitManager
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * @Author lf_lv
 * @date  2018/10/22
 * Description:
 */
class ImageModel {

    //下载图片
    fun getImageList(url: String): Observable<ImageListParser> {
        return RetrofitManager.service.getImageList(url)
                .compose(SchedulerUtils.ioToMain())
    }
}