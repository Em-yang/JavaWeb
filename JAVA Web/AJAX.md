# AJAX

Asynchrronous JavaScript And XML:异步的额javaScript和XML

AJAX指的是一种交换方式，异步加载，客户端和服务器的数据交互更新在局部页面的技术，不需要刷新整个页面（局部刷新）

优点：

1、局部刷新，效率更高

2、用户体验更好

基于jQuery的AJAX

```jsp
<%--
  Created by IntelliJ IDEA.
  User: 杨
  Date: 2020/3/10
  Time: 9:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" >
        $(function () {
            var btn = $("#btn");
            btn.click(function () {
                $.ajax({
                    url:'/test',
                    type:'post',
                    data:'id=1',
                    dataType:'text',
                    success:function (data) {
                        var text = $("#text");
                        text.before("<span>"+data+"</span><br/>");
                    }
                });
            });
        })
    </script>
</head>
<body>

        <input id="text" type="text" /><br/>
        <input id="btn" type="button" value="提交">
</body>
</html>
```

不能用表单提交请求，改用jQuery方式动态绑定事件来提交

Servlet不能跳转到JSP，只能将数据返回

```java
package com.yang.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test")
public class TestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String str = "Hello World!";
        resp.getWriter().write(str);
    }
}
```



### 传统的Web数据交互 VS AJAX数据交互

* 客户端请求的方式不同：

  传统：浏览器发送同步请求（form,a）

  AJAX：异步引擎对象发送异步请求

* 服务器响应的方式不同

  传统：响应一个完整JSP页面（试图）

  AJAX：响应需要的数据

* 客户端处理方式不同

  传统：需要等待服务器完成响应并且重新加载整个页面之后，用户才能进行后续的操作

  AJAX：动态更新页面中的局部内容，不影响用户的其他操作



### AJAX原理

![image-20200310103203654](E:\笔记\img\image-20200310103203654.png)



### 基于jQuery的AJAX语法

$.ajax({属性})

* 常用的属性：

  url：请求的后端服务地址

  type：请求方式，默认 get

  data：请求参数

  dataType：服务器返回的数据类型	text/json

  success：请求成功的回调函数

  errror：请求失败的回调函数

  complete：请求完成的回调函数，无论成功或失败都回调用

  中间用逗号隔开  最后一个可以不同加



### JSON

JavaScript Object Notation 	一种轻量级数据交互格式，完成 js 与 java 等后端开发语言对象数据之间的转换

客户端和服务器之间传递对象数据，需要用 JSON 格式

```java
package com.yang.servlet;

import com.yang.entity.User;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test")
public class TestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        User user = new User(1,"张三",99.9);
        JSONObject jsonObject = JSONObject.fromObject(user);
        resp.getWriter().write(jsonObject.toString());
    }
}
```

```jsp
<%--
  Created by IntelliJ IDEA.
  User: 杨
  Date: 2020/3/10
  Time: 12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>AJAX</title>
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        $(function () {
            var btn = $("#btn");
            btn.click(function () {
                $.ajax({
                    url:'/test',
                    type:'post',
                    dataType:'json',
                    success:function (data) {
                        // console.log(data);
                        $("#id").val(data.id)
                        $("#name").val(data.name)
                        $("#score").val(data.score)
                    }
                });
            });
        })
    </script>
  </head>
  <body>
    编号：<input id="id" type="text" /><br/>
    姓名：<input id="name" type="text" /><br/>
    成绩：<input id="score" type="text" /><br/>
    <input type="button" value="提交" id="btn" />
  </body>
</html>
```



### AJAX应用

