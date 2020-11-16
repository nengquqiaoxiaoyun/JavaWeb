<%--
  Created by IntelliJ IDEA.
  User: wentimei
  Date: 2020/11/16
  Time: 下午2:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    String id = session.getId();
    out.print("id: " +  id + "<br/>");
    Object uname = session.getAttribute("uname");
    out.print(uname);
%>



</body>
</html>
