package com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.net.exception

import com.google.gson.JsonParseException
import com.orhanobut.logger.Logger
import org.json.JSONException
import java.net.ConnectException
import java.net.SocketException
import java.net.UnknownHostException
import java.text.ParseException

/**
 * @Author lf_lv
 * @date  2018/10/22
 * Description:
 */
object ExceptionHandle {
    var errorCode = ErrorStatus.UNKNOWN_ERROR
    var errorMsg = "请求失败，请稍后重试"

    fun handleException(e: Throwable): String {
        e.printStackTrace()
        when (e) {
            is SocketException -> {//网络超时
                Logger.e("TAG", "网络连接异常: " + e.message)
                errorMsg = "网络连接异常"
                errorCode = ErrorStatus.NETWORK_ERROR
            }
            is ConnectException -> {//均视为网络错误
                Logger.e("TAG", "网络连接异常: " + e.message)
                errorMsg = "网络连接异常"
                errorCode = ErrorStatus.NETWORK_ERROR
            }
            is ParseException, is JSONException, is JSONException -> {//均视为解析错误
                Logger.e("TAG", "数据解析异常: " + e.message)
                errorMsg = "数据解析异常"
                errorCode = ErrorStatus.SERVER_ERROR
            }
            is ApiException -> {//服务器返回的错误信息
                errorMsg = e.message.toString()
                errorCode = ErrorStatus.SERVER_ERROR
            }
            is UnknownHostException -> {
                Logger.e("TAG", "网络连接异常: " + e.message)
                errorMsg = "网络连接异常"
                errorCode = ErrorStatus.NETWORK_ERROR
            }
            is IllegalArgumentException -> {
                errorMsg = "参数错误"
                errorCode = ErrorStatus.SERVER_ERROR
            }
            else -> {
                try {
                    Logger.e("TAG", "错误: " + e.message)
                } catch (e1: Exception) {
                    Logger.e("TAG", "未知错误Debug调试 ")
                }

                errorMsg = "未知错误，可能抛锚了吧~"
                errorCode = ErrorStatus.UNKNOWN_ERROR
            }
        }
        return errorMsg + "  " + errorCode
    }
}