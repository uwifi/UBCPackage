package com.ubc.ylkjcjq.activitys;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.ubc.ylkjcjq.R;
import com.ubc.ylkjcjq.adapters.RecyclerViewAdapter;
import com.ubc.ylkjcjq.models.CoinObject;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class CoinDetialActivity extends AutoLayoutActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {


    private SwipeRefreshLayout mSwipeRefreshWidget;
    List<CoinObject> datas = new ArrayList<>();
    RecyclerViewAdapter mRecyclerViewAdapter = new RecyclerViewAdapter(datas);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_detial);

        //recyclerView填充数据(忽略不计)
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        mSwipeRefreshWidget = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_widget);

        mSwipeRefreshWidget.setColorSchemeResources(R.color.blue, R.color.community_bg,
                R.color.umeng_fb_color_btn_normal, R.color.umeng_fb_color_btn_pressed);
        mSwipeRefreshWidget.setOnRefreshListener(this);

        // 这句话是为了，第一次进入页面的时候显示加载进度条
        mSwipeRefreshWidget.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));

        mSwipeRefreshWidget.post(new Runnable() {
            @Override
            public void run() {
//                mSwipeRefreshWidget.setRefreshing(true);
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                finish();
                break;
            case R.id.btn_shoukuan:
                startActivity(new Intent(this, ReceivablesCodeActivity.class));
                break;
            case R.id.btn_zhuanzhang:
                startActivity(new Intent(this, TransferActivity.class));
                break;
            default:

                break;
        }
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshWidget.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshWidget.setRefreshing(false);
            }
        }, 1000);
    }
}
