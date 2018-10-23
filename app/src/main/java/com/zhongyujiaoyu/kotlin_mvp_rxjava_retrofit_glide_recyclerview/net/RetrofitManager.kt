package com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.net

import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.ZYApplication
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.api.ApiService
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.api.UriConstant
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.utils.AppUtils
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.utils.NetworkUtil
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.utils.Preference
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import okio.Timeout
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * @Author lf_lv
 * @date  2018/10/22
 * Description: Retrofit进行配置封装
 */
object RetrofitManager {

    private val token: String by Preference("token", "")

    val service: ApiService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        getRetrofit().create(ApiService::class.java)
    }

    //获取retrofit实例
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(UriConstant.BASE_URL)
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun getOkHttpClient(): OkHttpClient {
        //添加一个log拦截器,打印所有的log
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        //可以设置请求过滤的水平,body,basic,headers
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        //设置 请求的缓存的大小跟位置
        val cacheFile = File(ZYApplication.context.cacheDir, "cache")
        val cache = Cache(cacheFile, 1024 * 1024 * 50)//50M

        return OkHttpClient.Builder()
                .addInterceptor(addQueryParamterInterceptor()) //参数添加
                .addInterceptor(addHeaderInterception()) // token过滤
//              .addInterceptor(addCacheInterceptor())
                .addInterceptor(httpLoggingInterceptor) //日志,所有的请求响应度看到
                .cache(cache)
                .connectTimeout(60L, TimeUnit.SECONDS)
                .readTimeout(60L, TimeUnit.SECONDS)
                .writeTimeout(60L, TimeUnit.SECONDS)
                .build()
    }

    //设置公共参数
    private fun addQueryParamterInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val request: Request
            val modifiedUrl = originalRequest.url().newBuilder()
                    // Provide your custom parameter here
                    .addQueryParameter("udid", "d2807c895f0348a180148c9dfa6f2feeac0781b5")
                    .addQueryParameter("deviceModel", AppUtils.getMobileModel())
                    .build()
            request = originalRequest.newBuilder().url(modifiedUrl).build()
            chain.proceed(request)
        }
    }

    //设置头
    private fun addHeaderInterception(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val requestBuilder = originalRequest.newBuilder()
                    .header("token", token)
                    .method(originalRequest.method(), originalRequest.body())
            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }

    //设置缓存
    private fun addCacheInterception(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            if (!NetworkUtil.isNetworkAvailable(ZYApplication.context)) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build()
            }
            val response = chain.proceed(request)
            if (NetworkUtil.isNetworkAvailable(ZYApplication.context)) {
                val maxAge = 0
                // 有网络时 设置缓存超时时间0个小时 ,意思就是不读取缓存数据,只对get有用,post没有缓冲
                response.newBuilder()
                        .header("Cache-Control", "public,max-age=" + maxAge)
                        .removeHeader("Retrofit")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .build()
            } else {
                // 无网络时，设置超时为4周  只对get有用,post没有缓冲
                val maxStale = 60 * 60 * 24 * 28
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("nyn")
                        .build()
            }

            response
        }
    }
}