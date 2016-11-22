package com.liyunlong.varyview;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.liyunlong.varyview.interfaces.IVaryViewHelper;


/**
 * 有数据、无数据、加载中、加载失败、无网络、网络不佳页面切换辅助类(重叠)
 *
 * @author liyunlong
 * @date 2016/11/17 18:03
 */
public class OverlapVaryViewHelper implements IVaryViewHelper {

    public View contentView;  // 显示数据的View
    public IVaryViewHelper helper; // 切换不同视图的帮助类

    public OverlapVaryViewHelper(View view) {
        this.contentView = view; // 显示数据的View
        ViewGroup parent; // 找到父View
        if (contentView.getParent() != null) {
            parent = (ViewGroup) contentView.getParent();
        } else {
            parent = (ViewGroup) contentView.getRootView().findViewById(android.R.id.content);
        }

        int childIndex = 0; // 要显示的View在父View中的位置
        int childCount = parent.getChildCount();
        for (int index = 0; index < childCount; index++) { // 遍历显示数据的View的父View的Child
            if (contentView == parent.getChildAt(index)) {
                childIndex = index; // 获取要显示的View在父View中的位置
                break;
            }
        }

        // 移除显示数据的View，并重新将一个FrameLayout添加到显示数据的View的位置
        Context context = contentView.getContext();
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();// 获取显示数据的View的参数信息
        FrameLayout frameLayout = new FrameLayout(context); // 创建一个FrameLayout
        parent.removeViewAt(childIndex); // 移除显示数据的View
        parent.addView(frameLayout, childIndex, layoutParams);// 将创建的FrameLayout添加进原来的View的位置
        // 在这个FrameLayout中实现将新的View覆盖在原来显示数据的View上
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View floatView = new View(context); // 创建用于覆盖显示数据的View的新View
        frameLayout.addView(contentView, params);// 将显示数据的View添加到创建的FrameLayout中
        frameLayout.addView(floatView, params);// 将用于覆盖显示数据的View的新View添加到创建的FrameLayout中

        helper = new ReplaceVaryViewHelper(floatView); // 创建切换不同视图的帮助类
    }

    @Override
    public Context getContext() {
        return helper.getContext();
    }

    @Override
    public View getContentView() {
        return helper.getContentView();
    }

    @Override
    public View getCurrentView() {
        return helper.getCurrentView();
    }

    @Override
    public void showLayout(View view) {
        helper.showLayout(view);
    }

    @Override
    public void showLayout(@LayoutRes int layoutId) {
        helper.showLayout(layoutId);
    }

    @Override
    public View inflate(@LayoutRes int layoutId) {
        return helper.inflate(layoutId);
    }

    @Override
    public void restoreLayout() {
        helper.restoreLayout();
    }
}
