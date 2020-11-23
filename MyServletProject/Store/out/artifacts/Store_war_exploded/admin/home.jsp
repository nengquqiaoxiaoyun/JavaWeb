<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<c:set value="${pageContext.request.contextPath}" var="root"></c:set>
<html>
<head>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>
        body {
            SCROLLBAR-ARROW-COLOR: #ffffff;
            SCROLLBAR-BASE-COLOR: #dee3f7;
        }
    </style>
</head>

<frameset rows="103,*,43" frameborder=0 border="0" framespacing="0">
    <frame src="${root}/admin/top.jsp" name="topFrame" scrolling="NO" noresize>
    <frameset cols="159,*" frameborder="0" border="0" framespacing="0">
        <frame src="${root}/admin/left.jsp" name="leftFrame" noresize scrolling="YES">
        <frame src="${root}/admin/welcome.jsp" name="mainFrame">
    </frameset>
    <frame src="${root}/admin/bottom.jsp" name="bottomFrame" scrolling="NO" noresize>
</frameset>
</html>
