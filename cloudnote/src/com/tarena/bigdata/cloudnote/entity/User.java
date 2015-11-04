package com.tarena.bigdata.cloudnote.entity;

public class User {

	private String cnUserId;// 用户ID
	private String cnUserName;// 用户名
	private String cnUserPassword;// 密码
	private String cnUserToken;// 令牌
	private String cnUserDesc;// 说明

	public String getCnUserId() {
		return cnUserId;
	}

	public void setCnUserId(String cnUserId) {
		this.cnUserId = cnUserId;
	}

	public String getCnUserName() {
		return cnUserName;
	}

	public void setCnUserName(String cnUserName) {
		this.cnUserName = cnUserName;
	}

	public String getCnUserPassword() {
		return cnUserPassword;
	}

	public void setCnUserPassword(String cnUserPassword) {
		this.cnUserPassword = cnUserPassword;
	}

	public String getCnUserToken() {
		return cnUserToken;
	}

	public void setCnUserToken(String cnUserToken) {
		this.cnUserToken = cnUserToken;
	}

	public String getCnUserDesc() {
		return cnUserDesc;
	}

	public void setCnUserDesc(String cnUserDesc) {
		this.cnUserDesc = cnUserDesc;
	}

}