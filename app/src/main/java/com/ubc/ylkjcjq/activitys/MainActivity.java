package com.ubc.ylkjcjq.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

import com.ubc.ylkjcjq.R;
import com.ubc.ylkjcjq.fragments.HomeMain1Fragment;
import com.ubc.ylkjcjq.fragments.HomeMain2Fragment;
import com.zhy.autolayout.AutoLayoutActivity;

public class MainActivity extends AutoLayoutActivity implements View.OnClickListener {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private HomeMain1Fragment mHomeMain1Fragment;
    private HomeMain2Fragment mHomeMain2Fragment;
    private ImageView btn_home_pre;
    private ImageView btn_wode_pre;
    private TextView tv_zichang,tv_wode;
    private FrameLayout fmpan;
    private LayoutInflater inflater;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        mHomeMain1Fragment = new HomeMain1Fragment();
        transaction.replace(R.id.fragment_stub, mHomeMain1Fragment);
        transaction.commit();
    }

    private void initView() {
        inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        fmpan = (FrameLayout) findViewById(R.id.bottom);
        btn_home_pre = (ImageView) findViewById(R.id.btn_home_pre);
        btn_wode_pre = (ImageView) findViewById(R.id.btn_wode_pre);
        tv_zichang = (TextView) findViewById(R.id.tv_zichang);
        tv_wode = (TextView) findViewById(R.id.tv_wode);

        FrameLayout FrameLayout1 = (FrameLayout) findViewById(R.id.tab1);
        FrameLayout FrameLayout2 = (FrameLayout) findViewById(R.id.tab2);

        FrameLayout1.setOnClickListener(this);
        FrameLayout2.setOnClickListener(this);
        btn_home_pre.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        hidebtn();
        switch (view.getId()) {
            case R.id.tab1:
                btn_home_pre.setVisibility(View.VISIBLE);
                tv_zichang.setVisibility(View.VISIBLE);
                transaction = fragmentManager.beginTransaction();
                mHomeMain1Fragment = new HomeMain1Fragment();
                transaction.replace(R.id.fragment_stub, mHomeMain1Fragment);
                transaction.commit();
                break;

            case R.id.tab2:
                btn_wode_pre.setVisibility(View.VISIBLE);
                tv_wode.setVisibility(View.VISIBLE);
                transaction = fragmentManager.beginTransaction();
                mHomeMain2Fragment = new HomeMain2Fragment();
                transaction.replace(R.id.fragment_stub, mHomeMain2Fragment);
                transaction.commit();
                break;
            default:

                break;
        }
    }

    private void hidebtn(){
        btn_home_pre.setVisibility(View.GONE);
        btn_wode_pre.setVisibility(View.GONE);
        tv_zichang.setVisibility(View.GONE);
        tv_wode.setVisibility(View.GONE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String result = data.getExtras().getString("result");
            Intent mIntent = new Intent(this,TransferActivity.class);
            mIntent.putExtra("code",result);
            startActivity(mIntent);
        }else {
            mHomeMain1Fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}

