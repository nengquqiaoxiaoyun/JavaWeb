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

cookie:
<form action="cookieServlet" method="get">
    <%
        Cookie[] cookies = request.getCookies();
        out.print(cookies.length + "<br/>");
        for (Cookie cookie : cookies) {
            out.print(cookie.getName() + ": " + cookie.getValue() + "<br/>");
        }
    %>
    <input type="submit">
</form>


</body>
</html>
