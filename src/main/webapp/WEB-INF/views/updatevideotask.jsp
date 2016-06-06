<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/common.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="/console/index">首页</a> >> <a href="/console/videotasks">视频任务列表</a>
	
	<form action="/console/update_video_task_post" method="post">
		<div>
			<label for="url">URL: </label>
			<input type="text" name="url" style="width:300px" value="${videotask.url }" disabled/>
		</div>
		<div>
			<label for="platform">平台: </label>
			<c:out value="${videotask.platformStr }"/>
		</div>
		<div>
			<label for="title">标题: </label>
			<input type="text" name="title" style="width:300px" value="${videotask.title }" />
		</div>
		<div>
			<label for="reset-time">定时重抓: </label>
			<input type="text" name="reset-hour" style="width:50px" value="${videotask.reset_time.toString().split(':')[0] }" />
			:
			<input type="text" name="reset-min" style="width:50px" value="${videotask.reset_time.toString().split(':')[1] }" />
			: 
			<input type="text" name="reset-min" style="width:50px" value="00" disabled/>
		</div>
		<input type="hidden" name="vid" value="${videotask.vid }" />
		<div>
			<input type="submit" value="更新" />
		</div>
	</form>
</body>
</html>