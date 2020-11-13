<%--
  Created by IntelliJ IDEA.
  User: wentimei
  Date: 2020/11/12
  Time: 上午9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="cookie2.jsp" >
    <%
        Cookie cookie = new Cookie("name", "张三");
        Cookie cookie1 = new Cookie("name1", "lisi");

        response.addCookie(cookie);
        response.addCookie(cookie1);

    %>
    <input type="submit">
</form>


</body>
</html>
