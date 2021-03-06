### *Tomcat*

目录结构：

![preview](assets/v2-d8b75829a65958c65d50781155ae80a1_r.jpg)

#### *DefaultServlet*

![image-20201120092122430](assets/image-20201120092122430.png)

*DefaultServlet*是默认的*Servlet*，它匹配所有的路径，优先级最低

当浏览器找不到对应的*Servlet*处理时，就由*DefaultServlet*处理：

静态资源能找到就读取并相应

动/静态资源找不到就响应*404*

##### *JspServlet*

![image-20201120092728812](assets/image-20201120092728812.png)

动态资源*(Jsp)*能找到，会被转换成*Servlet*并相应，不需要另外的*Servlet*

**.jsp*这样的资源交由*JspServlet*处理，它将把找到的*Jsp*转换为*Servlet*

#### 虚拟路径（*tomcat*官方推荐部署方式）

通常我们将项目放在*tomcat*的*webapps*目录下，我们还可以采用以下方式：

进入*tomcat*的*Catalina*的*localhost*目录下配置 *xxx.xml*， *xxx*即访问路径

如果名为*ROOT*则不需要写上名字

```java
<Context docBase="/Users/wentimei/Downloads/jspProject"  />
```

*docBase*: 项目的实际路径，路径应该指向的是*WEB-INF*的上一级

![image-20201113090819884](assets/image-20201113090819884.png)

如果有多个项目则配置多个*xml*即可

### *Servlet*

*Servlet：* 运行在服务端的Java*小程序*，是*sun*公司提供一套规范，用**来处理客户端请求、响应给浏览器的动态资源**

#### *servlet*中路径总结

准守相对路径和绝对路径原则，只不过这里的 **/** 代表了当前项目

#### *servlet*下获取资源问题

https://www.cnblogs.com/deng-cc/p/7152988.html

```java
// 用ClassLoader加载资源时不能是绝对路径，只能是相对路径
// 这样是从classpath（src）下获取资源
InputStream resourceAsStream = JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");

// 可以是相对路径和绝对路径
 InputStream resourceAsStream =  JdbcUtils.class.getResourceAsStream("/druid.properties");
```

#### *Servlet*生命周期

如果没有配置*(或小于0) \<load-on-startup>\</load-on-startup>* *Servlet*会在第一次请求的时候*init*，配置大于等于0时，在*Tomcat*启动时就初始化。

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

- 获取虚拟路径 *(getContextPath)*

- 根据虚拟路径获取绝对路径*（getRealPath）*

> */* 斜杠被服务器解析地址为: *http://ip:port/工程名/* *映射到* *IDEA* *代码的* *web* 目录

- 存取数据*(setAttribute, getAttribute, removeAttribute)*

#### *HttpServletRequest*

*Tomcat*会把每次请求的*HTTP*协议信息解析好封装到*HttpServletRequst*中，传递到*service（doGet、doPost）*中给我们使用。通过*HttpServletRequst*，可以获取到所有的请求信息。

##### 获取多选框的值

```jsp
	<select name="cid">
 		<c:forEach items="${requestScope.categories}" var="category">
			<option value="${category.cid}" 
${category.cid == requestScope.product.category.cid ? "selected = 'selected'" : ""}>${category.cname}
            </option>
		</c:forEach>
	</select>
```

通过*request.getParameter()*获取到的*select*中*option*的值，如果*option*的*value*有值就取*value*的值，如果没有就取*option*标签中间的值

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

**发送请求时创建，请求结束时销毁**

##### *request*域对象

*request*也是一个域对象，所以也可以存取数据*(setAttribute, getAttribute, removeAttribute)*

*request*作为域对象来存储数据时**必须结合<u>请求转发</u>来使用，否则就没有任何意义。**

##### *request*请求转发

**请求转发只能在项目内转发**

##### ！！！转发后静态资源问题

静态资源如果是相对路径，那么是相对于页面而言的。请求转发的路径并不会改变，转发后的页面如果是相对路径，那么就是相对于转发后的路径。导致静态资源找不到

