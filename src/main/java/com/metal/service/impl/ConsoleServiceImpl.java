package com.metal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.metal.common.Constants;
import com.metal.dao.ConsoleDao;
import com.metal.model.SubVideoTaskBean;
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

}
