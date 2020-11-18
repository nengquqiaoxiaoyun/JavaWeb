<%--
  Created by IntelliJ IDEA.
  User: wentimei
  Date: 2020/11/18
  Time: 下午4:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

姓名：<%=request.getParameter("username")%>         <br/>
response: <%=response.getCharacterEncoding()%> <br/>
request: <%=request.getCharacterEncoding()%>

</body>
</html>
