<%--
  Created by IntelliJ IDEA.
  User: wentimei
  Date: 2020/11/13
  Time: 上午9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


cookie333: <br/>
<%


    Cookie[] cookies = request.getCookies();
    if(cookies != null) {
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            String value = cookie.getValue();
            out.print("name: " + name + ", value: " + value + "<br/>");
        }
    }

%>

</body>
</html>
