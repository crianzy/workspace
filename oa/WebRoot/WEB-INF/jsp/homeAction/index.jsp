<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>OA</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/script/jquery_treeview/jquery.cookie.js"></script>
<%@ include file="/WEB-INF/jsp/public/common.jspf"%>
</head>


	<frameset rows="100,*,25" framespacing=0 border=0 frameborder="0">
		<frame noresize name="TopMenu" scrolling="no" src="${pageContext.request.contextPath}/homeAction_top.action">
		<frameset cols="180,*" id="resize">
			<frame noresize name="menu" scrolling="yes" src="${pageContext.request.contextPath}/homeAction_left.action">
			<frame noresize name="right" scrolling="yes" src="${pageContext.request.contextPath}/homeAction_right.action">
		</frameset>
		<frame noresize name="status_bar" scrolling="no" src="${pageContext.request.contextPath}/homeAction_bottom.action">
	</frameset>

	<noframes>
		<body>
		</body>
	</noframes>
</html>