![image-20201126131742295](assets/image-20201126131742295.png)

获得请求转发器：

```java
RequestDispatcher getRequestDispatcher(String path)
```

通过转发器对象转发：

```java
forward(ServletRequest request, ServletResponse response)
```

###### 请求转发的路径

```java
// 这里的路径 不管是绝对路径还是相对路径都是一样的，都表示 http://ip地址:端口号/项目名称
request.getRequestDispatcher("sessionDemo/session.jsp").forward(request, response);
```

###### 转发与重定向的区别

- 重定向的地址栏会发生变化，转发不会
- **重定向两次请求两次响应，转发一次请求一次响应**
- 重定向路径需要加工程名，转发不需要
- 重定向可以跳转到任意页面，转发只能在服务器内部进行转发

*转发和重定向：*

![image-20201112091301135](assets/image-20201112091301135.png)

###### 转发与重定向使用时机

- 看跳转是否在项目内，在项目内都可以，项目外必须重定向
- 是否需要携带数据，如果传递参数用转发，否则重定向
- 重定向可以防止表单重复提交，转发不可以

#### *HttpServletResponse*

每次请求进来，*Tomcat* 服务器都会创建一个 *Response* 对象传递给 *Servlet* 程序去使用。*HttpServletRequest* 表示请求过来的信息，*HttpServletResponse* 表示所有响应的信息

##### 输出流

*getOutPutStream():* 字节流，常用语下载（传递二进制数据）

*getWriter():* 字符流，常用语回传字符串

**两个流同时只能使用一个！否则会报错**

***Tomcat*会自动调用*response*输出流的 *close*方法和*flush*方法, 因此不需要我们手动关闭流**

##### 响应乱码

在*Servle*t中，响应流输出中文会发生乱码，采用如下方式：

第一种方式：

```java
// 设置服务器字符集为 UTF-8 
response.setCharacterEncoding("UTF-8"); 
// 通过响应头，设置浏览器也使用 UTF-8 字符集 
response.setHeader("Content-Type", "text/html; charset=UTF-8");
```

第二种方式：

```java
/*
  它会同时设置服务器和客户端都使用 UTF-8 字符集，还设置了响应头
  此方法一定要在获取流对象之前调用才有效 
*/
response.setContentType("text/html; charset=UTF-8");
```

在*JSP*中只需要设置页面的编码即可

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
```

##### 重定向

第一种方式：

```java
// 设置响应状态码 302 ，表示重定向
response.setStatus(302); 
// 设置响应头，说明 新的地址在哪里 
response.setHeader("Location", "http://localhost:8080");
```

第二种方式：

```java
response.sendRedirect("http://localhost:8080");
```

###### 重定向的路径

```java
// 下面三种方式都可以重定向到指定位置
 response.sendRedirect(getServletContext().getContextPath() +    "/sessionDemo/session.jsp");
 response.sendRedirect("sessionDemo/session.jsp");
 response.sendRedirect("/servletProject/sessionDemo/session.jsp");
