package com.ubc.ylkjcjq.activitys;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ubc.ylkjcjq.utils.LoginConfig;
import com.zhy.autolayout.AutoLayoutActivity;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class BaseActivity extends AutoLayoutActivity {

    private  SweetAlertDialog pDialog;
    public LoginConfig mLoginConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDialog();
    }

    private void initDialog(){
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#4dc1fd"));
        pDialog.setTitleText("Waiting...");
        pDialog.setCancelable(false);
        mLoginConfig = new LoginConfig(this);
    }

    public void showDialog(){
        pDialog.show();
    }

    public void hideDialog(){
        pDialog.dismissWithAnimation();
    }
}
