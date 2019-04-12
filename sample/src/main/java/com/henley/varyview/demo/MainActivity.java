package com.henley.varyview.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.henley.varyview.OverlapVaryViewHelper;
import com.henley.varyview.VaryViewHelperController;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static final String[] actions = {"有数据", "无数据", "加载中", "加载失败", "无网络", "网络不佳"};
    private TextView tvContent;
    private VaryViewHelperController helperController;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gvMenu = (GridView) findViewById(R.id.menu);
        tvContent = (TextView) findViewById(R.id.content);
        helperController = createCaseViewHelperController1(); // 方法一
//        helperController = createCaseViewHelperController2(); // 方法二
//        helperController = createCaseViewHelperController3(); // 方法三
        helperController.setUpRefreshViews(helperController.getErrorView(), helperController.getNetworkPoorView());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Arrays.asList(actions));
        gvMenu.setAdapter(adapter);
        gvMenu.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        switch (position) {
            case 0:
                helperController.restore();
                break;
            case 1:
                helperController.showEmptyView();
                break;
            case 2:
                helperController.showLoadingView();
                break;
            case 3:
                helperController.showErrorView();
                break;
            case 4:
                helperController.showNetworkErrorView();
                break;
            case 5:
                helperController.showNetworkPoorView();
                break;
        }
    }

    /**
     * 创建CaseViewHelperController(方法一)
     */
    private VaryViewHelperController createCaseViewHelperController1() {
        return new VaryViewHelperController(tvContent)
                .setUpEmptyView(getView(actions[1], Color.parseColor("#ff0000")))
                .setUpLoadingView(getView(actions[2], Color.parseColor("#ffda00")))
                .setUpErrorView(getView(actions[3], Color.parseColor("#0dfa30")))
                .setUpNetworkErrorView(getView(actions[4], Color.parseColor("#feaf43")))
                .setUpNetworkPoorView(getView(actions[5], Color.parseColor("#ff0acd")))
                .setUpRefreshListener(refreshListener);
    }

    /**
     * 创建CaseViewHelperController(方法二)
     */
    private VaryViewHelperController createCaseViewHelperController2() {
        return new VaryViewHelperController(new OverlapVaryViewHelper(tvContent))
                .setUpEmptyView(getView(actions[1], Color.parseColor("#ff0000")))
                .setUpLoadingView(getView(actions[2], Color.parseColor("#ffda00")))
                .setUpErrorView(getView(actions[3], Color.parseColor("#0dfa30")))
                .setUpNetworkErrorView(getView(actions[4], Color.parseColor("#feaf43")))
                .setUpNetworkPoorView(getView(actions[5], Color.parseColor("#ff0acd")))
                .setUpRefreshListener(refreshListener);
    }

    /**
     * 创建CaseViewHelperController(方法三)
     */
    private VaryViewHelperController createCaseViewHelperController3() {
        return new VaryViewHelperController.Builder()
                .setContentView(tvContent)
                .setEmptyView(getView(actions[1], Color.parseColor("#ff0000")))
                .setLoadingView(getView(actions[2], Color.parseColor("#ffda00")))
                .setErrorView(getView(actions[3], Color.parseColor("#0dfa30")))
                .setNetworkErrorView(getView(actions[4], Color.parseColor("#feaf43")))
                .setNetworkPoorView(getView(actions[5], Color.parseColor("#ff0acd")))
                .setRefreshListener(refreshListener)
                .build();
    }

    private View getView(String action, int color) {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_varyview, null);
        TextView tvAction = (TextView) view.findViewById(R.id.action);
        tvAction.setText(action);
        tvAction.setTextColor(color);
        return view;
    }

    View.OnClickListener refreshListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            showToast("开始刷新...");
            view.postDelayed(new Runnable() {
                @Override
                public void run() {
                    showToast("刷新成功");
                    helperController.restore();
                }
            }, 1500);
        }
    };

    public void showToast(CharSequence message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

}
