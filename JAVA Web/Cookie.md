# Cookie

 Cookie是服务端在HTTP响应中附带传给浏览器的一个小文本文件，一旦浏览器保存了某了Cookie，在之后的请求和响应过程中，会将此Cookie来回传递，这样就可以 通过Cookie 这个载体完成客户端个服务器端的服务交互。

Cookie

​	创建 Cookie

```java
Cookie cookie = new Cookie("name","tom");
response.addCookie(cookie);
```

​	读取Cookie

```java
Cookie[] cookies = request.getCookies();
for(Cookie cookie:ckkoies){
	out.writer(cookie.getName()+":"+cookie.getValuee()+"<br/>");
}
```



Cookie常用的方法

1、void setMaxAge(int age)	设置Cookie的有效时间，单位为秒

2、int getMaxAge()		获取Cookie的有效时间

3、String 	getName()	获取Cookie的name

4、Strring	getValue()	获取Cookie的value



### Session和Cookie的区别

session：保存在服务器

​				 保存的数据是Object

​				 会随着会话的结束而销毁

​				 保存重要信息



cookie：保存在浏览器

​				保存的数据是String

​				可以长期保存在浏览器中，于会话无关

​				保存不重要信息 



从存储用户信息比较：

​	session： setAttribute("name","admin)   存

​					  getAttribute("name")			取

​					  生命周期： 服务器端:只要Web应用重启就销毁；  客户端： 只要浏览器关闭就销毁

​					退出登录：session.invalidate()

​	Cookie：response.addCookie(new Cookie(name,"admin))   	存

```java
//取
Cookie[] cookies = request.getCookies();
for(Cookie cookie:cookies){
	iif(cookie.getName().equals("name")){
	out.writer("欢迎回来"+cookies.getValue());
	}
}
```

​	生命周期：不随服务器端的重启而销毁；客户端：默认是只要关闭浏览器就销毁，我们通过setMaxAge()方法设置有效期，一旦设置了有效期，则不随浏览器的关闭而销毁，而是由设置的时间来决定。

​	退出登录：setMaxAge(0)