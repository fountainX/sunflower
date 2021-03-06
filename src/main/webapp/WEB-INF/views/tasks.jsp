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
	<div>
		<a href="/console/index">首页</a> >> <a href="/console/tasks">搜索任务列表</a>
	</div>
	<div>
		<a href="/console/inserttask">>>添加新的搜索任务</a>
	</div>
	<table border=1>
		<colgroup>
			<col width="50">
			<col width="100">
			<col width="100">
			<col width="100">
			<col width="50">
			<col width="50">
			<col width="50">
		</colgroup>
		<tr>
		    <th>ID</th>
			<th>关键字</th>
			<th>状态</th>
			<th>开始时间</th>
			<!-- <th>结束时间</th> -->
			<th>抓取量</th>
			<th>详细</th>
			<th>操作</th>
		</tr>
		<c:forEach var="task" items="${tasks }">
			<tr>
				<td><c:out value="${task.task_id }"/></td>
				<td><c:out value="${task.key_word }"/></td>
				<td><c:out value="${task.statusStr }"/></td>
				<td><c:out value="${task.start_time }"/></td>
				<%-- <td><c:out value="${task.end_time }"/></td> --%>
				<td><c:out value="${task.count }"/></td>
				<td><a href="/console/subtasks/${task.task_id }">详细</a></td>
				<td>
					<c:choose>
						<c:when test="${task.status == 0 || task.status == 1 }">
							<a href="javascript:stop_confirm(${task.task_id })">停止</a>
						</c:when>
						<c:otherwise>
							<a href="javascript:revert_confirm(${task.task_id });">重置</a>
						</c:otherwise>
					</c:choose>
					<a href="javascript:remove_confirm(${task.task_id });">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<script type="text/javascript">
		function revert_confirm(task_id) {
		  	var r=confirm("确定重置吗？")
		  	if (r==true) {
		    	window.location.href = "/console/reverttask/" + task_id;
		  	} else {
		    	// nothing
			}
		}
		function stop_confirm(task_id) {
		  	var r=confirm("确定停止吗？")
		  	if (r==true) {
		    	window.location.href = "/console/stoptask/" + task_id;
		  	} else {
		    	// nothing
			}
		}
		function remove_confirm(task_id) {
		  	var r=confirm("确定删除任务吗？将彻底删除！")
		  	if (r==true) {
		    	window.location.href = "/console/removetask/" + task_id;
		  	} else {
		    	// nothing
			}
		}
	</script>
</body>
</html>