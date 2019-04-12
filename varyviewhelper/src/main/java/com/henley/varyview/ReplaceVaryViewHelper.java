package com.henley.varyview;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.henley.varyview.interfaces.IVaryViewHelper;

/**
 * 有数据、无数据、加载中、加载失败、无网络、网络不佳页面切换辅助类(替换)
 *
 * @author Henley
 * @date 2016/11/17 16:39
 */
public class ReplaceVaryViewHelper implements IVaryViewHelper {

    private View contentView; // 显示数据的View
    private ViewGroup parentView; // 显示数据的View的父View
    private int viewIndex; // 要显示的View在父View中的位置
    private ViewGroup.LayoutParams params; // 显示数据的View的参数信息
    private View currentView; // 当前显示的View

    public ReplaceVaryViewHelper(View contentView) {
        this.contentView = contentView; // 显示数据的View
        initParams(); // 初始化参数信息
    }

    /**
     * 初始化参数信息
     */
    private void initParams() {
        this.params = this.contentView.getLayoutParams(); // 获取显示数据的View的参数信息
        if (contentView.getParent() != null) { // 获取显示数据的View的父View
            this.parentView = (ViewGroup) contentView.getParent();
        } else {
            this.parentView = (ViewGroup) contentView.getRootView().findViewById(android.R.id.content);
        }
        int count = parentView.getChildCount();
        for (int index = 0; index < count; index++) { // 遍历显示数据的View的父View的Child
            if (contentView == parentView.getChildAt(index)) {
                this.viewIndex = index; // 获取要显示的View在父View中的位置
                break;
            }
        }
        this.currentView = this.contentView; // 当前显示的View
    }

    @Override
    public Context getContext() {
        return contentView.getContext();
    }

    @Override
    public View getContentView() {
        return contentView;
    }

    @Override
    public View getCurrentView() {
        return currentView;
    }

    @Override
    public void showLayout(View view) {
        if (parentView == null) {
            initParams();
        }
        this.currentView = view;
        // 如果已经是那个view，那就不需要再进行替换操作了
        if (parentView.getChildAt(viewIndex) != view) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            // 切换的时候，先移除原先显示的View,再显示需要的View
            parentView.removeViewAt(viewIndex);
            parentView.addView(view, viewIndex, params);
        }
    }

    @Override
    public void showLayout(@LayoutRes int layoutId) {
        showLayout(inflate(layoutId));
    }

    @Override
    public View inflate(@LayoutRes int layoutId) {
        return LayoutInflater.from(getContext()).inflate(layoutId, null);
    }

    @Override
    public void restoreLayout() {
        showLayout(contentView);
    }

}
