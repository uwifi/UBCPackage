package com.ubc.ylkjcjq.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ubc.ylkjcjq.R;
import com.ubc.ylkjcjq.activitys.CoinDetialActivity;
import com.ubc.ylkjcjq.activitys.CreatePackageActivity;
import com.ubc.ylkjcjq.activitys.ReceivablesCodeActivity;
import com.ubc.ylkjcjq.adapters.EndMenuItemAdapter;
import com.ubc.ylkjcjq.adapters.RecyclerViewAdapter;
import com.ubc.ylkjcjq.http.httputils.AllUrl;
import com.ubc.ylkjcjq.http.httputils.AsyncTaskManager;
import com.ubc.ylkjcjq.http.httputils.GsonUtils;
import com.ubc.ylkjcjq.http.httputils.HttpUtil;
import com.ubc.ylkjcjq.http.requestparams.BaseRequestParm;
import com.ubc.ylkjcjq.http.responsebeans.BaseResponseBean;
import com.ubc.ylkjcjq.http.responsebeans.RequestListener;
import com.ubc.ylkjcjq.models.CreateWallet;
import com.ubc.ylkjcjq.models.EndMenu;
import com.ubc.ylkjcjq.models.Wallet;
import com.ubc.ylkjcjq.utils.GlobleValue;
import com.ubc.ylkjcjq.utils.LoginConfig;
import com.ubc.ylkjcjq.views.ObservableScrollView;
import com.ubc.ylkjcjq.views.RecyclerViewItemClickListener;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;


/**
 * 首页
 *
 * @author CJQ
 */
public class HomeMain1Fragment extends Fragment implements View.OnClickListener ,ObservableScrollView.ScrollViewListener,SwipeRefreshLayout.OnRefreshListener {


    private RecyclerView mRecyclerView;
    private ImageView image_header;
    private ObservableScrollView personalScrollView;
    private ImageView nodataImageView;
    private int height;
    private View layoutHead;
    private ImageView imageView;
    private ImageView mTabLineIv;
    private TextView mTvOne, mTvTwo,tv_zongzichang,zongzichang,tvWallet2,tvWallet;
    private int screenWidth;

    private DrawerLayout mDrawerLayout;
    private ListView mListView;
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private Context mContext;

    String[] datas = {"ETH","UBC","BAT","DGD","GNT","1ST"};

