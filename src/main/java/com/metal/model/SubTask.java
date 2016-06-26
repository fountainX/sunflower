package com.metal.model;

import java.util.Date;

import com.metal.common.Constants;

public class SubTask {

	private long sub_task_id;
	private long task_id;
	private int platform;
	private String url;
	private int status;
	private Date start_time;
	private Date end_time;
	private int reset_hour;
	private int count;
	
	public long getSub_task_id() {
		return sub_task_id;
	}
	public void setSub_task_id(long sub_task_id) {
		this.sub_task_id = sub_task_id;
	}
	public long getTask_id() {
		return task_id;
	}
	public void setTask_id(long task_id) {
		this.task_id = task_id;
	}
	public int getPlatform() {
		return platform;
	}
	public void setPlatform(int platform) {
		this.platform = platform;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	
	public int getReset_hour() {
		return reset_hour;
	}
	public void setReset_hour(int reset_hour) {
		this.reset_hour = reset_hour;
	}

	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public String getPlatformStr() {
		switch (this.platform) {
		case Constants.PLATFORM_WEIBO:
			return "微博";
		case Constants.PLATFORM_WEIXIN:
			return "微信";
		default:
			return "未知";
		}
	}

	public String getStatusStr() {
		switch (this.status) {
		case Constants.TASK_STATUS_INIT:
			return "初始";
		case Constants.TASK_STATUS_RUNNING:
			return "运行中";
		case Constants.TASK_STATUS_FINISH:
			return "完成";
		case Constants.TASK_STATUS_STOP:
			return "手动结束";
		case Constants.TASK_STATUS_EXSTOP:
			return "异常结束";
		default:
			return "未知";
		}
	}

}
