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
	<table border=1>
		<tr>
		    <th>ID</th>
		    <th>任务ID</th>
			<th>平台</th>
			<th>URL</th>
			<th>状态</th>
			<th>添加时间</th>
			<!-- <th>结束时间</th> -->
		</tr>
		<c:forEach var="subtask" items="${subtasks }">
			<tr>
				<td><c:out value="${subtask.sub_task_id }"/></td>
				<td><c:out value="${subtask.task_id }"/></td>
				<td><c:out value="${subtask.platformStr }"/></td>
				<td><c:out value="${subtask.url }"/></td>
				<td><c:out value="${subtask.statusStr }"/></td>
				<td><c:out value="${subtask.start_time }"/></td>
				<%-- <td><c:out value="${subtask.last_update_time }"/></td> --%>
			</tr>
		</c:forEach>
	</table>
</body>
</html>