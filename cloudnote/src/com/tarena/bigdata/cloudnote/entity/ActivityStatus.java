package com.tarena.bigdata.cloudnote.entity;

public class ActivityStatus {

	private String cnActivityStatus_id;// 活动状态ID
	private String cnActivityId;// 活动ID
	private String cnActivityStatusCode;// 活动状态Code
	private String cnActivityStatusName;// 活动状态名称

	public String getCnActivityStatus_id() {
		return cnActivityStatus_id;
	}

	public void setCnActivityStatus_id(String cnActivityStatus_id) {
		this.cnActivityStatus_id = cnActivityStatus_id;
	}

	public String getCnActivityId() {
		return cnActivityId;
	}

	public void setCnActivityId(String cnActivityId) {
		this.cnActivityId = cnActivityId;
	}

	public String getCnActivityStatusCode() {
		return cnActivityStatusCode;
	}

	public void setCnActivityStatusCode(String cnActivityStatusCode) {
		this.cnActivityStatusCode = cnActivityStatusCode;
	}

	public String getCnActivityStatusName() {
		return cnActivityStatusName;
	}

	public void setCnActivityStatusName(String cnActivityStatusName) {
		this.cnActivityStatusName = cnActivityStatusName;
	}

}