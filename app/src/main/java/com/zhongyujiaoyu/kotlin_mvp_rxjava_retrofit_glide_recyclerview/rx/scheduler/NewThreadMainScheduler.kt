package com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.rx.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @Author lf_lv
 * @date  2018/10/22
 * Description:
 */
class NewThreadMainScheduler<T> :BaseScheduler<T>(Schedulers.newThread(),AndroidSchedulers.mainThread()) {
}