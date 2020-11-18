<%@ page import="com.chhoyun.junit.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: wentimei
  Date: 2020/11/17
  Time: 下午3:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


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

    Map<String, String> hashMap = new HashMap<>();
    hashMap.put("uname", "213");
    request.setAttribute("hashMap", hashMap);

%>

<h2>
    list:
    ${userList[1].name} <br/>
    ${userList[0].name} <br/>
    ${userList[0].password} <br/>
</h2>

map: <br/>
${hashMap.uname}

</body>
</html>
