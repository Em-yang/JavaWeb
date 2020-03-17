# Servlet

##### 什么是Servlet？

Servlet是Java Web开发的基石，与平台无关的服务器组件，它运行在Servlet容器/Web应用服务器/Tomcat，负责与客户端进行通信。

##### Servlet的功能

1、创建并返回基于客户请求的动态HTML页面

2、与数据库进行通信

##### 如何使用Servlet

Servlet本身是一组接口(javax.servlet，java.lang、java.util、javax.sql )，自定义一个类，并且实现Servlet接口，这个类就具备了接受客户端请求以及做出响应的功能。

```java
package com.yang.servlet;

import javax.servlet.*;
import java.io.IOException;

public class MyServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("我是Servlet，我已经接受到消息");
        servletResponse.setContentType("text/html;charset=UTF-8");
        servletResponse.getWriter().write("我已经接受到消息");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}

```



浏览器不能直接访问Servlet文件，只能通过映射的方式来间接访问Servlet，映射需要开发者手动配置，有两种配置方式

​	1 基于XML文件的配置方式

```java
<servlet>
        <servlet-name>MyServlet</servlet-name>
        <servlet-class>com.yang.servlet.MyServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>MyServlet</servlet-name>
        <url-pattern>/myservlet</url-pattern>
    </servlet-mapping>
```

​	2 基于注解的方式

```java
package com.yang.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
//注解
@WebServlet("/myservlet")

public class MyServlet implements Servlet {
   
}

```

上述两种配置方式结果完全一致，将/myservlet与MyServlet进行映射，即在浏览器地址中直接访问/myservlet就可以映射到MyServlet。

##### Servlet的生命周期

1、当浏览器访问Servlet的时候，Tomcat会查询当前Servlet的实例化对象是否存在，如果不存在，则通过反射机制动态创建对象，如果存在就直接执行第三步。

2、调用init方法完成初始化操作

3、调用service方法完成业务逻辑操作

4、关闭 Tomcat 时，会调用destroy方法，释放当前对象所占用的资源



Servlet的生命周期方法：无参构造方法，init，service，destroy

1、 无参构造方法只调用一次，创建对象

2、init只调用一次，初始化对象

3、service调用N次，执行业务方法

4、destroy 只调用一次，卸载对象。

##### ServletConfig

该接口是用来描述Servlet的基本信息的

getServletName()   返回Servlet的名称，全类名（带着包名的类名）

getInitParameter()  获取init参数的值（web.xml）

getInitParameterNames()  返回所有的initParamter的name值，一般用作遍历初始化参数

getServletContext()   返回ServletContext对象，它是Servlet的上下文，整个Servlet的管理者

##### ServletConfig 和  ServletContext 区别

​	ServletConfig 作用与某个Servlet实例，每个Servlet都有对应的ServletConfig

​	ServletContext 作用于整个Web应用，一个Web应用对应一个ServletContext，多个Servlet实例对应一个ServletContext

一个局部对象（ServletConfig），一个全局对象（ServletContext）。



##### Servlet的层次结构

Servlet ---> GenericServlet ---> HttpServlet

HTTP 请求有很多类型，常用的有四种：

​	GET	读取

​	POST	保存

​	PUT	修改

​	DELETE	删除

GenericServlet实现了Servlet接口，同时它的子类屏蔽了不常用的方法，子类只需要重写service方法即可。HttpServlet继承GenericServle。根据请求的类型进行分发处理。GET进入doGet方法，POST进入doPost方法。

开发者自定义的Servlet类只需要继承HttpServlet即可，重写doGet和doPost

```java
package com.yang.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
		resp.getWriter().write("GET");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
		resp.getWriter().write("POST");
    }
}
```