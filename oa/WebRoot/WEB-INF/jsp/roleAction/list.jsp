<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
	<title>岗位列表</title>
	<%@include file="/WEB-INF/jsp/public/common.jspf" %>
	<script type="text/javascript">
	
	</script>
</head>

<body>
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="style/images/title_arrow.gif"/> 岗位管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>
<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
            	<td width="200px">岗位名称</td>
                <td width="300px">岗位说明</td>
                <td>相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="roleList">
        	<s:iterator value="#roleList">
        	<tr class="TableDetail1 template">
				<td>${name }&nbsp;</td>
				<td>${description }&nbsp;</td>
				<td>
					<s:a action="roleAction_delete?id=%{id}" onclick="return delConfirm()">删除</s:a>
					<s:a action="roleAction_editUI?id=%{id}">修改</s:a>
					<s:a action="roleAction_setPrivilegeUI?id=%{id}">设置权限</s:a>
				</td>
			</tr>
			</s:iterator>
			<%-- <s:a action="roleAction_addUI">添加</s:a>
			<tr class="TableDetail1 template">
				<td>${role.name}&nbsp;</td>
				<td>${role.description}&nbsp;</td>
				<td><a onClick="return delConfirm()" href="list.html">删除</a>
					<a href="saveUI.html">修改</a>
					<a href="setPrivilegeUI.html">设置权限</a>
				</td>
			</tr> --%>
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <!-- <a href="saveUI.html"><img src="style/images/createNew.png" /></a> -->
            <s:a action="roleAction_addUI"><img src="style/images/createNew.png" /></s:a>
        </div>
    </div>
</div>
</body>
</html>
