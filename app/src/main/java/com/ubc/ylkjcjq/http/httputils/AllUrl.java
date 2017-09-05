package com.ubc.ylkjcjq.http.httputils;


/**
 * Created by cjq on 16/9/26.
 */

public class AllUrl {

    private static AllUrl mAllUrl;

    public static AllUrl getInstance() {
        if (mAllUrl == null) {
            mAllUrl = new AllUrl();
        }
        return mAllUrl;
    }


    //创建账户 http://192.168.1.122:10060/ubc/bag/account/create
    public String getCreatAccountUrl() {
        return Url.BASE_URL + "/account/create";
    }

    //登录账户
    public String getLoginAccountUrl() {
        return Url.BASE_URL + "/account/token";
    }

    //刷新token
    public String getRefreshTokenUrl() {
        return Url.BASE_URL + "/account/token";
    }

    //创建钱包
    public String getCreatWalletUrl() {
        return Url.BASE_URL + "/account/wallet";
    }

    //账户钱包列表
    public String getAccountWalletsUrl() {
        return Url.BASE_URL + "/account/wallet/project";
    }

    //账户钱包币的列表
    public String getAccountCoinsUrl(String projectAddress) {
        return Url.BASE_URL + "/account/wallet/" + projectAddress + "/item";
    }

    //账户钱包币的信息
    public String getAccountCoinDefailUrl(String projectAddress) {
        return Url.BASE_URL + "/account/wallet/project/" + projectAddress;
    }

}
