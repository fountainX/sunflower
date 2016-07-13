package com.metal.service;

import java.util.List;

import com.metal.model.SubTask;
import com.metal.model.SubVideoTaskBean;
import com.metal.model.Task;
import com.metal.model.VideoTaskBean;

public interface ConsoleService {

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

	public void updateVideoTask(VideoTaskBean video);
	
	
	public List<Task> getTasks();
	
	public List<SubTask> getSubTasks(long taskId);
	
	public Task getTaskById(long task_id);
	
	public SubTask getSubTaskById(long subTaskId);
	
	public void createTask(Task task);
	
	public void revertTask(long task_id);
	
	public void revertSubTask(long subTaskId);
	
	public void stopTask(long taskId);
	
	public void resetHour(long subTaskId, int hour);

	public void removeVideoTask(long vid);

	public void removeTask(long task_id);

	public void startQQDanmuTask(long vid);
}
