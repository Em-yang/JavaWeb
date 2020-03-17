# JSTL

JSP Standard Tag Library	JSP标准标签库，JSP为开发者提供的一系列的标签，使用这些标签可以完成一些逻辑处理，比如循环遍历，让代码更加简洁，不再出现JSP脚本穿插的情况。

实际开发中EL和JSTL结合起来使用，JSTL侧重逻辑处理，EL负责展示数据

### JSTL使用

1、导入jar包（ jstl.jar	standard.jar） 存放位置web/WEB-INF

2、在JSP页面开始的地方导入JSTL标签库

```jsp
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
```

3、在需要的地方使用

```jsp
<c:forEach items="${list}" var="user">
  <tr>
    <th>${user.id}</th>
    <th>${user.name}</th>
    <th>${user.score}</th>
    <th>${user.address.value}</th>
  </tr>
</c:forEach>
```



### JSTL优点

1、提供了统一的标签

2、可以用于编写各种动态功能

核心标签库常用标签：

set	out	remove	catch

set：向域对象中添加数据

```jsp
<c:set var="name" value="tom" scope="request">
</c:set>
${requestScope.name}

<%
        User user = new User(1,"张三",66.6,new Address(1,"河南"));
        request.setAttribute("user",user);
    %>
    ${user.name}
    <c:set target="${user}" property="name" value="李四"></c:set>
    ${user.name}
```

out：输出域对象中的数据

```jsp
<c:set var="name" value="tom"></c:set>
<c:out value="${name}" default="未定义"></c:out>
```



remove：删除域对象中的数据

```jsp
<c:remove var="name" scope="page"></c:remove>
<c:out value="${name}" default="未定义"></c:out>
```



catch：捕获异常

```jsp
<c:catch var="error">
    <%
        int a = 10/0;
    %>
</c:catch>
```



条件标签：if	choose

```jsp
<c:set var="num1" value="1"></c:set>
<c:set var="num2" value="2"></c:set>
<c:if test="${num1>num2}">ok</c:if>
<c:if test="${num1<num2}">fail</c:if>
<hr>
<c:choose>
    <c:when test="${num1>num2}">ok</c:when>
    <c:otherwise>fail</c:otherwise>
</c:choose>
```



迭代标签：forEach

```jsp
<c:forEach items="${list}" var="str" begin="2" end="3" step="2" varStatus="sta">
    ${sta.index}-${str}<br>
</c:forEach>
```



格式化标签库常用的标签：

```jsp
<%
    request.setAttribute("data",new Date());
%>
<fmt:formatDate value="${data}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
<fmt:formatNumber value="32145.232343" maxIntegerDigits="2" maxFractionDigits="3"></fmt:formatNumber>
```



函数标签库常用标签：

```jsp
<%
    request.setAttribute("info","Java,C");
%>
${fn:contains(info, "python")}<br/>
${fn:startsWith(info, "Java")}<br/>
${fn:endsWith(info, "C")}<br/>
${fn:indexOf(info, "va")}<br/>
${fn:replace(info, "C","python")}<br/>
${fn:substring(info, 2,3 )}<br/>
${fn:split(info, ",")[0]}-${fn:split(info, ",")[1]}<br/>
```