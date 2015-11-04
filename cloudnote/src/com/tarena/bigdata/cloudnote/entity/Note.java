package com.tarena.bigdata.cloudnote.entity;


public class Note {

	private String cnNoteId;// 笔记ID
	private String cnNotebookId;// 笔记本ID
	private String cnUserId;// 用户ID
	private String cnNoteStatusId;// 笔记状态ID:备用
	private String cnNoteTypeId;// 笔记本类型ID：备用
	private String cnNoteTitle;// 笔记标题
	private String cnNoteBody;// 笔记内容
	private long cnNoteCreateTime;// 笔记创建时间
	private long cnNoteLastModifyTime;// 笔记最近修改时间

	public String getCnNoteId() {
		return cnNoteId;
	}

	public void setCnNoteId(String cnNoteId) {
		this.cnNoteId = cnNoteId;
	}

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

	public String getCnNoteStatusId() {
		return cnNoteStatusId;
	}

	public void setCnNoteStatusId(String cnNoteStatusId) {
		this.cnNoteStatusId = cnNoteStatusId;
	}

	public String getCnNoteTypeId() {
		return cnNoteTypeId;
	}

	public void setCnNoteTypeId(String cnNoteTypeId) {
		this.cnNoteTypeId = cnNoteTypeId;
	}

	public String getCnNoteTitle() {
		return cnNoteTitle;
	}

	public void setCnNoteTitle(String cnNoteTitle) {
		this.cnNoteTitle = cnNoteTitle;
	}

	public String getCnNoteBody() {
		return cnNoteBody;
	}

	public void setCnNoteBody(String cnNoteBody) {
		this.cnNoteBody = cnNoteBody;
	}

	public long getCnNoteCreateTime() {
		return cnNoteCreateTime;
	}

	public void setCnNoteCreateTime(long cnNoteCreateTime) {
		this.cnNoteCreateTime = cnNoteCreateTime;
	}

	public long getCnNoteLastModifyTime() {
		return cnNoteLastModifyTime;
	}

	public void setCnNoteLastModifyTime(long cnNoteLastModifyTime) {
		this.cnNoteLastModifyTime = cnNoteLastModifyTime;
	}

}