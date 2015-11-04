package com.tarena.bigdata.cloudnote.entity;

public class Share {
	private String cnShareId;// 共享ID
	private String cnShareTitle;// 共享标题
	private String cnShareBody;// 共享内容
	private String cnNoteId;// 笔记ID
	public String getCnShareId() {
		return cnShareId;
	}
	public void setCnShareId(String cnShareId) {
		this.cnShareId = cnShareId;
	}
	public String getCnShareTitle() {
		return cnShareTitle;
	}
	public void setCnShareTitle(String cnShareTitle) {
		this.cnShareTitle = cnShareTitle;
	}
	public String getCnShareBody() {
		return cnShareBody;
	}
	public void setCnShareBody(String cnShareBody) {
		this.cnShareBody = cnShareBody;
	}
	public String getCnNoteId() {
		return cnNoteId;
	}
	public void setCnNoteId(String cnNoteId) {
		this.cnNoteId = cnNoteId;
	}

	

}