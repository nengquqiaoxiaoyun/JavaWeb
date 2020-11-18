<%@ page import="com.chhoyun.junit.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: wentimei
  Date: 2020/11/18
  Time: 上午9:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    request.setAttribute("num", 2);
%>


测试标签中的scope <br/>
<c:if test="${num == 2}" var="result" scope="request">

    ${num}
    ${result}

</c:if>
<br/>

测试var的作用域 <br/>
${result}
<br/>

<%
    User user1 = new User();
    user1.setName("admin");
    user1.setPassword("admin123");


    User user2 = new User();
    user2.setName("马云");
    user2.setPassword("123");

    List<User> userList = new ArrayList();
    userList.add(user1);
    userList.add(user2);
    request.setAttribute("userList", userList);

%>

测试forEach<br/>
<c:forEach items="${userList}" varStatus="status" var="user" step="2">
    ${user.name} <br/>
    ${status.current.password}    <br/>
</c:forEach>

<br/>

测试set<br/>

<c:set scope="request" var="name" value="zhangsan" target="" property="">

</c:set>



</body>
</html>
