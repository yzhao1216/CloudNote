package com.tarena.bigdata.cloudnote.entity;


public class NoteActivity {


	private String cnNoteActivityId;//投稿ID
	private String cnActivityId;//活动ID
	private String cnNoteId;//笔记ID
	private int cnNoteActivityUp;//投稿赞:增加数
	private int cnNoteActivityDown;//投稿踩:增加数
	private String cnNoteActivityTitle;//
	private String cnNoteActivityBody;//
	public String getCnNoteActivityId() {
		return cnNoteActivityId;
	}
	public void setCnNoteActivityId(String cnNoteActivityId) {
		this.cnNoteActivityId = cnNoteActivityId;
	}
	public String getCnActivityId() {
		return cnActivityId;
	}
	public void setCnActivityId(String cnActivityId) {
		this.cnActivityId = cnActivityId;
	}
	public String getCnNoteId() {
		return cnNoteId;
	}
	public void setCnNoteId(String cnNoteId) {
		this.cnNoteId = cnNoteId;
	}
	public int getCnNoteActivityUp() {
		return cnNoteActivityUp;
	}
	public void setCnNoteActivityUp(int cnNoteActivityUp) {
		this.cnNoteActivityUp = cnNoteActivityUp;
	}
	public int getCnNoteActivityDown() {
		return cnNoteActivityDown;
	}
	public void setCnNoteActivityDown(int cnNoteActivityDown) {
		this.cnNoteActivityDown = cnNoteActivityDown;
	}
	public String getCnNoteActivityTitle() {
		return cnNoteActivityTitle;
	}
	public void setCnNoteActivityTitle(String cnNoteActivityTitle) {
		this.cnNoteActivityTitle = cnNoteActivityTitle;
	}
	public String getCnNoteActivityBody() {
		return cnNoteActivityBody;
	}
	public void setCnNoteActivityBody(String cnNoteActivityBody) {
		this.cnNoteActivityBody = cnNoteActivityBody;
	}



}