```

### *Cookie*

 *Cookie*是将用户的信息保存到**客户端**浏览器的一个技术,当下次访问的时候,浏览器会自动携带*Cookie*的信息过来到服务器端.

*addCookie*即把*Cookie*响应给客户端

*Cookie*存在于客户端，却是由服务器产生发送给客户端的，起初有个*JSESSIONID*是服务器自动生成给客户端的

**相当于本地缓存的作用，可以提高效率，但是不够安全，最多只能存储*4kb*的数据，*key*和*value*都是*String*类型的**

#### ！！！作用范围

*cookie*作用范围默认在当前文件下（初次生成的时候）

![image-20201126154500980](assets/image-20201126154500980.png)

如图，如果我在该*servlet*下生成一个*cookie*，那么他默认就作用于*localhost:port/项目名/servlet*这个路径下面，其他路径下此*cookie*并不能有效使用。

##### 请求路径

```java
cookie.setPath(request.getContextPath());
```

这样就可以设置*cookie*作用与项目下

#### 生存时间

*Cookie*的默认生成时间是一次会话，*cookie.setMaxAge()*可以设置生存时间，单位为秒

不主动设置*MaxAge*，默认*MaxAge<0*，即*cookie*存在于浏览器内存中，Cookie随浏览器关闭而消失即一次会话

设置*MaxAge>0*，响应持久性*Cookie*，会存在电脑硬盘的特定文件夹下

设置特定*Cookie*的*MaxAge=0*，则会删除已经存在客户端的此*Cookie：*

```java
Cookie cookie = new Cookie("","");
cookie.setMaxAge(0);
response.addCookie(cookie);
```

### *Session*

*Session*就是会话，它是用来维护一个客户端和服务器之间关联的一种技术

相比较*Cookie*存在客户端，*Session*则是服务端的东西

#### *Session*机制

客户端第一次访问服务端时，服务端会产生一个*session*对象（用于存储客户信息），并且每个*session*对象都有一个唯一的*sessionId*（用于区分其他*session*）。服务端还会产生一个*cookie*，*name=JSESSIONID，value=sessionId*。服务端会在响应客户端的时候将*cookie*发送给客户端。因此客户端就有了*cookie*和服务端*session*一一对应。

也就是说当客户端访问服务器，看是否有*cookie（JESSIONID）*能和服务端的*session（sessionId）*对应起来，没有则创建

#### 生命周期

在 *Tomcat* 服务器的配置文件 *web.xml*中默认有以下的配置，它就表示配置了当前 *Tomcat* 服务器下所有的 *Session* 超时配置默认时长为：*30* 分钟。 

![image-20201116161918558](assets/image-20201116161918558.png)

如果想配置所有Session的超时时长，在自己项目中的web.xml中配置以上信息即可。

如果想单独配置某个Session的时长可以通过*setMaxInactiveInterval(int interval)*来配置单位为秒。

**注意：** ***session*只作用于一次会话，也就是说，一旦会话结束，之后再访问就获取不到数据了。**

*cookie*默认为一次会话，关闭浏览器之后*cookie*销毁。关闭浏览器后仅仅是丢失*session*的*id*即JSESSIONID，从而导致找不到session，**而session本身还是存在的**。如果再次访问服务器，就会创建新的*cookie*。如果想要一次会话之后仍能识别原来的客户端，只需要设置*cookie（JSESSIONID）*的持久化即可。

> [浏览器关闭后，Session就销毁了吗？](https://blog.csdn.net/QQ1012421396/article/details/70842148)

##### *Session*序列化

当服务器关闭之后，会生成一个叫*SESSIONS.ser*的文件用来保存所有的session信息，该文件生成在*Tomcat*的*work/Catalina/localhost/项目*下。当启动服务器是，该文件被读取并销毁。

#### 使用

```java
// 获取session
Session session = request.getSession();
// 存
session.setAttribute("", obj);
// 取
Object obj = session.getAttribute("");
// 销毁所有的session
session.invalidate();
// 销毁指定session
session.remoteAttribute("");
// 设置指定session的超时时间 单位s
session.setMaxInactiveInterval(60 * 20);
```

### *JSP*

*JSP*全称*Java Server Page*，直译就是“运行在服务器端的页面”。我们可以直接在*JSP*文件里写*HTML*代码，使用上把它**当做** *HTML*文件。我们还可以把*Java*代码内嵌在*JSP*页面中，很方便地把动态数据渲染成静态页面。

当有人请求*JSP*时，**服务器内部会经历一次动态资源*（JSP）*到静态资源*（HTML）*的转化**，**服务器会自动帮我们把*JSP*中的*HTML*片段和数据拼接成静态资源响应给浏览器**。也就是说*JSP*是运行在服务器端，但最终发给客户端的都已经是转换好的*HTML*静态页面（在响应体里）。

> WEB容器接收到以.jsp为扩展名的URL的访问请求时，它将把该请求交给JSP引擎去处理。Tomcat中的JSP引擎就是一个Servlet程序，它负责解释和执行JSP页面。
>
> 每个JSP 页面在第一次被访问时，JSP引擎将它翻译成一个Servlet源程序，接着再把这个Servlet源程序编译成Servlet的class类文件，然后再由WEB容器像调用普通Servlet程序一样的方式来装载和解释执行这个由JSP页面翻译成的Servlet程序。 
>
> 【存放位置】
> Tomcat把为JSP页面创建的Servlet源文件和class类文件放置在“<TOMCAT_HOME>\work\Catalina\<主机名>\<应用程序名>\”目录中，Tomcat将JSP页面翻译成的Servlet的包名为org.apache.jsp.<JSP页面在WEB应用程序内的目录名> 。
>
> 来源：https://zhuanlan.zhihu.com/p/42343690

#### 九大内置对象

*Jsp*内置对象可以直接使用

1. *Request:*
2. *Response:*
3. *Session:*
4. *Application:*
5. *PageContext:*
6. *Config:*
7. *Out:*
8. *Page:*
9. *Exception:*

#### 四大作用域

所有的作用域都有三个一样的方法： ***(setAttribute, getAttribute, removeAttribute)***

- *PageContext：*作用于当前页面
- *Request：*作用于一次请求
- *Session：*作用于一次会话
- *Application：*整个项目有效（重启或其他项目无效，可以使用*JNDI*解决）

### *EL*表达式

*EL（Express Lanuage）*表达式可以嵌入在*JSP*页面内部，减少*JSP*脚本的编写，***EL*出现的目的是要替代*JSP*页面中脚本的编写**

原来从域中取出数据: ***<%=request.getAttribute(name)%>***

使用*EL*进行取代:***${name}***

***EL*是从四个域中去找和*name*匹配的值，优先顺序从作用域最小的开始，依次往上**

可以指定域去拿取数据：***${requestScop.name}***

**如果值为*null*，*EL*将展示位空字符串**

#### *EL*的三个作用

1. 可以从域中取值
2. 可以执行表达式的运算
3. 有*11*个内置对象**（*cookie*等）**

####  *.*点运算 和  *[ ]*  中括号运算符 

使用*[ ]*可以输出一些特出字符，而 *.* 不能

```jsp
<body> 
  <% 
    Map<String,Object> map = new HashMap<String, Object>(); 
    map.put("a.a.a", "aaaValue"); 
    map.put("b+b+b", "bbbValue"); 
    map.put("c-c-c", "cccValue"); 
    request.setAttribute("map", map); 
  %> 
    
    ${ map['a.a.a'] } <br> 
    ${ map["b+b+b"] } <br> 
    ${ map['c-c-c'] } <br> 
    
