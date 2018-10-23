package com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.ui.activity

import android.Manifest
import android.content.Intent
import android.graphics.Typeface
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.Toast
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.R
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.ZYApplication
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.base.BaseActivity
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.utils.AppUtils
import kotlinx.android.synthetic.main.activity_splash.*
import pub.devrel.easypermissions.EasyPermissions

class SplashActivity : BaseActivity() {

    private var textTypeface: Typeface? = null
    private var descTypeface: Typeface? = null
    private var alphaAnimation: AlphaAnimation? = null

    init {
        textTypeface = Typeface.createFromAsset(ZYApplication.context.assets, "fonts/Lobster-1.4.otf")
        descTypeface = Typeface.createFromAsset(ZYApplication.context.assets, "fonts/FZLanTingHeiS-L-GB-Regular.TTF")
    }

    override fun layoutId(): Int = R.layout.activity_splash


    override fun initData() {
    }


    override fun initView() {
        tv_app_name.typeface = textTypeface
        tv_splash_desc.typeface = descTypeface
        tv_version_name.text = "v${AppUtils.getVerName(ZYApplication.context)}"

        //渐变展示启动屏
        alphaAnimation = AlphaAnimation(0.3f, 1.0f)
        alphaAnimation?.duration = 2000
        alphaAnimation?.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationEnd(p0: Animation?) {
                redirectTo()
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

            override fun onAnimationStart(p0: Animation?) {

            }

        })

        checkPermission()
    }


    override fun start() {

    }

    fun redirectTo() {
        val intent = Intent(this, ImageActivity::class.java)
        startActivity(intent)
        finish()
    }

    /**
     * 6.0以下版本(系统自动申请) 不会弹框
     * 有些厂商修改了6.0系统申请机制，他们修改成系统自动申请权限了
     */
    private fun checkPermission() {
        val perms = arrayOf(Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        EasyPermissions.requestPermissions(this, "应用需要以下权限,请允许", 0, *perms)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        if (requestCode == 0) {
            if (perms.isNotEmpty()) {
                if (perms.contains(Manifest.permission.READ_PHONE_STATE) && perms.contains(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    if (alphaAnimation != null) {
                        iv_web_icon.startAnimation(alphaAnimation)
                    }
                }
            }
        }
    }


}
