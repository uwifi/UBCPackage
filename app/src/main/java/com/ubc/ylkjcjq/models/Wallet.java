package com.ubc.ylkjcjq.models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by ylkjcjq on 2017/9/4.
 */

public class Wallet implements Serializable {

        @Expose
        private  int id;
        @Expose
        private  boolean isShow = false;//是否默认显示
        @Expose
        private String  projectAppellation;//钱包名称
        @Expose
        private String  projectSymbol;//钱包类型
        @Expose
        private String  projectIcon;//钱包图标
        @Expose
        private String  accountAddress;//钱包地址
        @Expose
        private String  account;//钱包所属账户'
        @Expose
        private String  accountValue;//
        @Expose
        private String  status;//钱包状态
        @Expose
        private int  accountId;//所属账户id

        public void setId(int id) {
                this.id = id;
        }

        public void setShow(boolean show) {
                isShow = show;
        }

        public void setProjectAppellation(String projectAppellation) {
                this.projectAppellation = projectAppellation;
        }

        public void setProjectSymbol(String projectSymbol) {
                this.projectSymbol = projectSymbol;
        }

        public void setProjectIcon(String projectIcon) {
                this.projectIcon = projectIcon;
        }

        public void setAccountAddress(String accountAddress) {
                this.accountAddress = accountAddress;
        }

        public void setAccount(String account) {
                this.account = account;
        }

        public void setAccountValue(String accountValue) {
                this.accountValue = accountValue;
        }

        public void setStatus(String status) {
                this.status = status;
        }

        public void setAccountId(int accountId) {
                this.accountId = accountId;
        }

        public int getId() {
                return id;
        }

        public boolean isShow() {
                return isShow;
        }

        public String getProjectAppellation() {
                return projectAppellation;
        }

        public String getProjectSymbol() {
                return projectSymbol;
        }

        public String getProjectIcon() {
                return projectIcon;
        }

        public String getAccountAddress() {
                return accountAddress;
        }

        public String getAccount() {
                return account;
        }

        public String getAccountValue() {
                return accountValue;
        }

        public String getStatus() {
                return status;
        }

        public int getAccountId() {
                return accountId;
        }
}
