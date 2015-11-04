package com.tarena.bigdata.cloudnote.entity;

import java.io.Serializable;


/**
 * 请求返回公共消息封装体
 * @author YHT
 *
 */
public class Response implements Serializable{

	private static final long serialVersionUID = -8670328647557340122L;
	private int status = 1;  	// -1失败1成功
	private Object resource;	// 返回数据
	private String message;		// 消息内容
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Object getResource() {
		return resource;
	}
	public void setResource(Object resource) {
		this.resource = resource;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
