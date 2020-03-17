# JSP

JSP 本质上就是Servlet，JSP主要负责与用户进行交互，将最终的界面呈现给用户，HTML+JS+CSS+JAVA的混合文件

当一个服务器接受到一个后缀是jsp的请求时，将改请求交给jsp引擎去处理，每一个jsp页面第一次次被访问的时候，jsp引擎会将它翻译成一个Servlet文件，在由Web容器 调用Servlet完成响应。

单纯从开发的角度看，JSP就是在HTML中嵌入Java程序。

具体的嵌入方式有三种：

1、JSP脚本，执行java逻辑代码

```jsp
<% java代码 %>
```

2、JSP 声明 ：定义java方法

```jsp
<%! java方法 %>
```

3、JSP表达式：把java对象直接输出到HTML页面中

```jsp
<%=java变量 %>
```

```jsp
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.yang.servlet.test.User" %>
<%--
  Created by IntelliJ IDEA.
  User: 杨
  Date: 2020/3/2
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <%
     List<User> list = new ArrayList<>();
      list.add(new User("张三",22));
      list.add(new User("张四",23));
      list.add(new User("张五",24));
    %>
  <table>
    <tr>
      <td>姓名</td>
      <td>年龄</td>
    </tr>
    <%
      for (int i=0;i<list.size();i++){
    %>
    <tr>
      <td><%=list.get(i).getName() %></td>
      <td><%=list.get(i).getAge() %></td>
    </tr>
    <%
      }
    %>
  </table>
  </body>
</html>


```

![image-20200303154640612](E:\笔记\img\image-20200303154640612.png)



### JSP内置对象9个

1、request : 表示一次请求，来自HttpServletRequest类

2、response ：表示一次响应，来自HttpServletResponse类

3、pageContext：页面上下文，获取页面信息，来自来自PageContext类。

4、session ：表示一次会话，保存用户信息，来自HttpSession类

5、application：表示当前Web应用，全局对象，保存所有用户共享信息，来自ServletContext类。

6、confiig：当前JSP对应的Servlet的ServletConfig对象，获取当前Servlet的信息

7、out：向浏览器输出数据，来自JspWriter类

8、page：当前jsp对应的Servlet对象，来自Servlet类

9、excerption：表示JSP页面发生的异常，来自Exception类

常用的是request，response，session，application，pageContext



request常用方法：

1、String getParrameter(String key) 	获取客户端传来的参数 （客户端向服务器端传数据时使用）

2、void setAttribute(String key,Object value)	通过键值对的形式保存数据

3、Object getAttribute(String key)	通过key取出value  （服务器端内部传递数据时使用）

4、RequestDispatcher getRequestDispather(String path)   返回一个RequestDispatche对象，该对象的forward方法用于请求转发。

5、String[] getParameterValues(Strring key)	获取客户端传来的多个同名参数

6、void setCharacterEncoding(String charset)	指定每个请求的编码



### HTTP请求状态码:

200:	正常

404： 资源找不到

400： 请求类型不匹配

500：java 程序抛出异常



response常用方法：

1、sendRedirect(String path)  重定向，页面之间的跳转

**转发getRequestDispather和重定向sendRedirect的区别**

**转发是将同一个请求传给下一个页面，重定向是创建一个新的请求传给下一个页面。**

**转发：同一个请求在服务器之间传递，地址栏不变，也叫服务器跳转。**

**重定向：由客户端发送一次新的请求来访问跳转后的目标资源，地址栏改变，也叫客户端跳转。**

**如果两个页面之间需要通过request来传值i，则必须使用转发，不能使用重定向**



### Session

用户会话

服务器无法识别每一次HTTP请求的出处（不知道来自哪个终端），它只会接受到一个请求信号，所以就存在一个问题：将用户的响应发送给其他人，必须有一种技术来让服务器知道请求来自哪，这就是会话技术。

会话：就是客户端与服务器之间发生的一系列连续的请求和响应的过程，打开浏览器进行操作到关闭浏览器的过程

会话状态：指服务器和浏览器在会话过程中产生的状态信息，借助于会话状态，服务器能把属于同一次会话的一系列请求和响应联系起来。

实现会话有两种方法：

​	1、session

​	2、cookie

属于同一次会话的请求都有一个相同的表示符，sessionID

session常用的方法：

1、String gteId()  获取sessionID

2、void  setMaxNactiveInterval(int interval)  	设置session的失效时间，单位为秒

3、int  getMaxNactiveInterval()	获取当前session的失效时间

4、void  invalidate()	设置session立即失效

5、void setArrtibute(String key,Object value)	通过键值对的形式来存储数据

6、Object  getArrtibute(String key)   通过键获取对应的数据

7、void  removeAttribute(String key) 	通过键删除对应的数据

