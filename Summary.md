# 对以往错题知识点的总结

###### 1	

​	Serializable接口是专门提供给类实现序列化用的。要实现序列化对象必须要实现 Serializable 接口

###### 2	

​	String类不可变，指的是String对象内容不可变，因为'String对象存在常量池中，而String的引用是可以可变，可以为String引用赋予新的对象字符串。

###### 3	

​	checked exception：指的是编译时异常，该类异常需要本函数必须处理的，用try和catch处理，或者用throws抛出异常，然后交给调用者去处理异常。runtime exception：指的是运行时异常，该类异常不必须本函数必须处理，当然也可以处理。Thread.sleep()抛出的InterruptException属于checked exception；IllegalArgumentException属于Runtime exception;

###### 4 

```java
public class Test {
public static void main(String[] args) {
System.out.println(test());

}
private static int test() {
int temp = 1;
try {
System.out.println(temp);
return ++temp;
} catch (Exception e) {
System.out.println(temp);
return ++temp;
} finally {
++temp;
System.out.println(temp);
}
}
}
```

  输出结果为 1 3 2
  （1）若try代码块内含有return，同时存在finally代码块（代码块内无return值）时，先执行finally函数的值。
  （2）若try代码块内含有return，同时存在finally代码块且代码块内含有return值时，此时finally代码块内的return值将直接返回（或覆盖掉try代码块中的return值）。

###### 5	

​	ThreadLocal可以给一个初始值，而每个线程都会获得这个初始化值的一个副本，这样才能保证不同的线程都有一份拷贝。ThreadLocal 不是用于解决共享变量的问题的，不是为了协调线程同步而存在，而是为了方便每个线程处理自己的状态而引入的一个机制.

###### 6	内部类 

![image-20200224111407142](https://github.com/Em-yang/Summary/raw/master/img/image-20200224111407142.png)

###### 7	

​	数组命名时名称与[]可以随意排列，但声明的二维数组中第一个中括号中必须要有值，它代表的是在该二维数组中有多少个一维数组。

![image-20200224111630089](https://github.com/Em-yang/Summary/raw/master/img/image-20200224111630089.png)

###### 8	switch

```java
void main(void) {
    char *s = "1314520";
    int v1 = 0, v2 = 0, v3 = 0, v4 =0;
    for (int i = 0; s[i]; i++) {
        switch(s[i]) {
            default: v4++;
            case '1': v1++;
            case '2': v2++;
            cas3 '3': v3++;
        }
    }
    printf("%d, %d, %d, %d", v4,v1,v2,v3)
}
输出为： 3 5 6 7
```

default是所有case条件不符合时才会执行；case语句若不加break，则当一条满足条件的case语句执行后，其下面的case语句都会执行。

###### 9	遇到 无法从静态上下文中引用非静态方法 的问题

​		有两种解决方法：

​				1. 把调用的方法改成静态方法

​				2. 创建一个该类的对象，用对象来调用这个方法