    RecyclerViewAdapter mRecyclerViewAdapter = new RecyclerViewAdapter(datas);

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main1, null);
        mContext = view.getContext();
        //recyclerView填充数据(忽略不计)
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        tv_zongzichang = (TextView) view.findViewById(R.id.tv_zongzichang);
        zongzichang = (TextView) view.findViewById(R.id.zongzichang);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        mRecyclerViewAdapter.setmRecyclerViewItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                startActivity(new Intent(view.getContext(),CoinDetialActivity.class));
            }
        });

        personalScrollView = (ObservableScrollView) view.findViewById(R.id.mScrollView);

        layoutHead = view.findViewById(R.id.view_head);
        imageView = (ImageView)view. findViewById(R.id.iv_personal_bg);
        layoutHead.setBackgroundColor(Color.argb(0, 20, 52, 161));
        layoutHead.setAlpha(0);

        //获取顶部图片高度后，设置滚动监听
        ViewTreeObserver vto = imageView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                imageView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                height = imageView.getHeight();
                imageView.getWidth();
                personalScrollView.setScrollViewListener(HomeMain1Fragment.this);
            }
        });

        personalScrollView.smoothScrollTo(0, 0);

        view.findViewById(R.id.iv_menu).setOnClickListener(this);
        view.findViewById(R.id.mRL1).setOnClickListener(this);
        view.findViewById(R.id.mRL2).setOnClickListener(this);
        view.findViewById(R.id.tvWallet2).setOnClickListener(this);
        view.findViewById(R.id.tvWallet3).setOnClickListener(this);

        tvWallet = (TextView)view.findViewById(R.id.tvWallet);
        tvWallet2 = (TextView)view.findViewById(R.id.tvWallet2);

        mDrawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        mListView = (ListView) view.findViewById(R.id.listview);

        mSwipeRefreshWidget = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        mRecyclerView = (RecyclerView) view.findViewById(android.R.id.list);

        mSwipeRefreshWidget.setColorSchemeResources(R.color.blue,R.color.community_bg,
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
        getWallet();
        return view;
    }


    @Override
    public void onClick(View view) {

//        Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(imageView);

        switch (view.getId()) {
            case R.id.iv_menu:
                if (mDrawerLayout.isDrawerOpen(Gravity.END)){
                    mDrawerLayout.closeDrawer(Gravity.END,true);
                }else {
                    mDrawerLayout.openDrawer(Gravity.END,true);
                }
                break;
            case R.id.mRL1:
                mDrawerLayout.closeDrawer(Gravity.END,true);
                startActivityForResult(new Intent(view.getContext(), CaptureActivity.class), 0);
                break;

            case R.id.mRL2:
                mDrawerLayout.closeDrawer(Gravity.END,true);
                    startActivity(new Intent(view.getContext(), CreatePackageActivity.class));
                break;
            case R.id.tvWallet2:
            case R.id.tvWallet3:
                startActivity(new Intent(view.getContext(), ReceivablesCodeActivity.class));
                break;
           default:

                break;
        }
    }

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {

//		Log.i("TAG","y--->"+y+"    height-->"+height);
        if (y <= height) {
            float scale = (float) y / height;
            float alpha = (255 * scale);
//			Log.i("TAG","alpha--->"+alpha);

            //layout全部透明
//			layoutHead.setAlpha(scale);

            //只是layout背景透明(仿知乎滑动效果)
            layoutHead.setBackgroundColor(Color.argb((int) alpha, 20, 52, 161));
            layoutHead.setAlpha(scale);

            tv_zongzichang.setAlpha(1-scale);
            zongzichang.setAlpha(1-scale);
        }
    }

    @Override
    public void onRefresh() {

        mSwipeRefreshWidget.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshWidget.setRefreshing(false);
            }
        },1000);
    }

    private void getWallet() {
        String url = AllUrl.getInstance().getAccountWalletsUrl();
        if (HttpUtil.isNetworkAvailable(mContext)) {
            AsyncTaskManager.getInstance().StartHttp(new BaseRequestParm(url, "",
                            AsyncTaskManager.ContentTypeJson, "GET", LoginConfig.getAuthorization()),
                    new RequestListener<BaseResponseBean>() {

                        @Override
                        public void onFailed() {
                            handler.sendEmptyMessage(GlobleValue.FAIL);
                        }

                        @Override
                        public void onComplete(BaseResponseBean bean) {
                            if (bean.isSuccess()) {
                                analiData(bean);
                            } else
                                handler.sendEmptyMessage(GlobleValue.FAIL);
                        }
                    }, mContext);
        } else {
            Toasty.error(mContext, "网络未连接", Toast.LENGTH_SHORT, true).show();
            return;
        }
    }

    //获取钱包 币的列表
    private void getWalletCoins(String projectAddress) {

        String url = AllUrl.getInstance().getAccountCoinsUrl(projectAddress);
        if (HttpUtil.isNetworkAvailable(mContext)) {
            AsyncTaskManager.getInstance().StartHttp(new BaseRequestParm(url, "",
                            AsyncTaskManager.ContentTypeJson, "GET", LoginConfig.getAuthorization()),
                    new RequestListener<BaseResponseBean>() {

                        @Override
                        public void onFailed() {
                            handler.sendEmptyMessage(GlobleValue.FAIL);
                        }

                        @Override
                        public void onComplete(BaseResponseBean bean) {
                            if (bean.isSuccess()) {
                                analiDataCoins(bean);
                            } else
                                handler.sendEmptyMessage(GlobleValue.FAIL);
                        }
                    }, mContext);
        } else {
            Toasty.error(mContext, "网络未连接", Toast.LENGTH_SHORT, true).show();
            return;
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GlobleValue.SUCCESS:
                    if(mWallets == null || mWallets.size()==0){
                        return;
                    }
                    mWallets.get(LoginConfig.getDefailtWallt()).setShow(true);
                    tvWallet.setText(mWallets.get(LoginConfig.getDefailtWallt()).getProjectAppellation());
                    tvWallet2.setText(mWallets.get(LoginConfig.getDefailtWallt()).getAccountAddress());

                    getWalletCoins(mWallets.get(LoginConfig.getDefailtWallt()).getAccountAddress());

                    EndMenuItemAdapter ada = new EndMenuItemAdapter(mContext,mWallets);
                    mListView.setAdapter(ada);
                    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            mDrawerLayout.closeDrawer(Gravity.END,true);

                        }
                    });

                    break;
                case GlobleValue.FAIL:

                    break;
            }
        }
    };


    private List<Wallet> mWallets;
    private void analiData(BaseResponseBean bean) {
        try {
            mWallets = GsonUtils.toList(GsonUtils.getRootJsonObject(bean.getResult()),
                    "data", Wallet.class);
            handler.sendEmptyMessage(GlobleValue.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            handler.sendEmptyMessage(GlobleValue.FAIL);
        }
    }

    private void analiDataCoins(BaseResponseBean bean) {
        try {
//            mWallets = GsonUtils.toList(GsonUtils.getRootJsonObject(bean.getResult()),
//                    "data", Wallet.class);
            handler.sendEmptyMessage(GlobleValue.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            handler.sendEmptyMessage(GlobleValue.FAIL);
        }
    }
}
