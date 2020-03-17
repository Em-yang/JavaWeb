# EL表达式

Expression Language 表达式语言，替代JSP页面中数据访问时的复杂编码，可以非常便捷的取出域对象(pageContext	request	session	application)中保存的数据，前提是一定要setAttribute，EL就相当于简化getAttribute

语法：${变量名} 	变量名就是setAttribute对应的key值

EL 表达式

1、对于4种域对象的查找顺序

pageContext > requuest > session > application

按照上述的顺序进行查找，找到立即返回，在application中找不到，则返回null

2、指定作用域进行查找

pageContext：${pageScope.name}

request：${requestScope.name}

session：${sessionScope.name}

application：${applicationScope.name}



数据级联：

```jsp
    <%
        User user = new User(1,"张三",90.5,new Address(1,"汝州"));
        pageContext.setAttribute("user",user);
    %>
    <table>
        <tr>
            <th>编号</th>
            <th>姓名</th>
            <th>成绩</th>
            <th>地址</th>
        </tr>
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.score}</td>
            <td>${user.address.value}</td>
        </tr>
    </table>    <%
        User user = new User(1,"张三",90.5,new Address(1,"汝州"));
        pageContext.setAttribute("user",user);
    %>
    <table>
        <tr>
            <th>编号</th>
            <th>姓名</th>
            <th>成绩</th>
            <th>地址</th>
        </tr>
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.score}</td>
            <td>${user.address.value}</td>
        </tr>
    </table>
```

${user.id}   

1、id -->> Id

2、Id --> getId

3、去user类里去找getId()方法 	从本质来讲user.id是和id的get方法结合起来的



EL执行表达式

&&	||	!	<	>	<=	>=	==

```jsp
<%
//        pageContext.setAttribute("num1",10);
////        pageContext.setAttribute("num2",9);
        pageContext.setAttribute("num1",false);
        pageContext.setAttribute("num2",true);
    %>
    <%--${num1>num2}--%>
    ${num1&&num2}
```



```java
&&	and
||	or
!	not
==	eq
!=	ne
<	lt
>	gt
<=	le
>=	ge
empty	是否为空 （变量为null，长度为0的String，siae为0的集合的时候为空） 若为空返回true
```

