package com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.mvp.model.bean.parser;

import com.zhongyujiaoyu.kotlin_mvp_rxjava_retrofit_glide_recyclerview.mvp.model.bean.ImageResult;

import java.util.HashSet;

/**
 * @Author lf_lv
 * @date 2018/10/22
 * Description:
 */

public class ImageListParser {
    private String code;
    private HashSet<ImageResult> result;

    public String getCode() {
        return code == null ? "" : code;

    }

    public void setCode(String code) {
        this.code = code;
    }

    public HashSet<ImageResult> getResult() {
        return result;

    }

    public void setResult(HashSet<ImageResult> result) {
        this.result = result;
    }
}
