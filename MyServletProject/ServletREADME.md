#### 虚拟路径（*tomcat*官方推荐部署方式）

通常我们将项目放在*tomcat*的*webapps*目录下，我们还可以采用以下方式：

进入*tomcat*的*Catalina*的*localhost*目录下配置 *xxx.xml*， *xxx*即访问路径

如果名为*ROOT*则不需要写上名字

```java
<Context docBase="/Users/wentimei/Downloads/jspProject"  />
```

*docBase*: 项目的实际路径

如果有多个项目则配置多个*xml*即可

### *Servlet*

#### *servlet*下获取资源问题

https://www.cnblogs.com/deng-cc/p/7152988.html

*Servlet：* 运行在服务端的Java*小程序*，是*sun*公司提供一套规范，用**来处理客户端请求、响应给浏览器的动态资源**

#### *Servlet*声明周期

1. 构造*Servlet*，然后使用*init*方法初始化
2. 每次都会调用service方法处理客户端的请求
3. *destroy*在*web*工程停止时调用

#### *ServletConfig*

*Servlet*程序的配置信息类

![image-20201109150009458](assets/image-20201109150009458.png)

- 获取*Servlet*的别名即*\<servlet-name\>*
- 获取初始化参数
- 获取*ServletContext*对象

*Servlet*程序和*ServletConfig*对象都是有*Tomcat*负责创建，我们负责使用

*Servlet*默认是程序第一次访问时创建，每个*Servlet*的创建都会有一个对应的*ServletConfig*对象创建

也就是*ServletConfig*是独立存在的，如果自己的*Servlet*要重写*init*方法应该要调用父类的初始化方法，因为父类的*init*方法给*ServletConfig*进行了赋值

![image-20201109152406001](assets/image-20201109152406001.png)

#### ServletContext域对象

*ServletContext*是一个接口表示上下文对象

***ServletContext*在*web*工程启动时创建，停止时销毁**

**每个web工程只有一个*ServletContext***

*ServletContext*对象是一个域对象（像*map*一项存取数据）

##### 作用

- 获取*web.xml*的上下文参数*(getInitParameter)*

- 获取当前的工程路径 *(getContextPath)*

- 获取工程部署后在服务器硬盘上的绝对路径*（getRealPath）*

> */* 斜杠被服务器解析地址为: *http://ip:port/工程名/* *映射到* *IDEA* *代码的* *web* 目录

- 存取数据*(setAttribute, getAttribute, removeAttribute)*

#### *HttpServletRequest*

*Tomcat*会把每次请求的*HTTP*协议信息解析好封装到*HttpServletRequst*中，传递到*service（doGet、doPost）*中给我们使用。通过*HttpServletRequst*，可以获取到所有的请求信息。

##### 获取中文参数乱码

原因：页面提交的参数是以*utf-8*进行编码的，而服务端获取参数时使用的*iso8859-1*,编解码不一致导致了乱码

###### *doGet*

需要进行字符串的重构

*username = **new** String(username.getBytes("iso-8859-1"), "utf-8");*

*Tomcat 8.0*以上版本中注意*server.xml*的*Connector*编码 如果不是*utf-8*也要进行重构，或者修改编码。（如果是*utf-8*则*Get*不会乱码）

![image-20201110142040212](assets/image-20201110142040212.png)

###### *doPost*

*request.setCharacterEncoding(“utf-8”);*

##### *request*生命周期

发送请求时创建，请求结束时销毁

##### *request*域对象

*request*也是一个域对象，所以也可以存取数据*(setAttribute, getAttribute, removeAttribute)*

*request*作为域对象来存储数据时**必须结合<u>请求转发</u>来使用，否则就没有任何意义。**

##### *request*请求转发

获得请求转发器：

```java
RequestDispatcher getRequestDispatcher(String path)
```

通过转发器对象转发：

```java
forward(ServletRequest request, ServletResponse response)
```

###### 转发与重定向的区别

- 重定向的地址栏会发生变化，转发不会
- 重定向两次请求两次响应，转发一次请求一次响应
- 重定向路径需要加工程名，转发不需要
- 重定向可以跳转到任意页面，转发只能在服务器内部进行转发

###### 转发与重定向使用时机

- 看跳转是否在项目内，在项目内都可以，项目外必须重定向
- 是否需要携带数据，如果传递参数用转发，否则重定向
- 重定向可以防止表单重复提交，转发不可以

##### *Web*资源路径的编写

1. 客户端：

   客户端 */* 代表  *http://ip地址:端口号* ， 不加上 */*表示当前操作文件的父路径

2. 服务端

   服务端 */*代表  *http://ip地址:端口号/项目名称*

**浏览器解析的路径是前端路径，*Tomcat*服务器解析的路径是后端路径**

#### *HttpServletResponse*

每次请求进来，*Tomcat* 服务器都会创建一个 *Response* 对象传递给 *Servlet* 程序去使用。*HttpServletRequest* 表示请求过来的信息，*HttpServletResponse* 表示所有响应的信息

##### 输出流

*getOutPutStream():* 字节流，常用语下载（传递二进制数据）

*getWriter():* 字符流，常用语回传字符串

**两个流同时只能使用一个！否则会报错**

***Tomcat*会自动调用*response*输出流的 *close*方法和*flush*方法, 因此不需要我们手动关闭流**

##### 响应乱码

第一种方式：

```java
// 设置服务器字符集为 UTF-8 
resp.setCharacterEncoding("UTF-8"); 
// 通过响应头，设置浏览器也使用 UTF-8 字符集 
resp.setHeader("Content-Type", "text/html; charset=UTF-8");
```

第二种方式：

```java
/*
  它会同时设置服务器和客户端都使用 UTF-8 字符集，还设置了响应头
  此方法一定要在获取流对象之前调用才有效 
*/
resp.setContentType("text/html; charset=UTF-8");
```

##### 重定向

第一种方式：

```java
// 设置响应状态码 302 ，表示重定向
resp.setStatus(302); 
// 设置响应头，说明 新的地址在哪里 
resp.setHeader("Location", "http://localhost:8080");
```

第二种方式：

```java
resp.sendRedirect("http://localhost:8080");
```