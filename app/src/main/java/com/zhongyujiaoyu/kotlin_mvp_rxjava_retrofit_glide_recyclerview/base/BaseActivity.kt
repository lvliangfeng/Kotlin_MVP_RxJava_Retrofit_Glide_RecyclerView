package com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.base

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import com.classic.common.MultipleStatusView
import com.orhanobut.logger.Logger
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

abstract class BaseActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {

    //多种状态切换的自定义View
    protected var mLayoutStatusView: MultipleStatusView? = null

    open val mRetryClickListener: View.OnClickListener = View.OnClickListener {
        start()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        initData()
        initView()
        initListener()
        start()
    }

    private fun initListener() {
        mLayoutStatusView?.setOnClickListener(mRetryClickListener)
    }

    //加载布局
    abstract fun layoutId(): Int

    //初始化数据
    abstract fun initData()

    //初始化 View
    abstract fun initView()

    //开始请求
    abstract fun start()

    //打开软键盘
    fun openKeyBoard(mEditText: EditText, mContext: Context): Unit {
        val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN)
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    //关闭软键盘
    fun closeKeyBoard(mEditText: EditText, mContext: Context): Unit {
        val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mEditText.windowToken, 0)
    }

    /**
     * 重写要申请权限的Activity或者Fragment的onRequestPermissionResult()方法
     * 在里面调用EasyPermissions.onRequestPermissionsResult(),实现回调
     * @param requestCode 权限请求识别码
     * @param permissions 申请的权限
     * @param grantResults 授权结果
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    /**
     * 当权限被成功申请的时候执行回调
     *
     * @param requestCode 权限请求的识别码
     * @param perms       申请的权限的名字
     */
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        Logger.d("EasyPermissions 获取成功的权限$perms")
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        val sb = StringBuilder()
        for (str in perms) {
            sb.append(str)
            sb.append("\n")
        }
        sb.replace(sb.length - 2, sb.length, "")
        //用户点击拒绝并不在询问时调用
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            Toast.makeText(this, "已拒绝权限" + sb + "并不再询问", Toast.LENGTH_SHORT).show()
            AppSettingsDialog.Builder(this)
                    .setRationale("此功能需要" + sb + "权限，否则无法正常使用，是够打开设置")
                    .setPositiveButton("好的")
                    .setNegativeButton("不行")
                    .build()
                    .show()
        }
    }
}
