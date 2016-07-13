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
	<script type="text/javascript"
			src="<%=request.getContextPath()%>/static/js/jquery-1.10.2.min.js"></script>

	<a href="/console/index">首页</a> >> <a href="/console/tasks">搜索任务列表</a>
	<table border=1>
		<colgroup>
			<col width="50">
			<col width="50">
			<col width="50">
			<col>
			<col width="100">
			<col width="100">
			<col width="50">
			<col width="50">
		</colgroup>
		<tr>
		    <th>ID</th>
		    <th>任务ID</th>
			<th>平台</th>
			<th>URL</th>
			<th>状态</th>
			<th>添加时间</th>
			<th>抓取量</th>
			<th>定时</th>
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
				<td><c:out value="${subtask.count }"/></td>
				<td sid="${subtask.sub_task_id }" rehour="${subtask.reset_hour }">
					<c:out value="${subtask.reset_hour }"/>小时
					<button name="modi">修改</button>
				</td>
				<%-- <td><c:out value="${subtask.last_update_time }"/></td> --%>
			</tr>
		</c:forEach>
	</table>
	<script type="text/javascript">
		$(document).on("click", "button[name=modi]", function() {
			var node = $(this).parent("td");
			node.empty();
			node.html("<input type=\"text\" value=\"" + node.attr("rehour") + "\" style=\"width:20px;\"/>"
					+ "<button name=\"sub\">提交</button>");
		});
		$(document).on("click", "button[name=sub]", function() {
			var node = $(this).parent("td");
			var hour = node.children("input").val();
			var sid = node.attr("sid");
			$.post("/console/reset_hour",
				{
					sid : sid,
					hour : hour
				}, function() {
					location.reload();
				});
		});
	</script>
</body>
</html>