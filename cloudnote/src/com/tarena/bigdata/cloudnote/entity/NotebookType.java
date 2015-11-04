package com.tarena.bigdata.cloudnote.entity;

public class NotebookType {

	private String cnNotebookTypeId;// 笔记本类型ID
	private String cnNotebookTypeCode;// 笔记本类型Code
	private String cnNotebookTypeName;// 笔记本类型名称
	private String cnUserToken;// 笔记本类型说明

	public String getCnNotebookTypeId() {
		return cnNotebookTypeId;
	}

	public void setCnNotebookTypeId(String cnNotebookTypeId) {
		this.cnNotebookTypeId = cnNotebookTypeId;
	}

	public String getCnNotebookTypeCode() {
		return cnNotebookTypeCode;
	}

	public void setCnNotebookTypeCode(String cnNotebookTypeCode) {
		this.cnNotebookTypeCode = cnNotebookTypeCode;
	}

	public String getCnNotebookTypeName() {
		return cnNotebookTypeName;
	}

	public void setCnNotebookTypeName(String cnNotebookTypeName) {
		this.cnNotebookTypeName = cnNotebookTypeName;
	}

	public String getCnUserToken() {
		return cnUserToken;
	}

	public void setCnUserToken(String cnUserToken) {
		this.cnUserToken = cnUserToken;
	}

}