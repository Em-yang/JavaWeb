# JDBC

Java DataBase Connectivity  是一个独立于特定数据库的管理系统，通用的SQL数据库存取和操作的公共接口，定义了一组接口，为访问不同的数据库提供了统一的接口

![image-20200310201658336](https://github.com/Em-yang/JavaWeb/blob/master/img/image-20200310201658336.png)



### JDBC体系结构

JDBC接口包括两个层面：

* 面向应用的API，供程序员使用
* 面向数据库的API,供厂商开发数据库的驱动程序

![image-20200310202319287](https://github.com/Em-yang/JavaWeb/blob/master/img/image-20200310202319287.png)



JDBC API

提供者：Java官方

内容：供开发者调用的接口

java.sql 和 javax.sql

* DriiverrManager类
* Connectiion类
* Statement类
* ResultSet类

DriverManager

提供者：java官方

作用：管理不同的JDBC驱动

JDBC驱动

提供者：数据库厂商

作用：负责连接不同的数据库



### JDBC的使用

1、加载数据库驱动，java程序与数据库之间的桥梁

2、获取Connection，Java程序与数据库的一次连接

3、创建Statement对象，由Connection产生，执行SQL语句

4、如果需要接受返回值， 创建ResultSet对象，保存Statement执行之后所查询到的结果。

```java
package com.yang.domain;

import java.sql.*;

public class Test {

    public  static void main(String[] args){

        try {
            //1、加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            String url="jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8";
            String user = "root";
            String password = "981015yang";
            Connection connection = DriverManager.getConnection(url,user,password);
//            String sql = "insert into user(name,score) values('Shary',99)";
//            String sql = "UPDATE user SET NAME ='pity'";
//            String sql = "DELETE FROM USER ";
//            Statement statement = connection.createStatement();
//            int i = statement.executeUpdate(sql);
            String sql = "select * from user";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString(2);
                Double score = resultSet.getDouble(3);
                System.out.println(id+"-"+name+"-"+score);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
```



### PreparedStatement

Sttatement的子类，提供了SQL占位符的功能。

使用 Statement 开发有两个问题

1、需要频繁拼接 String 字符串，出错率较高

2、存在SQL 注入的风险

SQL注入：利用某些系统没有对用户输入的信息进行充分检测，在用户输入的数据中注入非法的SQL语句，从而利用系统的sql引擎完成恶意行为的做法。

```java
try {
            //1、加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            String url="jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8";
            String user = "root";
            String password = "981015yang";
            Connection connection = DriverManager.getConnection(url,user,password);
            String username = "TOM";
            String userpassword = "123";
            String sql = "select * from  user  where name = ? and password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,userpassword);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                System.out.println("成功");
            }else {
                System.out.println("失败");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
```
