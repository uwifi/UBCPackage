package com.ubc.ylkjcjq.activitys;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.ubc.ylkjcjq.R;
import com.ubc.ylkjcjq.http.httputils.AllUrl;
import com.ubc.ylkjcjq.http.httputils.AsyncTaskManager;
import com.ubc.ylkjcjq.http.httputils.GsonUtils;
import com.ubc.ylkjcjq.http.httputils.HttpUtil;
import com.ubc.ylkjcjq.http.requestparams.BaseRequestParm;
import com.ubc.ylkjcjq.http.responsebeans.BaseResponseBean;
import com.ubc.ylkjcjq.http.responsebeans.RequestListener;
import com.ubc.ylkjcjq.utils.GlobleValue;
import com.ubc.ylkjcjq.utils.LoginConfig;
import com.ubc.ylkjcjq.utils.MD5Util;
import es.dmoral.toasty.Toasty;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private LoginConfig mLoginConfig;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            hideDialog();
            switch (msg.what) {
                case GlobleValue.SUCCESS:
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();
                    break;
                case GlobleValue.FAIL:
                    Toasty.error(LoginActivity.this,"请检查账户及密码是否正确", Toast.LENGTH_SHORT,true).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        findViewById(R.id.otherLogin).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        Button email_register_in_button = (Button)findViewById(R.id.email_register_in_button);
        email_register_in_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();
            }
        });
        mLoginConfig = new LoginConfig(this);
    }

    private void attemptRegister() {
        startActivity(new Intent(this,CreateAccountActivity.class));
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password) || !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_invalid_name));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            showProgress(false);
            doLogin(email, MD5Util.encrypt(password));
        }
    }

    private void doLogin(String name, String psw) {
//        encodeURIComponent
//        name = URLEncoder.encode(name);
        String date = "username=" + name + "&password=" + psw + "&grant_type=password";

        String url = AllUrl.getInstance().getLoginAccountUrl();
        if (HttpUtil.isNetworkAvailable(this)) {
            showDialog();
            AsyncTaskManager.getInstance().StartHttp(new BaseRequestParm(url, date,
                            AsyncTaskManager.ContentTypeXfl, "POST", AsyncTaskManager.LoginToken),
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
            Toasty.error(this,"网络未连接", Toast.LENGTH_SHORT,true).show();
            return;
        }
    }

    private void analiData(BaseResponseBean bean) {
        // 数据解析
            JsonObject json = GsonUtils.getRootJsonObject(bean.getResult());
            String token = GsonUtils.getKeyValue(json, "access_token").getAsString();
            String refresh_token = GsonUtils.getKeyValue(json, "refresh_token").getAsString();
            String expires_in = GsonUtils.getKeyValue(json, "expires_in").getAsString();// 有效时间
            if (token != null) {
                mLoginConfig.setAuthorization("Bearer " + token);
                mLoginConfig.setReAuthorization(refresh_token);
                mLoginConfig.setAvailbleTime(expires_in);
                mLoginConfig.setStartTime(System.currentTimeMillis());
                Log.i("i", "获取token=" + token);
                handler.sendEmptyMessage(GlobleValue.SUCCESS);
            } else {
                handler.sendEmptyMessage(GlobleValue.FAIL);
            }
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 5;
    }

}

