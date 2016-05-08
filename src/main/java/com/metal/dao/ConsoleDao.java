package com.metal.dao;

import java.util.List;

import com.metal.model.SubVideoTaskBean;
import com.metal.model.VideoTaskBean;

public interface ConsoleDao {
	
	public List<VideoTaskBean> getVideoTasks();

	public List<SubVideoTaskBean> getSubVideoTasks(long vid);

	public void createVideoTasks(VideoTaskBean videoTaskBean);
	
	public VideoTaskBean getVideoTaskById(long vid);
	
	public SubVideoTaskBean getSubVideoTaskById(long subVid);
	
	public void revertVideoTask(long vid);
	
	public void revertSubVideoTask(long subVid);
	
	public void stopVideoTask(long vid);
	
	public int getVideoCommentCount(long vid);
	
	public int getSubVideoCommentCount(long subVid);
}
