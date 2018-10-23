package com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.ui.adapter

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.support.v7.widget.CardView
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.king.kotlinmvp.glide.GlideApp
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.Constants
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.R
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.ZYApplication
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.mvp.model.bean.ImageResult
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.ui.activity.ImageDetailActivity
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.view.recyclerview.ViewHolder
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.view.recyclerview.adapter.CommonAdapter

/**
 * @Author lf_lv
 * @date  2018/10/22
 * Description:
 */
class ImageAdapter(mContext: Activity, categoryList: ArrayList<ImageResult>, layoutId: Int) : CommonAdapter<ImageResult>(mContext, categoryList, layoutId) {

    private var textTypeface: Typeface? = null

    init {
        textTypeface = Typeface.createFromAsset(ZYApplication.context.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
    }

    fun setData(categoryList: ArrayList<ImageResult>) {
        mData.clear()
        mData = categoryList
        notifyDataSetChanged()
    }

    fun addData(dataList: ArrayList<ImageResult>) {
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }

    //绑定数据
    override fun bindData(holder: ViewHolder, data: ImageResult, position: Int) {
        holder.setText(R.id.tv_hint, "#${data.desc}")
        holder.getView<TextView>(R.id.tv_hint).typeface = textTypeface

        holder.setImagePath(R.id.iv_image, object : ViewHolder.HolderImageLoader(data.url) {
            override fun loadImage(iv: ImageView, path: String) {
                GlideApp.with(mContext)
                        .load(GlideUrl(path, LazyHeaders.Builder().addHeader("Referer", "http://www.mzitu.com/").build()))
                        .placeholder(R.color.color_darker_gray)
                        .transition(DrawableTransitionOptions().crossFade())
                        .thumbnail(0.5f)
                        .into(iv)
            }

        })

        holder.setOnItemClickListener(View.OnClickListener {
            val intent = Intent(mContext as Activity, ImageDetailActivity::class.java)
            intent.putExtra(Constants.BUNDLE_IMAGE_DATA, data)
            mContext.startActivity(intent)
        })

        //动画
        val animation = AnimationUtils.loadAnimation(mContext, R.anim.translate_0_1)
        holder.getView<CardView>(R.id.mLayout).startAnimation(animation)
    }
}