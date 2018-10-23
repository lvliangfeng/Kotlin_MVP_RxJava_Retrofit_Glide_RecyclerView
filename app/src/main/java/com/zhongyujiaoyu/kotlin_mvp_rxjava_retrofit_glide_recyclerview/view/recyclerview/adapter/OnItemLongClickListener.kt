package com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.view.recyclerview.adapter

import java.text.FieldPosition

/**
 * @Author lf_lv
 * @date  2018/10/23
 * Description:
 */
interface OnItemLongClickListener {
    fun onItemLongClick(obj: Any?, position: Int): Boolean
}