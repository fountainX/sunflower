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
	<a href="/console/index">首页</a> >> <a href="/console/videotasks">视屏任务列表</a>
	
	<form action="/console/insert_video_task_post" method="post">
		<div>
			<label for="url">URL: </label>
			<input type="text" name="url" style="width:300px" />
		</div>
		<div>
			<label for="platform">平台: </label>
			<select name="platform">
				<option value="0">腾讯</option>
				<option value="1">优土</option>
				<option value="2" selected>爱奇艺</option>
				<option value="3">乐视</option>
				<option value="4">搜狐</option>
			</select>
		</div>
		<div>
			<label for="title">标题: </label>
			<input type="text" name="title" style="width:300px" />
		</div>
		<div>
			<input type="submit" value="添加" />
		</div>
	</form>
</body>
</html>