```jsp
<%--
  Created by IntelliJ IDEA.
  User: 杨
  Date: 2020/3/10
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script>
        $(function () {
            //修改省份
            $("#province").change(function () {
                var id = $(this).val();
                $.ajax({
                    url:'/location',
                    type:'post',
                    data:'id='+id+"&type=province",
                    dataType:'json',
                    success:function (data){
                        //console.log(data);
                        var content ="";
                        var cities = data.cities;
                        var citiesArea = data.areas;
                        for (var i=0;i<cities.length;i++){
                            content += "<option>"+cities[i]+"</option>";
                        }
                        $("#city").html(content);
                        content="";
                        for (var j=0;j<citiesArea.length;j++){
                            content += "<option>"+citiesArea[j]+"</option>";
                        }
                        $("#area").html(content);
                    }
                })
            })
            //修改城市
            $("#city").change(function () {
                var id= $(this).val();
                $.ajax({
                    url:'/location',
                    type:'post',
                    data:'id='+id+'&type=city',
                    dataType:'json',
                    success:function (data) {
                        var  content ="";
                        for (var i=0;i<data.length;i++){
                            content += "<option>"+data[i]+"</option>"
                        }
                        $("#area").html(content);
                    }
                })
            })
        })
    </script>
</head>
<body>
    省：<select id="province">
            <option value="陕西省">陕西省</option>
            <option value="河南省">河南省</option>
            <option value="河北省">河北省</option>
        </select>
    市：<select id="city">
            <option value="西安市">西安市</option>
            <option value="宝鸡市">宝鸡市</option>
            <option value="渭南市">渭南市</option>
        </select>
    区：<select id="area">
            <option>雁塔区</option>
            <option>莲湖区</option>
            <option>新城区</option>
        </select>
</body>
</html>
```

```java
package com.yang.servlet;

import com.yang.entity.Location;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/location")
public class LocationServlet  extends HttpServlet{
    private static Map<String,List<String>> cityMap;
    private static Map<String,List<String>> provinceMap;
    static {
        //由于没有数据库因此用Map来存数据
        //初始化数据
        cityMap = new HashMap<>();
        //陕西省
        List<String> areas = new ArrayList<>();
        areas.add("雁塔区");
        areas.add("莲湖区");
        areas.add("新城区");
        cityMap.put("西安市",areas);
        areas = new ArrayList<>();
        areas.add("宝鸡市的区1");
        areas.add("宝鸡市的区2");
        areas.add("宝鸡市的区3");
        cityMap.put("宝鸡市",areas);
        areas = new ArrayList<>();
        areas.add("渭南市的区1");
        areas.add("渭南市的区2");
        areas.add("渭南市的区3");
        cityMap.put("渭南市",areas);
        //河南省
        areas = new ArrayList<>();
        areas.add("郑州市的区1");
        areas.add("郑州市的区2");
        areas.add("郑州市的区3");
        cityMap.put("郑州市",areas);
        areas = new ArrayList<>();
        areas.add("洛阳市的区1");
        areas.add("洛阳市的区2");
        areas.add("洛阳市的区3");
        cityMap.put("洛阳市",areas);
        areas = new ArrayList<>();
        areas.add("平顶山市的区1");
        areas.add("平顶山市的区2");
        areas.add("平顶山市的区3");
        cityMap.put("平顶山市",areas);

        //河北省
        areas = new ArrayList<>();
        areas.add("唐山市的区1");
        areas.add("唐山市的区2");
        areas.add("唐山市的区3");
        cityMap.put("唐山市",areas);
        areas = new ArrayList<>();
        areas.add("石家庄市的区1");
        areas.add("石家庄市的区2");
        areas.add("石家庄市的区3");
        cityMap.put("石家庄市",areas);
        areas = new ArrayList<>();
        areas.add("衡水市的区1");
        areas.add("衡水市的区2");
        areas.add("衡水市的区3");
        cityMap.put("衡水市",areas);

        provinceMap = new HashMap<>();
        List<String> cities = new ArrayList<>();
        cities.add("西安市");
        cities.add("宝鸡市");
        cities.add("渭南市");
        provinceMap.put("陕西省",cities);

        cities = new ArrayList<>();
        cities.add("郑州市");
        cities.add("洛阳市");
        cities.add("平顶山市");
        provinceMap.put("河南省",cities);

        cities = new ArrayList<>();
        cities.add("唐山市");
        cities.add("石家庄市");
        cities.add("衡水市");
        provinceMap.put("河北省",cities);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        String id = req.getParameter("id");
        String type = req.getParameter("type");
        switch (type){
            case "city":
                List<String> areas = cityMap.get(id);
                JSONArray jsonArray = JSONArray.fromObject(areas);
                resp.getWriter().write(jsonArray.toString());
                break;
            case "province":
                List<String> cities = provinceMap.get(id);
                String city = cities.get(0);
                List<String> citiesAreas = cityMap.get(city);
                Location location = new Location(cities,citiesAreas);
                JSONObject jsonObject = JSONObject.fromObject(location);
                resp.getWriter().write(jsonObject.toString());
                break;
        }


    }
}
```