</body>
```

### *JSTL*标签库

*EL* 表达式主要是为了替换 *JSP* 中的表达式脚本，而标签库则是为了替换代码脚本，这样使得整个*JSP*页面更佳简洁

http://archive.apache.org/dist/jakarta/taglibs/standard/binaries/

进入网址下载*JSTL*的安装包并导入

```jsp
// uri 需要导入的路径 prefix 用来区分不同的标签库
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
```

#### 常用标签

1. ***\<c:if\>***

属性：

```jsp
// test 表示判断的条件
// var 表示判断的结果
// scope 表示var的作用域
<c:if  test="${num == 2}" var="result" scope="request">
    ${num}
    ${result}
</c:if>
```

2. ***\<c:forEach\>***

属性：

```jsp
// items 要遍历的集合对象
// var 对象的名字
// varStatus 记录了对象的状态信息，该对象类型为 javax.servlet.jsp.jstl.core.LoopTagStatus
// begin 遍历开始的位置
// end 遍历结束的位置
// step 每次移动的步数
<c:forEach items="${userList}" varStatus="status" var="user">
    ${user.name} <br/>
    ${status.current.password}    <br/>
</c:forEach>
```

3. ***\<c:set\>***

属性：

```jsp
// scope 给哪个域设置值
// var key
// value value
// target 给目标对象传值，必须是Map或者有Setter的对象
// property 给对象的哪个属性传值
<c:set scope="request" var="name" value="zhangsan" target="" property="">

