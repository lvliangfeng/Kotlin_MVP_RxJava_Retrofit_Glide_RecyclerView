package com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.net.exception

/**
 * @Author lf_lv
 * @date  2018/10/22
 * Description:
 */
class ApiException : RuntimeException {

    private var code: Int? = null

    constructor(throwable: Throwable, code: Int) : super(throwable) {
        this.code = code
    }

    constructor(message: String) : super(Throwable(message))
}