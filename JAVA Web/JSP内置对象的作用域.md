# JSP内置对象的作用域

就是指对应对象的使用范围

page、request、session、application

四个都有setAttribute、getAttribute方法 都可以存、取数据

page作用域：对应的内置对象pageContext

request作用域：对应的内置对象是request

session作用域：对应的内置对象是session

applicati作用域：对应的内置对象是application

page < request < session < applicatiion

page 只在当前页面有效

request 在一次请求内有效

session 在一次会话内有效

application 对应整个Web应用



### 网站访问量统计（案例   E:\Practise\myjsp004）

```jsp
<%
    Integer count = (Integer) application.getAttribute("count");
    if (count == null){
        count=1;
        application.setAttribute("count",count);
    }else {
        count++;
        application.setAttribute("count",count);
    }
%>
您是当前的第<%=count%>位访客
```