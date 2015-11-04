package com.tarena.bigdata.cloudnote.entity;

import java.util.Date;


public class Notebook {

	private String cnNotebookId;	// 笔记本ID
	private String cnUserId;		// 用户ID
	private String cnNotebookTypeId;// 笔记本类型ID
	private String cnNotebookName;	// 笔记本名
	private String cnNotebookDesc;	// 笔记本说明
	private Date cnNotebookCreatetime;//笔记本创建时间

	public String getCnNotebookId() {
		return cnNotebookId;
	}

	public void setCnNotebookId(String cnNotebookId) {
		this.cnNotebookId = cnNotebookId;
	}

	public String getCnUserId() {
		return cnUserId;
	}

	public void setCnUserId(String cnUserId) {
		this.cnUserId = cnUserId;
	}

	public String getCnNotebookTypeId() {
		return cnNotebookTypeId;
	}

	public void setCnNotebookTypeId(String cnNotebookTypeId) {
		this.cnNotebookTypeId = cnNotebookTypeId;
	}

	public String getCnNotebookName() {
		return cnNotebookName;
	}

	public void setCnNotebookName(String cnNotebookName) {
		this.cnNotebookName = cnNotebookName;
	}

	public String getCnNotebookDesc() {
		return cnNotebookDesc;
	}

	public void setCnNotebookDesc(String cnNotebookDesc) {
		this.cnNotebookDesc = cnNotebookDesc;
	}

	public Date getCnNotebookCreatetime() {
		return cnNotebookCreatetime;
	}

	public void setCnNotebookCreatetime(Date cnNotebookCreatetime) {
		this.cnNotebookCreatetime = cnNotebookCreatetime;
	}

	
}