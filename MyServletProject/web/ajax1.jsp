<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wentimei
  Date: 2020/11/20
  Time: 上午10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
    <title>Title</title>
</head>
<body>
<input type="button" value="js_ajax" onclick="js_ajax()">
<input type="button" value="js_ajax" onclick="js_ajax_post()">

<input type="button" value="异步" onclick="js_ajax_async()">
<input type="button" value="同步" onclick="js_ajax_notasync()">
<input type="button" value="按钮" onclick="alert('ss');">


</body>

<script type="text/javascript">
    function js_ajax() {
        //1.创建一个js的ajax对象。
        var request = new XMLHttpRequest();
        //2.给这个对象绑定事件
        /*
            readystate XMLHttpRequest对象的状态属性的变化事件
                    1：open()方法被调用，但是请求还未发出。
                    2：send()方法被调用，请求已经发出，但是还没有接受到响应。
                    3：所有响应头部都已经接收到。响应体开始接收但未完成。
                    4：http响应已经全部接受完毕。

            status: 代表了响应的状态码。
            responseText :就是响应体的内容

        */
        request.onreadystatechange = function () {
            //我们的代码应该在请求发出，并且成功的接收到响应 的情况下，才去做页面相应数据的刷新。
            // 而且应该 保证响应的状态码为200的情况下才执行数据的刷新。
            if (request.readyState == 4 && request.status == 200) {
                alert(request.responseText);
            }
        }

        //3.设置请求的参数
        /*
            method:请求提交的方式
            url:请求的地址
            async:是否是异步。  true，代表异步，false，代表同步。
        */
        request.open("get", "${root}/demoServlet1?username=zhangsan", true);

        //4.发送请求
        request.send();
    }

    function js_ajax_post() {
        //1.创建一个js的ajax对象。
        var request = new XMLHttpRequest();

        request.onreadystatechange = function () {
            //我们的代码应该在请求发出，并且成功的接收到响应 的情况下，才去做页面相应数据的刷新。
            // 而且应该 保证响应的状态码为200的情况下才执行数据的刷新。
            if (request.readyState == 4 && request.status == 200) {
                alert("ssss");
            }
        }


        request.open("post", "${root}/demoServlet1", true);
        //post提交必须手动设置这个请求 头，就是以url格式提交参数。必须放在open方法的下面。
        request.setRequestHeader("content-type", "application/x-www-form-urlencoded");
        //4.发送请求
        request.send("username=zhangsan");
    }


    function js_ajax_async() {
        //1.创建一个js的ajax对象。
        var request = new XMLHttpRequest();
        //2.给这个对象绑定事件
        request.onreadystatechange = function () {
            //我们的代码应该在请求发出，并且成功的接收到响应 的情况下，才去做页面相应数据的刷新。
            // 而且应该 保证响应的状态码为200的情况下才执行数据的刷新。
            if (request.readyState == 4 && request.status == 200) {
                alert(request.responseText);
            }
        }
        request.open("get", "${root}/demoServlet1?username=zhangsan", true);

        //4.发送请求
        request.send();
    }

    function js_ajax_notasync() {
        //1.创建一个js的ajax对象。
        var request = new XMLHttpRequest();
        //2.给这个对象绑定事件
        request.onreadystatechange = function () {
            //我们的代码应该在请求发出，并且成功的接收到响应 的情况下，才去做页面相应数据的刷新。
            // 而且应该 保证响应的状态码为200的情况下才执行数据的刷新。
            if (request.readyState == 4 && request.status == 200) {
                alert(request.responseText);
            }
        }
        request.open("get", "${root}/demoServlet1?username=zhangsan", false);

        //4.发送请求
        request.send();
    }

</script>
</html>
