package com.ubc.ylkjcjq.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 *
 * Created by cjq on 17/8/23.
 * 系统配置
 *
 */

public class LoginConfig {
    public static SharedPreferences sp;

    public LoginConfig(Context context) {
        super();
        sp = context.getSharedPreferences("Loginconfig", Context.MODE_PRIVATE);
    }

    // 设置userName,在登录设置
    public void setUserName(String schema) {
        Editor ed = sp.edit();
        ed.putString("userName", schema);
        ed.commit();
    }

    public String getUserName() {
        return sp.getString("userName", "");
    }

    // 设置user密码,在登录设置
    public void setUserPass(String pass) {
        Editor ed = sp.edit();
        ed.putString("userpw", pass);
        ed.commit();
    }

    public String getUserPass() {
        return sp.getString("userpw", "");
    }

    // 认证码,保持登陆状态
    public static String getAuthorization() {
        return sp.getString("Authorization", "");
    }

    // 认证码,保持登陆状态
    public void setAuthorization(String ContentType) {
        Editor ed = sp.edit();
        ed.putString("Authorization", ContentType);
        ed.commit();
    }

    // 刷新token
    public static String getReAuthorization() {
        return sp.getString("reAuthorization", "");
    }

    // 刷新token
    public void setReAuthorization(String ContentType) {
        Editor ed = sp.edit();
        ed.putString("reAuthorization", ContentType);
        ed.commit();
    }

    // 有效时间
    public void setAvailbleTime(String expires_in) {
        Editor ed = sp.edit();
        ed.putString("AvailbleTime", expires_in);
        ed.commit();
    }

    // 有效时间
    public static String getAvailbleTime() {
        return sp.getString("AvailbleTime", "0");
    }

    // token获取时的系统时间
    public void setStartTime(long expires_in) {
        Editor ed = sp.edit();
        ed.putLong("NowTime", expires_in);
        ed.commit();
    }

    // token获取时的系统时间
    public static long getStartTime() {
        return sp.getLong("NowTime", 0);
    }
}
