### Tomcat

Web服务器：tomcat、JBoss、Webblogic、Jetty

##### 安装Tomcat

1 官网下载  https://tomcat.apache.org/download-90.cgi

![image-20200301100857946](E:\笔记\img\image-20200301100857946.png)

2 解压缩

​	bin： 存放各个平台下启动和停止Tomcat服务的脚本文件

​	conf: 存放各种Tomcat服务器的配置文件

​	lib： 存放Tomcat服务器的jar包

​	logs：存放Tomcat服务运行的日志文件

​	temp：Tomcat运行时的临时文件

​	webapps：存放客户端允许访问的资源（java程序）

​	work:存放Tomcat将JSP转换之后的Servlet文件



### IDEA集成Tomcat（创建Web项目）

1、创建Java Web工程

![image-20200301102047649](E:\笔记\img\image-20200301102047649.png)

![image-20200301102216395](E:\笔记\img\image-20200301102216395.png)

3、IDEA中配置tomcat

![image-20200301102824518](E:\笔记\img\image-20200301102824518.png)

![image-20200301102903813](E:\笔记\img\image-20200301102903813.png)

![image-20200301103032011](E:\笔记\img\image-20200301103032011.png)

![image-20200301103055396](E:\笔记\img\image-20200301103055396.png)

选择本地的tomcat路径即可

4、把项目加入到tomcat中

![image-20200301103514813](E:\笔记\img\image-20200301103514813.png)

热部署

![image-20200301103744106](E:\笔记\img\image-20200301103744106.png)


