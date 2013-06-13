<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
	<title>Itcast OA</title>
    <%@ include file="/WEB-INF/jsp/public/common.jspf" %>
	<link href="${pageContext.request.contextPath}/style/blue/login.css" type=text/css rel=stylesheet>
	<script type="text/javascript">
	
		$(function(){
			document.forms[0].loginName.focus();
		});
		
		if(window.parent != window){
			window.parent.location.reload(true);
		}
	</script>
</head>

<BODY LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0 CLASS=PageBody >
<FORM METHOD="post" NAME="actForm" ACTION="userAction_login">
    <DIV ID="CenterAreaBg">
        <DIV ID="CenterArea">
            <DIV ID="LogoImg"><IMG BORDER="0" SRC="style/blue/images/logo.png" /></DIV>
            
            <DIV ID="LoginInfo">
                <TABLE BORDER=0 CELLSPACING=0 CELLPADDING=0 width=100%>
             	   <tr>
                		<td colspan="3"><!-- 显示错误 -->
							<font color="red"><s:fielderror name="login"/></font>
                		</td>
                	</tr>
                    <TR>
                        <TD width=45 CLASS="Subject"><IMG BORDER="0" SRC="style/blue/images/login/userId.gif" /></TD>
                        <TD>
                        	<%-- <s:textfield name="loginName" cssClass="TextField"/> --%>
                        	<INPUT SIZE="20" CLASS="TextField" TYPE="text" NAME="loginName" value="${loginName }" />
                        </TD>
                        <TD ROWSPAN="2" STYLE="padding-left:10px;"><INPUT TYPE="image" SRC="style/blue/images/login/userLogin_button.gif"/></TD>
                    </TR>
                    <TR>
                        <TD CLASS="Subject"><IMG BORDER="0" SRC="style/blue/images/login/password.gif" /></TD>
                        <TD>
                        	<INPUT SIZE="20" CLASS="TextField" TYPE="password" NAME="password" /> 
                        	<%-- <s:password name="password" cssClass="TextField"></s:password> --%>
                        </TD>
                    </TR>
                </TABLE>
            </DIV>
            <DIV ID="CopyRight"><A HREF="javascript:void(0)">&copy; 2010 版权所有 itcast</A></DIV>
        </DIV>
    </DIV>
</FORM>
</BODY>


</html>

