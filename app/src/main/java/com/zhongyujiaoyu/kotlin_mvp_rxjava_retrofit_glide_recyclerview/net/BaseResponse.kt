package com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.net

/**
 * @Author lf_lv
 * @date  2018/10/22
 * Description:
 */
class BaseResponse<T>(val code: Int, val msg: String, val data: T) {
}