package com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.mvp.presenter

import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.base.BasePresenter
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.mvp.contract.ImageContract
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.mvp.model.ImageModel
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.mvp.model.bean.parser.ImageListParser
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * @Author lf_lv
 * @date  2018/10/22
 * Description:
 */
class ImagePresenter : BasePresenter<ImageContract.View>(), ImageContract.Presenter {

    private val imageModel by lazy {
        ImageModel()
    }

    override fun getImageList(url: String) {
        checkViewAttached()
        val disponsable = imageModel.getImageList(url)
//                .subscribe(object :Observer<ImageListParser> {
//                    override fun onComplete() {
//                    }
//                    override fun onSubscribe(d: Disposable) {
//                    }
//                    override fun onNext(t: ImageListParser) {
//                    }
//                    override fun onError(e: Throwable) {
//                    }
//                })
                .subscribe({ issue ->
                    mRootView?.apply {
                        val code = issue.code
                        if ("200" == code && null != issue.result) {
                            showIamgeData(issue.result)
                        }
                    }
                }, { throwable ->
                    mRootView?.apply {
                        showError(throwable.toString())
                    }
                })
        addSubscription(disponsable)
    }
}