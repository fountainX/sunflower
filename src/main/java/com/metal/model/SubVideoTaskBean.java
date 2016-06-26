package com.metal.model;

import java.util.Date;

import com.metal.common.Constants;

/**
 * sub video task bean
 * 
 * @author wxp
 *
 */
public class SubVideoTaskBean {
	private long sub_vid;
	private long vid;
	private String page_url;
	private int platform;
	private String title;
	private int status;
	private Date add_time;
	private Date last_update_time;
	private int count;

	public long getSub_vid() {
		return sub_vid;
	}

	public void setSub_vid(long sub_vid) {
		this.sub_vid = sub_vid;
	}

	public long getVid() {
		return vid;
	}

	public void setVid(long vid) {
		this.vid = vid;
	}

	public String getPage_url() {
		return page_url;
	}

	public void setPage_url(String page_url) {
		this.page_url = page_url;
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

	public Date getAdd_time() {
		return add_time;
	}

	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}

	public Date getLast_update_time() {
		return last_update_time;
	}

	public void setLast_update_time(Date last_update_time) {
		this.last_update_time = last_update_time;
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

	@Override
	public String toString() {
		return "SubVideoTaskBean [sub_vid=" + sub_vid + ", vid=" + vid
				+ ", page_url=" + page_url + ", platform=" + platform
				+ ", title=" + title + ", status=" + status + ", add_time="
				+ add_time + ", last_update_time=" + last_update_time + "]";
	}
}
