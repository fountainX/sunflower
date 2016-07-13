package com.metal.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.metal.common.Constants;
import com.metal.dao.ConsoleDao;
import com.metal.model.SubTask;
import com.metal.model.SubVideoTaskBean;
import com.metal.model.Task;
import com.metal.model.VideoTaskBean;
import com.metal.service.ConsoleService;

@Component
public class ConsoleServiceImpl implements ConsoleService {

	@Autowired
	private ConsoleDao consoleDao;
	
	@Override
	public List<VideoTaskBean> getVideoTasks() {
		return consoleDao.getVideoTasks();
	}

	@Override
	public List<SubVideoTaskBean> getSubVideoTasks(long vid) {
		return consoleDao.getSubVideoTasks(vid);
	}

	@Override
	public void createVideoTasks(VideoTaskBean videoTaskBean) {
		long tv_id = consoleDao.createTVShow(videoTaskBean.getTitle());
		videoTaskBean.setTv_id(tv_id);
		consoleDao.createVideoTasks(videoTaskBean);
	}

	@Override
	public VideoTaskBean getVideoTaskById(long vid) {
		return consoleDao.getVideoTaskById(vid);
	}

	@Override
	public SubVideoTaskBean getSubVideoTaskById(long subVid) {
		return consoleDao.getSubVideoTaskById(subVid);
	}
	
	@Override
	public void revertVideoTask(long vid) {
		VideoTaskBean bean = consoleDao.getVideoTaskById(vid);
		if(bean.getStatus() != Constants.TASK_STATUS_INIT && bean.getStatus() != Constants.TASK_STATUS_RUNNING) {
			consoleDao.revertVideoTask(vid);
		}
	}

	@Override
	public void revertSubVideoTask(long subVid) {
		SubVideoTaskBean bean = consoleDao.getSubVideoTaskById(subVid);
		if(bean.getStatus() != Constants.TASK_STATUS_INIT && bean.getStatus() != Constants.TASK_STATUS_RUNNING) {
			consoleDao.revertSubVideoTask(subVid);
		}
	}

	@Override
	public void stopVideoTask(long vid) {
		consoleDao.stopVideoTask(vid);
	}
	
	@Override
	public int getVideoCommentCount(long vid) {
		return consoleDao.getVideoCommentCount(vid);
	}

	@Override
	public int getSubVideoCommentCount(long subVid) {
		return consoleDao.getSubVideoCommentCount(subVid);
	}

	@Override
	public void updateVideoTask(VideoTaskBean video) {
		consoleDao.updateVideoTask(video);
	}
	
	@Override
	public void removeVideoTask(long vid) {
		consoleDao.removeVideoTask(vid);
	}
	
	@Override
	public List<Task> getTasks() {
		return consoleDao.getTasks();
	}

	@Override
	public List<SubTask> getSubTasks(long taskId) {
		return consoleDao.getSubTasks(taskId);
	}

	@Override
	public Task getTaskById(long task_id) {
		return consoleDao.getTaskById(task_id);
	}

	@Override
	public SubTask getSubTaskById(long subTaskId) {
		return consoleDao.getSubTaskById(subTaskId);
	}
	
	@Override
	public void createTask(Task task) {
		consoleDao.createTask(task);
	}
	
	@Override
	public void revertTask(long task_id) {
		Task task = consoleDao.getTaskById(task_id);
		if(task.getStatus() != Constants.TASK_STATUS_INIT && task.getStatus() != Constants.TASK_STATUS_RUNNING) {
			consoleDao.revertTask(task_id);
		}
	}

	@Override
	public void revertSubTask(long subTaskId) {
		SubTask subTask = consoleDao.getSubTaskById(subTaskId);
		if(subTask.getStatus() != Constants.TASK_STATUS_INIT && subTask.getStatus() != Constants.TASK_STATUS_RUNNING) {
			consoleDao.revertSubTask(subTaskId);
		}
	}

	@Override
	public void stopTask(long taskId) {
		consoleDao.stopTask(taskId);
	}

	@Override
	public void resetHour(long subTaskId, int hour) {
		consoleDao.resetHour(subTaskId, hour);
	}

	@Override
	public void removeTask(long task_id) {
		consoleDao.removeTask(task_id);
	}

	@Override
	public void startQQDanmuTask(long vid) {
		List<VideoTaskBean> videoTasks = consoleDao.getVideoTasks(Constants.VIDEO_PLATFORM_TENGXUN);
		int runCount = 0;
		for(VideoTaskBean bean : videoTasks) {
			if(bean.getBarrage_status() == Constants.TASK_STATUS_RUNNING) {
				runCount++;
			}
		}
		if(runCount > 0) {
			// TODO
			return;
		} else {
//			Process process =null;
		    String command = "nohup /Users/wxp/Documents/tmp/test.sh " + vid + " 30";
		    try {
				Runtime.getRuntime().exec(command);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
