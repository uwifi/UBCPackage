package com.ubc.ylkjcjq.models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * @author cjq
 *
 * @description
 * 
 */
public class RecordItem implements Serializable {

	@Expose
	private int id;
	@Expose
	private String itemAppellation;
	@Expose
	private String itemSymbol;//名称
	@Expose
	private String itemIcon;
	@Expose
	private String itemAddress;//代币地址  null代表公链
	@Expose
	private String publicType;
	@Expose
	private String status;
	@Expose
	private String projectAddress;
	@Expose
	private Double accountValue = 0.000;
	@Expose
	private String account;
	@Expose
	private int accountId;

	public void setId(int id) {
		this.id = id;
	}

	public void setItemAppellation(String itemAppellation) {
		this.itemAppellation = itemAppellation;
	}

	public void setItemSymbol(String itemSymbol) {
		this.itemSymbol = itemSymbol;
	}

	public void setItemIcon(String itemIcon) {
		this.itemIcon = itemIcon;
	}

	public void setItemAddress(String itemAddress) {
		this.itemAddress = itemAddress;
	}

	public void setPublicType(String publicType) {
		this.publicType = publicType;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setProjectAddress(String projectAddress) {
		this.projectAddress = projectAddress;
	}

	public void setAccountValue(Double accountValue) {
		this.accountValue = accountValue;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getId() {
		return id;
	}

	public String getItemAppellation() {
		return itemAppellation;
	}

	public String getItemSymbol() {
		return itemSymbol;
	}

	public String getItemIcon() {
		return itemIcon;
	}

	public String getItemAddress() {
		return itemAddress;
	}

	public String getPublicType() {
		return publicType;
	}

	public String getStatus() {
		return status;
	}

	public String getProjectAddress() {
		return projectAddress;
	}

	public Double getAccountValue() {
		return accountValue;
	}

	public String getAccount() {
		return account;
	}

	public int getAccountId() {
		return accountId;
	}
}
