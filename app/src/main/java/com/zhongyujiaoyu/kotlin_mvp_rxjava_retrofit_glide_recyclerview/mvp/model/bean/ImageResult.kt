package com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.mvp.model.bean

import java.io.Serializable

/**
 * @Author lf_lv
 * @date  2018/10/22
 * Description: 网络请求图片结果实例
 */
data class ImageResult(val name: String, val url: String, val desc: String, val extendOne: String) : Serializable {
    override fun toString(): String {
        return "ImageResult(name='$name', url='$url', desc='$desc', extendOne='$extendOne'"
    }
}