</c:set>

可以设置为 var 和 value 用来接收一个值
```

### *Filter*过滤器 责任链模式

*Filter*过滤器是*JavaEE*的规范，也就是接口。它的作用是：拦截请求，过滤响应

可以使用*Filter*实现统一事务管理

```java
// 在方法出将可以的异常throw就会被filter所catch，这时进行回滚
// 在jdbcutils中获取连接时将自动提交关闭
try {
    chain.doFilter(request, response);
    JdbcUtils.commit();
} catch (Exception e) {
    e.printStackTrace();
    JdbcUtils.rollback();
}
```

#### 使用

实现*Filter*接口，重写方法

*doFilter*中，使用*chain.doFilter*放行

```java
public class FilterImpl implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter 初始化了");
        String filterName = filterConfig.getFilterName();
        System.out.println("filter的名字是： " + filterName);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 前置方法 请求处理
        // 放行
        chain.doFilter(request, response);
        // 后置方法 响应处理
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁");
    }
}
```

#### 配置

```xml
<filter>
    <filter-name>encodingFilter</filter-name>
    <filter-class>com.chhoyun.filter.EncodingFilter</filter-class>
</filter>

<filter-mapping>
    <filter-name>encodingFilter</filter-name>
    // 匹配过滤路径
    <url-pattern>/encodingCode/*</url-pattern>
</filter-mapping>
```

#### 生命周期

*Filter*在*Tomcat*启动时初始化，在关闭时销毁，每次过滤都会调用*doFilter*方法

#### *Filter*过滤链

当配置多个过滤器的时候，会根据配置的*\<filter-mapping>*的先后顺序执行。执行顺序为先执行前面的请求，再执行后面的请求，请求执行完之后先执行后面的响应，再执行前面的相应。

过滤器执行顺序

![image-20201118150939561](assets/image-20201118150939561.png)

如何做到的？

*Tomcat*的*ApplicationFilterChain*类中源码如下：

![image-20201118151635975](assets/image-20201118151635975.png)

![image-20201118151721751](assets/image-20201118151721751.png)

![image-20201118151803737](assets/image-20201118151803737.png)

我们将响应后的代码放置*doFilter*方法后，当最后一个*filter*走过后会返回上一次调用的地方，这样就可以往前响应。

执行流程如图所示

![image-20201118153345566](assets/image-20201118153345566.png)

### *Listerner*监听器 观察者模式

```text
	6个常规监听器
    |---ServletContext
            |---ServletContextListener（生命周期监听）
            |---ServletContextAttributeListener（属性监听）
    |---HttpSession
            |---HttpSessionListener（生命周期监听）
            |---HttpSessionAttributeListener（属性监听）
    |---ServletRequest
            |---ServletRequestListener（生命周期监听）
            |---ServletRequestAttributeListener（属性监听）

	2个感知监听 感知监听器不需要配置web.mxl
    |---HttpSessionBindingListener 对象绑定到session或从session中解绑
    |---HttpSessionActivationListener 钝化与活化
	将一个对象存入到session域中，如果session被钝化（序列化）到磁盘上或者从磁盘上活化（反序列化）时都会被该监听器监听
```

#### 使用

实现要监听的接口，重写方法

#### 配置

```xml
<listener>
    <listener-class>com.chhoyun.listener.MyListener</listener-class>
</listener>
```

#### 生命周期

*ServletContextListtener*在*Tomcat*启动时创建，关闭时销毁

*ServletRequestListener*在发起请求时创建，请求结束时销毁

*HttpSessionListener*在*request.getSession*时创建，*session*销毁时销毁，如果在*session*生命周期内多次*getSession*也只有第一次*JSESSIONID*找不到对于的*session*时才会创建

属性监听器在各域进行*set/removeAttribute*时触发监听 （触发*add*、*replace*、*remove*，*get*不能触发）

### Ajax

*Ajax*是一种动态的网页开发技术，可以使网页实现异步刷新，就不是重新加载整个网页的情况下，对网页的某部分进行更新。传统的网页（不使用 *Ajax*）如果需要更新内容，必须重载整个网页页面

#### 同步&异步

同步：客户端发送请求到服务器端，当服务器返回响应之前，客户端都处于等待卡死状态

异步：客户端发送请求到服务器端，无论服务器是否返回响应，客户端都可以随意做其他事情，不会被卡死

#### 原生*Ajax*

*js*原生的*Ajax*其实就是围绕浏览器内内置的*Ajax*引擎对象进行学习的，要使用*js*原生的*Ajax*完成异步操作，有如下几个步骤：

1. 创建Ajax引擎对象
2. 为Ajax引擎对象绑定监听（监听服务器将数据响应给引擎）
3. 绑定提交地址
4. 发送请求
5. 接受响应数据

![image-20201120104152076](assets/image-20201120104152076.png)

##### 使用案列

```javascript
// post
function js_ajax_post() {
    // 1.创建一个js的ajax对象。
    var request = new XMLHttpRequest();

    request.onreadystatechange = function () {
        // 我们的代码应该在请求发出，并且成功的接收到响应 的情况下，才去做页面相应数据的刷新。
        // 而且应该 保证响应的状态码为200的情况下才执行数据的刷新。
        if (request.readyState == 4 && request.status == 200) {
            alert("ssss");
        }
    }


    request.open("post", "${root}/demoServlet1", true);
    // post提交必须手动设置这个请求 头，就是以url格式提交参数。必须放在open方法的下面。
    request.setRequestHeader("content-type", "application/x-www-form-urlencoded");
    // 4.发送请求
    request.send("username=zhangsan");
}


// get
function js_ajax_async() {
    // 1.创建一个js的ajax对象。
    var request = new XMLHttpRequest();
    // 2.给这个对象绑定事件
    request.onreadystatechange = function () {
        // 我们的代码应该在请求发出，并且成功的接收到响应 的情况下，才去做页面相应数据的刷新。
        // 而且应该 保证响应的状态码为200的情况下才执行数据的刷新。
        if (request.readyState == 4 && request.status == 200) {
            alert(request.responseText);
        }
    }
    request.open("get", "${root}/demoServlet1?username=zhangsan", true);

    // 4.发送请求
    request.send();
}
```

#### *JQuery*的*Ajax*

*JQuery*对原生*Ajax*进行了封装，封装后使用更简洁、功能更强大

##### *get*

![img](assets/wpsmtu2H4-5840680.jpg)

###### 使用案例

```javascript
 /*
  第一个参数：url:必须的，请求的地址
  第二个参数：data:可选的，请求的参数，一般是一个json
  第三个参数：callback:可选的，回调函数。   回调函数的参数就是响应体的内容
 */
function jqueryAjax2() {
    $.get("demoServlet1",{"username":"lisi"}, function(data) {
        alert(data)
    })
}
```

##### *post*

![img](assets/wpsfdZsGL.jpg)

###### 使用案列

```javascript
 /*
  第一个参数：url:必须的，请求的地址
  第二个参数：data:可选的，请求的参数，一般是一个json
  第三个参数：callback:可选的，回调函数。   回调函数的参数就是响应体的内容
 */
function jquertAjax2() {
	$.post("demoServlet1",{"username":"zhangsan"},function(data){
 		alert(data)
 	})
}
```

##### *ajax*

*get*和*post*默认都是异步请求，要使用同步请求就要使用*ajax*方法，设置*async*为*false*即为同步，默认为异步

```javascript
$.ajax({ 
    option1:value1,
	option2:value2... 
})
```

常用参数有：

1. *async：*是否异步
2. *data：*发送到服务端的参数，默认为*json*格式
3. *dataType*：服务端返回的数据类型，常用*text*和*json*，默认为*text*
4. *success：*响应成功后执行的函数
5. *type：*请求提交方式，*get/post*
6. *url：*请求地址

###### 使用案列

```javascript
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
```