package com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.utils

import android.content.Context
import android.util.DisplayMetrics

/**
 * @Author lf_lv
 * @date  2018/10/19
 * Description:
 */
object DisplayManager {
    init {

    }

    private var displayMetrics: DisplayMetrics? = null
    private var screenWidth: Int? = null
    private var screenHeight: Int? = null
    private var screenDpi: Int? = null
    //UI图的大小
    private const val STANDARD_WIDTH = 1080
    private const val STANDARD_HEIGHT = 1920

    fun init(context: Context): Unit {
        displayMetrics = context.resources.displayMetrics
        screenWidth = displayMetrics?.widthPixels
        screenHeight = displayMetrics?.heightPixels
        screenDpi = displayMetrics?.densityDpi
    }

    fun getScreenWidth(): Int? {
        return screenWidth
    }

    fun getScreenHeight(): Int? {
        return screenHeight
    }

    /**
     * 输入UI图的尺寸，输出实际的px
     * @param px ui图中的大小
     * @retur
     */
    fun getRealWidth(px: Int): Int? {
        //ui图的宽度
        return getRealWidth(px, STANDARD_WIDTH.toFloat())
    }

    /**
     * 输入UI图的尺寸，输出实际的px,第二个参数是父布局
     *
     * @param px          ui图中的大小
     * @param parentWidth 父view在ui图中的高度
     * @return
     */
    private fun getRealWidth(px: Int, parentWidth: Float): Int? {
        return (px / parentWidth * getScreenWidth()!!).toInt()
    }

    /**
     * 输入UI图的尺寸，输出实际的px
     *
     * @param px ui图中的大小
     * @return
     */
    fun getRealHeight(px: Int): Int? {
        //ui图的高度
        return getRealHeight(px, STANDARD_HEIGHT.toFloat())
    }

    /**
     * 输入UI图的尺寸，输出实际的px,第二个参数是父布局
     *
     * @param px           ui图中的大小
     * @param parentHeight 父view在ui图中的高度
     * @return
     */
    fun getRealHeight(px: Int, parentHeight: Float): Int? {
        return (px / parentHeight * getScreenHeight()!!).toInt()
    }

    fun getPaintSize(size: Int): Int? {
        return getRealHeight(size)
    }

    fun dip2px(dipValue: Float): Int? {
        val scale = displayMetrics?.density
        return (dipValue * scale!! + 0.5f).toInt()
    }
}