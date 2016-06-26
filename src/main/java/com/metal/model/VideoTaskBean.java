package com.metal.model;

import java.sql.Time;
import java.util.Date;

import com.metal.common.Constants;

public class VideoTaskBean {
	private long vid;
	private String url;
	private int platform;
	private String title;
	private int status;
	private Date start_time;
	private Date end_time;
	private long tv_id;
	private Time reset_time;
	private int count;

	public long getVid() {
		return vid;
	}

	public void setVid(long vid) {
		this.vid = vid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPlatform() {
		return platform;
	}

	public void setPlatform(int platform) {
		this.platform = platform;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public long getTv_id() {
		return tv_id;
	}

	public void setTv_id(long tv_id) {
		this.tv_id = tv_id;
	}
	
	public Time getReset_time() {
		return reset_time;
	}

	public void setReset_time(Time reset_time) {
		this.reset_time = reset_time;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getPlatformStr() {
		switch (this.platform) {
		case Constants.VIDEO_PLATFORM_TENGXUN:
			return "腾讯";
		case Constants.VIDEO_PLATFORM_YOUTU:
			return "优土";
		case Constants.VIDEO_PLATFORM_AQIYI:
			return "爱奇艺";
		case Constants.VIDEO_PLATFORM_LETV:
			return "乐视";
		case Constants.VIDEO_PLATFORM_SOHU:
			return "搜狐";
		default:
			return "未知";
		}
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
	
	@Override
	public String toString() {
		return "VideoTaskBean [vid=" + vid + ", url=" + url + ", platform="
				+ platform + ", title=" + title + ", status=" + status
				+ ", start_time=" + start_time + ", end_time=" + end_time + "]";
	}
}
