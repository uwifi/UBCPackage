package com.ubc.ylkjcjq.models;

import java.io.Serializable;

/**
 * Created by ylkjcjq on 2017/9/4.
 */

public class Wallet implements Serializable {

        private  int id;
        private  String createdAt;
        private  String projectAppellation;
        private  String projectSymbol;
        private  String projectIcon;
        private  String accountAddress;
        private  String accountValue;
        private  String updated_at;
        private  String created_at;
        private  String updatedAt;
        private  String account;
        private  int accountId;

        public int getId() {
                return id;
        }

        public String getCreatedAt() {
                return createdAt;
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

        public String getAccountValue() {
                return accountValue;
        }

        public String getUpdated_at() {
                return updated_at;
        }

        public String getCreated_at() {
                return created_at;
        }

        public String getUpdatedAt() {
                return updatedAt;
        }

        public String getAccount() {
                return account;
        }

        public int getAccountId() {
                return accountId;
        }

        public void setId(int id) {
                this.id = id;
        }

        public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
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

        public void setAccountValue(String accountValue) {
                this.accountValue = accountValue;
        }

        public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
        }

        public void setCreated_at(String created_at) {
                this.created_at = created_at;
        }

        public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
        }

        public void setAccount(String account) {
                this.account = account;
        }

        public void setAccountId(int accountId) {
                this.accountId = accountId;
        }
}
