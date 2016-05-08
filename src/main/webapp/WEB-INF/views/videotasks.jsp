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
		<a href="/console/videotasks">>首页</a>
	</div>
	<div>
		<a href="/console/insertvideotask">>>添加新的视频任务</a>
	</div>
	<table border=1>
		<tr>
		    <th>ID</th>
			<th>平台</th>
			<th>标题</th>
			<th>URL</th>
			<th>状态</th>
			<th>开始时间</th>
			<!-- <th>结束时间</th> -->
			<th>详细</th>
			<th>操作</th>
		</tr>
		<c:forEach var="videotask" items="${videotasks }">
			<tr>
				<td><c:out value="${videotask.vid }"/></td>
				<td><c:out value="${videotask.platformStr }"/></td>
				<td><c:out value="${videotask.title }"/></td>
				<td><c:out value="${videotask.url }"/></td>
				<td><c:out value="${videotask.statusStr }"/></td>
				<td><c:out value="${videotask.start_time }"/></td>
				<%-- <td><c:out value="${videotask.end_time }"/></td> --%>
				<td><a href="/console/subvideotasks/${videotask.vid }">详细</a></td>
				<c:choose>
					<c:when test="${videotask.status == 0 || videotask.status == 1 }">
						<td><a href="javascript:stop_confirm(${videotask.vid })">停止</a></td>
					</c:when>
					<c:otherwise>
						<td><a href="javascript:revert_confirm(${videotask.vid });">重置</a></td>
					</c:otherwise>
				</c:choose>
			</tr>
		</c:forEach>
	</table>
	<script type="text/javascript">
		function revert_confirm(vid) {
		  	var r=confirm("确定重置吗？")
		  	if (r==true) {
		    	window.location.href = "/console/revertvideotask/" + vid;
		  	} else {
		    	// nothing
			}
		}
		function stop_confirm(vid) {
		  	var r=confirm("确定停止吗？")
		  	if (r==true) {
		    	window.location.href = "/console/stopvideotask/" + vid;
		  	} else {
		    	// nothing
			}
		}
	</script>
</body>
</html>