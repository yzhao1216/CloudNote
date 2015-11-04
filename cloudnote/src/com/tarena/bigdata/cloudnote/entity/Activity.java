package com.tarena.bigdata.cloudnote.entity;

public class Activity {
	private String cnActivityId;// 活动ID
	private String cnActivityTitle;// 活动标题
	private String cnActivityBody;// 活动介绍(html片段)
	private long cnActivityEndTime;// 活动结束时间

	public String getCnActivityId() {
		return cnActivityId;
	}

	public void setCnActivityId(String cnActivityId) {
		this.cnActivityId = cnActivityId;
	}

	public String getCnActivityTitle() {
		return cnActivityTitle;
	}

	public void setCnActivityTitle(String cnActivityTitle) {
		this.cnActivityTitle = cnActivityTitle;
	}

	public String getCnActivityBody() {
		return cnActivityBody;
	}

	public void setCnActivityBody(String cnActivityBody) {
		this.cnActivityBody = cnActivityBody;
	}

	public long getCnActivityEndTime() {
		return cnActivityEndTime;
	}

	public void setCnActivityEndTime(long cnActivityEndTime) {
		this.cnActivityEndTime = cnActivityEndTime;
	}

}