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
	<a href="/console/index">首页</a> >> <a href="/console/tasks">搜索任务列表</a>
	<form action="/console/insert_task_post" method="post">
		<div>
			<label for="keyword">关键字: </label>
			<input type="text" name="keyword" style="width:300px" />
		</div>
		<div>
			<input type="submit" value="添加" />
		</div>
	</form>
</body>
</html>