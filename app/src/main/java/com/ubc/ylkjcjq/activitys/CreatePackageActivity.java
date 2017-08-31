package com.ubc.ylkjcjq.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ubc.ylkjcjq.R;
import com.ubc.ylkjcjq.http.httputils.AllUrl;
import com.ubc.ylkjcjq.http.httputils.AsyncTaskManager;
import com.ubc.ylkjcjq.http.httputils.HttpUtil;
import com.ubc.ylkjcjq.http.httputils.JsonObjectBuilder;
import com.ubc.ylkjcjq.http.requestparams.BaseRequestParm;
import com.ubc.ylkjcjq.http.responsebeans.BaseResponseBean;
import com.ubc.ylkjcjq.http.responsebeans.RequestListener;
import com.ubc.ylkjcjq.utils.GlobleValue;
import com.ubc.ylkjcjq.utils.MD5Util;
import es.dmoral.toasty.Toasty;

public class CreatePackageActivity extends BaseActivity implements View.OnClickListener {

    private AutoCompleteTextView mUserNameView,mPromptView;
    private EditText mPasswordView,mPassWorldAgainView;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            hideDialog();
            switch (msg.what) {
                case GlobleValue.SUCCESS:
                    Toasty.success(CreatePackageActivity.this,"创建账户成功",Toast.LENGTH_SHORT,true).show();
                    finish();
                    break;
                case GlobleValue.FAIL:
                    Toasty.error(CreatePackageActivity.this,"创建出现错误",Toast.LENGTH_SHORT,true).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_package);

        ((TextView)findViewById(R.id.tv_tit)).setText("创建钱包");

        mUserNameView = (AutoCompleteTextView) findViewById(R.id.username);
        mPromptView = (AutoCompleteTextView) findViewById(R.id.Prompt);
        mPasswordView = (EditText) findViewById(R.id.password);
        mPassWorldAgainView = (EditText) findViewById(R.id.passwordagain);
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 5;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                finish();
                break;
            case R.id.btncreate:
                mUserNameView.setError(null);
                mPromptView.setError(null);

                // Store values at the time of the login attempt.
                String name = mUserNameView.getText().toString();
                String email = mPromptView.getText().toString();
                String password = mPasswordView.getText().toString();
                String passwordagain = mPassWorldAgainView.getText().toString();

                boolean cancel = false;
                View focusView = null;

                // Check for a valid password, if the user entered one.
                if (TextUtils.isEmpty(passwordagain) || !isPasswordValid(passwordagain)) {
                    mPassWorldAgainView.setError(getString(R.string.error_invalid_password));
                    focusView = mPassWorldAgainView;
                    cancel = true;
                }

                // Check for a valid password, if the user entered one.
                if (TextUtils.isEmpty(password) || !isPasswordValid(password)) {
                    mPasswordView.setError(getString(R.string.error_invalid_password));
                    focusView = mPasswordView;
                    cancel = true;
                }

                // Check for a valid name.
                if (TextUtils.isEmpty(name)) {
                    mUserNameView.setError(getString(R.string.error_invalid_name));
                    focusView = mUserNameView;
                    cancel = true;
                }

                if (cancel) {
                    focusView.requestFocus();
                } else {
                    if(password.equals(passwordagain)){
                        Toasty.error(this,"登陆密码不能与提现密码一致",Toast.LENGTH_SHORT,true).show();
                        return;
                    }
                    JsonObjectBuilder builder = new JsonObjectBuilder();
                    builder.append("appellation", name);
                    builder.append("account", email);
                    builder.append("password", MD5Util.encrypt(password));
                    builder.append("withdraw", MD5Util.encrypt(passwordagain));
                    createAccount(builder.toString());
                }
                break;
            default:

                break;
        }
    }


    private  void createAccount(String date){

        String url = AllUrl.getInstance().getCreatAccountUrl();

        if (HttpUtil.isNetworkAvailable(this)) {
            showDialog();
            AsyncTaskManager.getInstance().StartHttp(new BaseRequestParm(url, date,
                            AsyncTaskManager.ContentTypeJson, "POST", ""),
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
                    }, this);
        } else {
            Toasty.error(this,"网络未连接",Toast.LENGTH_SHORT,true).show();
            return;
        }
    }

    private void analiData(BaseResponseBean bean) {
        try {
//            List<EndMenu> discussList = GsonUtils.toList(GsonUtils.getRootJsonObject(bean.getResult()), "content", EndMenu.class);
            handler.sendEmptyMessage(GlobleValue.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            handler.sendEmptyMessage(GlobleValue.FAIL);
        }
    }
}
