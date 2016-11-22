package com.liyunlong.varyview;

import android.view.View;

import com.liyunlong.varyview.interfaces.IVaryViewHelper;
import com.liyunlong.varyview.interfaces.IVaryViewHelperController;


/**
 * 有数据、无数据、加载中、加载失败、无网络、网络不佳页面切换控制器
 *
 * @author liyunlong
 * @date 2016/11/17 17:02
 */
public class VaryViewHelperController implements IVaryViewHelperController {

    public IVaryViewHelper helper; // 切换不同视图的帮助类
    public View mEmptyView; // 无数据View
    public View mLoadingView; // 加载中View
    public View mErrorView; // 加载失败View
    private View mNetworkErrorView; // 无网络View
    private View mNetworkPoorView; // 网络不佳View
    private View.OnClickListener mRefreshListener; // 刷新监听

    public VaryViewHelperController(View contentView) {
        this(new ReplaceVaryViewHelper(contentView));
    }

    public VaryViewHelperController(IVaryViewHelper helper) {
        this.helper = helper;
    }

    @Override
    public View getCurrentView() {
        return helper.getCurrentView();
    }

    @Override
    public View getContentView() {
        return helper.getContentView();
    }

    @Override
    public View getEmptyView() {
        return mEmptyView;
    }

    @Override
    public View getLoadingView() {
        return mLoadingView;
    }

    @Override
    public View getErrorView() {
        return mErrorView;
    }

    @Override
    public View getNetworkErrorView() {
        return mNetworkErrorView;
    }

    @Override
    public View getNetworkPoorView() {
        return mNetworkPoorView;
    }

    @Override
    public VaryViewHelperController setUpEmptyView(View view) {
        this.mEmptyView = view;
        return this;
    }

    @Override
    public VaryViewHelperController setUpLoadingView(View view) {
        this.mLoadingView = view;
        return this;
    }

    @Override
    public VaryViewHelperController setUpErrorView(View view) {
        this.mErrorView = view;
        return this;
    }

    @Override
    public VaryViewHelperController setUpNetworkErrorView(View view) {
        this.mNetworkErrorView = view;
        return this;
    }

    @Override
    public VaryViewHelperController setUpNetworkPoorView(View view) {
        this.mNetworkPoorView = view;
        return this;
    }

    @Override
    public VaryViewHelperController setUpRefreshViews(View... views) {
        if (views != null && views.length > 0 && mRefreshListener != null) {
            for (View view : views) {
                if (view != null) {
                    view.setEnabled(true);
                    view.setClickable(true);
                    view.setOnClickListener(mRefreshListener);
                }
            }
        }
        return this;
    }

    @Override
    public VaryViewHelperController setUpRefreshListener(View.OnClickListener refreshListener) {
        this.mRefreshListener = refreshListener;
        return this;
    }

    @Override
    public void showEmptyView() {
        helper.showLayout(mEmptyView);
    }

    @Override
    public void showLoadingView() {
        helper.showLayout(mLoadingView);
    }

    @Override
    public void showErrorView() {
        helper.showLayout(mErrorView);
    }

    @Override
    public void showNetworkErrorView() {
        helper.showLayout(mNetworkErrorView);
    }

    @Override
    public void showNetworkPoorView() {
        helper.showLayout(mNetworkPoorView);
    }

    @Override
    public void restore() {
        helper.restoreLayout();
    }

    @Override
    public void releaseCaseViews() {
        mEmptyView = null;
        mLoadingView = null;
        mErrorView = null;
        mNetworkErrorView = null;
        mNetworkPoorView = null;
    }

    public static class Builder {

        private View mContentView;
        private View mEmptyView;
        private View mLoadingView;
        private View mErrorView;
        private View mNetworkErrorView;
        private View mNetworkPoorView;
        private View[] views;
        private View.OnClickListener mRefreshListener;

        /**
         * 返回显示数据的View
         */
        public View getContentView() {
            return mContentView;
        }

        /**
         * 返回无数据的View
         */
        public View getEmptyView() {
            return mEmptyView;
        }

        /**
         * 返回加载中的View
         */
        public View getLoadingView() {
            return mLoadingView;
        }

        /**
         * 返回加载失败的View
         */
        public View getErrorView() {
            return mErrorView;
        }

        /**
         * 返回无网络的View
         */
        public View getNetworkErrorView() {
            return mNetworkErrorView;
        }

        /**
         * 返回网络不佳的View
         */
        public View getNetworkPoorView() {
            return mNetworkPoorView;
        }

        /**
         * 设置显示数据的View
         */
        public Builder setContentView(View contentView) {
            this.mContentView = contentView;
            return this;
        }

        /**
         * 设置无数据的View
         */
        public Builder setEmptyView(View emptyView) {
            this.mEmptyView = emptyView;
            return this;
        }

        /**
         * 设置加载中的View
         */
        public Builder setLoadingView(View loadingView) {
            this.mLoadingView = loadingView;
            return this;
        }

        /**
         * 设置加载失败的View
         */
        public Builder setErrorView(View errorView) {
            this.mErrorView = errorView;
            return this;
        }

        /**
         * 设置无网络的View
         */
        public Builder setNetworkErrorView(View networkErrorView) {
            this.mNetworkErrorView = networkErrorView;
            return this;
        }

        /**
         * 设置网络不佳的View
         */
        public Builder setNetworkPoorView(View networkPoorView) {
            this.mNetworkPoorView = networkPoorView;
            return this;
        }

        /**
         * 设置刷新监听
         */
        public Builder setRefreshListener(View.OnClickListener refreshListener) {
            this.mRefreshListener = refreshListener;
            return this;
        }

        /**
         * 设置可以刷新的View
         */
        public Builder setRefreshViews(View... views) {
            this.views = views;
            return this;
        }

        /**
         * 构建CaseViewHelperController对象
         */
        public VaryViewHelperController build() {
            if (mContentView == null) {
                throw new IllegalArgumentException("The contentView can not be null!");
            }
            VaryViewHelperController helper = new VaryViewHelperController(mContentView);
            if (mEmptyView != null) {
                helper.setUpEmptyView(mEmptyView);
            }
            if (mLoadingView != null) {
                helper.setUpLoadingView(mLoadingView);
            }
            if (mErrorView != null) {
                helper.setUpErrorView(mErrorView);
            }
            if (mNetworkErrorView != null) {
                helper.setUpNetworkErrorView(mNetworkErrorView);
            }
            if (mNetworkPoorView != null) {
                helper.setUpNetworkPoorView(mNetworkPoorView);
            }
            if (mRefreshListener != null) {
                helper.setUpRefreshListener(mRefreshListener);
            }
            if (views != null && views.length > 0) {
                helper.setUpRefreshViews(views);
            }
            return helper;
        }
    }
}
