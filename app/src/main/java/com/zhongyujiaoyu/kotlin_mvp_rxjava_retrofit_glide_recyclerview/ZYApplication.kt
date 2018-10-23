package com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.utils.DisplayManager
import kotlin.properties.Delegates

/**
 * Created by Administrator on 2018/10/19.
 */
class ZYApplication : Application() {

    companion object {
        private val TAG = "MyApplication"

        var context: Context by Delegates.notNull()
            private set
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        initConfig();
        DisplayManager.init(this)
        registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks)
    }

    /**
     * log 打印的初始化配置
     */
    private fun initConfig() {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)   //（可选）是否显示线程信息。 默认值为true
                .methodCount(0)          // （可选）要显示的方法行数。 默认2
                .methodOffset(8)         // （可选）隐藏内部方法调用到偏移量。 默认5
                .tag("lf_lv")           //（可选）每个日志的全局标记。 默认PRETTY_LOGGER
                .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }

    private val mActivityLifecycleCallbacks = object : ActivityLifecycleCallbacks {

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            Logger.d("onCreated: " + activity.componentName.className)
        }

        override fun onActivityStarted(activity: Activity) {
            Logger.d("onStarted: " + activity.componentName.className)
        }

        override fun onActivityResumed(activity: Activity) {
            Logger.d("onResumed:" + activity.componentName.className)
        }

        override fun onActivityPaused(activity: Activity) {
            Logger.d("onPaused:" + activity.componentName.className)
        }

        override fun onActivityStopped(activity: Activity) {
            Logger.d("onStopped:" + activity.componentName.className)
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {
            Logger.d("onSaveInstanceState:" + activity.componentName.className)
        }

        override fun onActivityDestroyed(activity: Activity) {
            Log.d(TAG, "onDestroyed: " + activity.componentName.className)
        }

    }

}