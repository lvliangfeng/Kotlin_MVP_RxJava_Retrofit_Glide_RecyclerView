package com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.ui.activity

import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.R
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.base.BaseActivity
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.mvp.contract.ImageContract
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.mvp.model.bean.ImageResult
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.mvp.presenter.ImagePresenter
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.showToast
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.ui.adapter.ImageAdapter
import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.utils.DisplayManager
import kotlinx.android.synthetic.main.activity_image.*

class ImageActivity : BaseActivity(), ImageContract.View, OnRefreshListener, OnLoadmoreListener {

    //懒加载Presenter
    private val mPresenter by lazy { ImagePresenter() }

    init {
        mPresenter.attachView(this)
    }

    private var mImageList = ArrayList<ImageResult>()

    private val mAdapter by lazy {
        let { ImageAdapter(it, mImageList, R.layout.item_image) }
    }

    private var url: String = "http://www.mmonly.cc/ktmh/dmmn/"

    private var currentPage: Int = 2

    private var isRefresh: Boolean = true

    override fun layoutId(): Int {
        return R.layout.activity_image
    }

    override fun initData() {
        refreshLayout.isEnableAutoLoadmore = true
        refreshLayout.isEnableRefresh = true
        refreshLayout.setOnRefreshListener(this)
        refreshLayout.setOnLoadmoreListener(this)
    }

    override fun initView() {
        mLayoutStatusView = multipleStatusView
        mGridView.layoutManager = GridLayoutManager(this@ImageActivity, 2)
        mGridView.adapter = mAdapter
        mGridView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                val position = parent.getChildPosition(view)
                val offset = DisplayManager.dip2px(2f)!!
                outRect.set(if (position % 2 == 0) 0 else offset, offset,
                        if (position % 2 == 0) offset else 0, offset)
            }
        })
    }

    override fun start() {
        mPresenter.getImageList(url)
    }


    override fun showLoading() {
        multipleStatusView?.showLoading()
    }

    override fun dismissLoading() {
        multipleStatusView?.showContent()
    }

    override fun showError(errorMsg: String) {
        showToast(errorMsg)
    }

    override fun showIamgeData(imageSet: HashSet<ImageResult>) {
        val list: ArrayList<ImageResult> = ArrayList()
        imageSet.forEach {
            list.add(it)
        }
        if (isRefresh) {
            mAdapter.setData(list)
            refreshLayout.finishRefresh()
        } else {
            mAdapter.addData(list)
            refreshLayout.finishLoadmore()
        }
    }

    override fun onRefresh(refreshlayout: RefreshLayout?) {
        url = "http://www.mmonly.cc/ktmh/dmmn/"
        isRefresh = true
        currentPage = 2
        mPresenter.getImageList(url)
    }

    override fun onLoadmore(refreshlayout: RefreshLayout?) {
        isRefresh = false
        url = "http://www.mmonly.cc/ktmh/dmmn/list_29_$currentPage.html"
        mPresenter.getImageList(url)
        currentPage++
    }
}
