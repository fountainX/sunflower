package com.metal.model;

import java.util.Date;

import com.metal.common.Constants;

public class Task {
	private long task_id;
	private String key_word;
	private int status;
	private Date start_time;
	private Date end_time;
	public long getTask_id() {
		return task_id;
	}
	public void setTask_id(long task_id) {
		this.task_id = task_id;
	}
	public String getKey_word() {
		return key_word;
	}
	public void setKey_word(String key_word) {
		this.key_word = key_word;
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
	
	public String getStatusStr() {
		switch(this.status) {
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
