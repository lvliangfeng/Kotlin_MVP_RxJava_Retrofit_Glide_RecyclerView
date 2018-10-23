package com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.api


import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.mvp.model.bean.parser.ImageListParser
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * @Author lf_lv
 * @date  2018/10/22
 * Description: 网络请求Retrofit需要的接口
 */
interface ApiService {
    @Headers("method:GET_IMAGE_LIST")
    @GET("mobile/mobileIn.do?")
    fun getImageList(@Query("url") url: String): Observable<ImageListParser>
}