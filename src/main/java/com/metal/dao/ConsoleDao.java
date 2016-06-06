package com.metal.dao;

import java.util.List;

import com.metal.model.SubTask;
import com.metal.model.SubVideoTaskBean;
import com.metal.model.Task;
import com.metal.model.VideoTaskBean;

public interface ConsoleDao {
	
	public List<VideoTaskBean> getVideoTasks();

	public List<SubVideoTaskBean> getSubVideoTasks(long vid);

	public long createTVShow(String title);
	
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
	
	public SubTask getSubTaskById(long sub_task_id);
	
	public void createTask(Task task);
	
	public void revertTask(long task_id);
	
	public void revertSubTask(long subTaskId);
	
	public void stopTask(long task_id);
}
