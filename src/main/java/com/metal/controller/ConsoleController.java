package com.metal.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.metal.model.SubVideoTaskBean;
import com.metal.model.VideoTaskBean;
import com.metal.service.ConsoleService;

@Controller
@RequestMapping("/console")
public class ConsoleController {

	@Autowired
	private ConsoleService consoleService;

	@RequestMapping("/test")
	@ResponseBody
	String test() {
		return "ok";
	}

	@RequestMapping("/index")
	String welcome() {
		return "index";
	}

	@RequestMapping("/videotasks")
	String getVideoTasks(Map<String, Object> model) {
		List<VideoTaskBean> videoTasks = consoleService.getVideoTasks();
		model.put("videotasks", videoTasks);
		return "videotasks";
	}

	@RequestMapping("/subvideotasks/{vid}")
	String getSubVideoTasks(@PathVariable("vid") long vid,
			Map<String, Object> model) {
		List<SubVideoTaskBean> subVideoTasks = consoleService
				.getSubVideoTasks(vid);
		model.put("subvideotasks", subVideoTasks);
		return "subvideotasks";
	}

	@RequestMapping("/insertvideotask")
	String insertVideoTask() {
		return "insertvideotask";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/insert_video_task_post")
	@ResponseBody
	void postInsertVideoTask(@RequestParam("url") String url,
			@RequestParam("platform") int platform,
			@RequestParam("title") String title, HttpServletResponse response) throws IOException {
		if(StringUtils.isEmpty(url) || StringUtils.isEmpty(title)) {
			response.sendRedirect("/console/videotasks");
		} else {
			VideoTaskBean videoTask = new VideoTaskBean();
			videoTask.setUrl(url);
			videoTask.setPlatform(platform);
			videoTask.setTitle(title);
			consoleService.createVideoTasks(videoTask);
			response.sendRedirect("/console/videotasks");
		}
	}
	
	@RequestMapping("/revertvideotask/{vid}")
	@ResponseBody
	void revert(@PathVariable("vid") long vid, HttpServletResponse response) throws IOException {
		consoleService.revertVideoTask(vid);
		response.sendRedirect("/console/videotasks");
	}
	
	@RequestMapping("/revertsubvideotask/{subvid}")
	@ResponseBody
	void revertSubVideoTask(@PathVariable("subvid") long subVid, HttpServletResponse response) throws IOException {
		consoleService.revertSubVideoTask(subVid);
		SubVideoTaskBean bean = consoleService.getSubVideoTaskById(subVid);
		response.sendRedirect("/console/subvideotasks/" + bean.getVid());
	}
	
	@RequestMapping("/stopvideotask/{vid}")
	@ResponseBody
	void stopVideoTask(@PathVariable("vid") long vid, HttpServletResponse response) throws IOException {
		consoleService.stopVideoTask(vid);
		response.sendRedirect("/console/videotasks");
	}
}