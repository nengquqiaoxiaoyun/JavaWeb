<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wentimei
  Date: 2020/11/20
  Time: 上午11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set value="${pageContext.request.contextPath}" var="root"></c:set>
<html>
<head>
    <title>Title</title>
</head>
<body>

<input type="button" value="jqueryAjax1" onclick="jqueryAjax1()">
<input type="button" value="jqueryAjax2" onclick="jqueryAjax2()">
<input type="button" value="jqueryAjax3" onclick="jqueryAjax3()">

</body>

<script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>

<script type="text/javascript">

    function jqueryAjax1() {
        $.get("${root}/demoServlet1?username=zhangsan", function (data) {
            alert(data)
        })

    }

    function jqueryAjax2() {
        $.get("${root}/demoServlet1", {"username": "lisi"}, function (data) {
            alert(data)
        })
    }

    function jqueryAjax3() {

        $.ajax({
            url: "/demoServlet1",
            data: {"username": "王五"},
            type: "post",
            success: function (resp) {
                alert("success")
                alert(resp)
            },
            dataType: "text"
        })
    }

</script>
</html>
