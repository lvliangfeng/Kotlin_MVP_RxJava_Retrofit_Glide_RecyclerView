package com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.ui.activity

import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.king.kotlinmvp.glide.GlideApp
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.Constants
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.R
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.base.BaseActivity
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.mvp.model.bean.ImageResult
import kotlinx.android.synthetic.main.activity_image_detail.*

class ImageDetailActivity : BaseActivity() {
    override fun initData() {
        val imageResult = intent.getSerializableExtra(Constants.BUNDLE_IMAGE_DATA) as ImageResult
        GlideApp.with(this@ImageDetailActivity)
                .load(GlideUrl(imageResult.url, LazyHeaders.Builder().addHeader("Referer", "http://www.mzitu.com/").build()))
                .placeholder(R.color.color_darker_gray)
                .transition(DrawableTransitionOptions().crossFade())
                .thumbnail(0.5f)
                .into(mImageView)

        mTvDesc.text = imageResult.desc
    }

    override fun initView() {
    }

    override fun start() {
    }

    override fun layoutId(): Int {
        return R.layout.activity_image_detail
    }


}
