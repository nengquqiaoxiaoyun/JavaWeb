<%@ page import="java.io.PrintWriter" %><%--
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

<%
    out.print("张三");
    PrintWriter writer = response.getWriter();
    out.write("张三");
    writer.println("张三");
%>

<br/>

<%="李四"%>


姓名：<%=request.getParameter("username")%>         <br/>
response: <%=response.getCharacterEncoding()%> <br/>
request: <%=request.getCharacterEncoding()%>

</body>
</html>
