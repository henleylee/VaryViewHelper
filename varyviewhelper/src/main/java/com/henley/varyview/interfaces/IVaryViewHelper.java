package com.henley.varyview.interfaces;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;

/**
 * 有数据、无数据、加载中、加载失败、无网络、网络不佳页面切换辅助类接口
 *
 * @author Henley
 * @date 2016/11/17 16:32
 */
public interface IVaryViewHelper {

    /**
     * 返回上下文
     */
    Context getContext();

    /**
     * 返回显示数据的View
     */
    View getContentView();

    /**
     * 返回当前正在显示的View
     */
    View getCurrentView();

    /**
     * 切换要显示的View
     */
    void showLayout(View view);

    /**
     * 切换要显示的布局
     */
    void showLayout(@LayoutRes int layoutId);

    /**
     * 实例化布局
     */
    View inflate(@LayoutRes int layoutId);

    /**
     * 恢复显示数据的View
     */
    void restoreLayout();
}
