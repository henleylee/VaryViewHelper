package com.henley.varyview.interfaces;

import android.view.View;

import com.henley.varyview.VaryViewHelperController;

/**
 * 有数据、无数据、加载中、加载失败、无网络、网络不佳页面切换控制器接口
 *
 * @author Henley
 * @date 2016/11/17 17:18
 */
public interface IVaryViewHelperController {

    /**
     * 返回当前正在显示的View
     */
    View getCurrentView();

    /**
     * 返回显示数据的View
     */
    View getContentView();

    /**
     * 返回无数据View
     */
    View getEmptyView();

    /**
     * 返回加载中View
     */
    View getLoadingView();

    /**
     * 返回加载失败View
     */
    View getErrorView();

    /**
     * 返回无网络View
     */
    View getNetworkErrorView();

    /**
     * 返回网络不佳View
     */
    View getNetworkPoorView();

    /**
     * 设置无数据View
     */
    VaryViewHelperController setUpEmptyView(View view);

    /**
     * 设置加载中View
     */
    VaryViewHelperController setUpLoadingView(View view);

    /**
     * 设置加载失败View
     */
    VaryViewHelperController setUpErrorView(View view);

    /**
     * 设置无网络View
     */
    VaryViewHelperController setUpNetworkErrorView(View view);

    /**
     * 设置网络不佳View
     */
    VaryViewHelperController setUpNetworkPoorView(View view);

    /**
     * 设置可以刷新的View
     */
    VaryViewHelperController setUpRefreshViews(View... views);

    /**
     * 设置刷新监听
     */
    VaryViewHelperController setUpRefreshListener(View.OnClickListener refreshListener);

    /**
     * 显示无数据View
     */
    void showEmptyView();

    /**
     * 显示加载中View
     */
    void showLoadingView();

    /**
     * 显示加载失败View
     */
    void showErrorView();

    /**
     * 显示无网络View
     */
    void showNetworkErrorView();

    /**
     * 显示网络不佳View
     */
    void showNetworkPoorView();

    /**
     * 恢复显示有数据View
     */
    void restore();

    /**
     * 释放所有View
     */
    void releaseCaseViews();
}
