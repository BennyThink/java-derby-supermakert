# java-derby-supermakert
A java derby database demo program.
**2017年4月14日更新**

## 明月你先看我 ##
选择***REMOVED***分支，至少你的英语水平应该足够看懂这个readme吧
默认登陆的账号密码是moon/***REMOVED***,Benny/***REMOVED***

一些目录的说明（dist目录需要你手动构建才会有）：
* dist中为jar文件
* dist中的javadoc为文档，你可以参考文档来“二次开发”
* src为由NetBeans生成的源代码
* market为数据库文件

## 开发工具 ##
NetBeans + JDK8u74 (1.8.0_74)

## 思想 ##
我今天给它大改，改成了比较纯正的OOP模式，虽说写的不咋样，但是也算得上足够参考能让你迈出打败“见类死”的第一步。
整个程序有三个包：
* ***REMOVED***.images 这个包用来保存icon、图片等
* ***REMOVED***.main   两个JFrame的主要文件
* ***REMOVED***.util   提供数据库、加盐散列函数相关的类

所有的数据库查询操作都是使用DBUtil类中的对应方法，而不是每个事件都经历建立连接-查询步骤。

美中不足的是，数据库要跟着jar（或者是NetBeans工程目录）走，解决方法也很简单，在`DBUtil`的构造方法中，取消那段注释就行了。缺点是会影响一点点程序启动的速度（需要构造嘛！）

## 数据库建表语句 ##
```
//@moon:通常来说你是不需要这个文件的。这里提供下表结构，以供参考。

connect 'jdbc:derby:market;create=true';

create table product(pID char(10) primary key, pName char(20),pProducer char(20),pOrigin char(10),pDate char(20),pPrice1 char(20),pPrice2 char(20));
create table admin(aID char(1),aUser varchar(10) primary key,aPassword varchar(140));

insert into admin values('0','moon','1000:6c0cfefea37e6b2a21d1822a4a9e5f0bb1e37b91971e578d:32907c9856b72c9190ed71f602f9138ba077af9d7df1ea85');
insert into admin values('1','Benny','1000:871eced7f4773c952b2fae7688087af85f1886840f04c645:39afda635b7a2d33553ba234866e86d96089729ec93fef60');

insert into product values('1','康师傅牛肉面','统一','沈阳','2016-3-5','2','3');
insert into product values('2','牛奶','蒙牛','沈阳','2016-4-5','2','4');
insert into product values('3','酸奶','统二','大连','2016-3-5','2','3');
insert into product values('4','冰红茶','雪公司','沈阳','2015-3-5','4','5');
insert into product values('5','冰绿茶','可口可乐公司','沈阳','2016-6-5','5','6');
```

## 其他的一些说明 ##
### 隐藏的bug ###
对于JTable，是使用了一些非常不好的方式来进行“初始化”，实际上正经的方法英是使用`DefaultTableModel`。所以在这可能会隐藏一些问题。
### 代码风格 ###
一些代码段风格可能很差劲，try和if来回嵌套，造成可读性非常低；如果有时间我会优化下的。
### 安全性 ###
### MySQL支持 ###
本Demo是支持MySQL的，需要你在`DBUtil.java`注释derby驱动，中取消MySQL的注释并修改连接字符串。
根据“柯克霍夫原则”，我需要做到哪怕别人拿到源代码、拿到数据库、用户还使用了123456这样的弱口令也无法逆向破解用户密码（尽管这只是一个小课设，但是安全习惯却要从小养成）。所以在这里我使用了加盐散列函数，它的主要特性有：
* 生成随机盐值：哪怕设置了相同的密码，存在数据库中的内容也是不同的。可以有效防范字典攻击、彩虹表。
* 慢散列函数：PBKDF2WithHmacSHA1  防范暴力破解。
* 慢比较算法：防范边信道攻击
咱可以拿这个来好好吹嘘一番……哈哈，很多程序员的安全意识都比较差；更是有很多人不知道什么是散列函数。

### 其他 ###
数据库的表结构可能不咋样，某处可能违反了OOP的设计原则，等等，这些问题都是存在的。嗯……



# 原始Readme #

What's this?
====
This is a java program during my Course Exercise Design. As the title has said, it's a demo with java derby database support.<br>
Design in NetBeans.

How to use it?
====
First, you need to install Java Development Kit or simply Java Runtime Enviroment.(suggest jdk)<br>
Then, you need to copy at least one derby.jar from `your_dir\jdk1.8.0_74\db\lib` to `your_dir\jdk1.8.0_74\jre\lib\ext`<br>
And now you are good to go! Just simply click the jar file in `build` and you will see it running!

Where did you save the database files?
====
Do you see a directory named `market`? That's the database files you're looking for.

What is derby? Compare to MySQL, MariaDB, Oracle...?
====
Apache Derby, an Apache DB subproject,
is an open source relational database implemented entirely in Java 
and available under the Apache License, Version 2.0.<br>
Well, it is a very very...tiny database system that all it needs is a 2.96MB jar file 
which includes base engine and embedded JDBC driver. 
Anyway, it was designed to be an embedded database engine.

How to build it manually?
====
Easy! In the `src` directory you will see some images and two java source files. Just simply cd to this directory, type:<br>
`javac Login.java`<br>
`java  Login`<br>
Don't forget to copy the database dirctory to the exact directory where the program lies in.

I cannot login/what's the default admin password?
====
Default username and passowrd is moon/***REMOVED*** and Bunny/111111.<br>
Don't ask me why there's no "Benny".

How could I run my own SQL command?
====
Quiet simply, in `your_path\jdk1.8.0_74\db\bin` you could see a `ij.bat`.<br>
Run it under your database directory (strongly advise to add this path to Environment Path).<br>
Then, type:<br>
`connect 'jdbc:derby:market;create=true';`<br>
"market" is the name of your database (here refers to the directory).
Ok, all set, you may run `insert into values`, `create table` and almost any SQL commands.

Cross-platform compatibility
====
Well, java is intended to let application developers "write once, run anywhere".

I have problems, I need help.
====
Well, contact me via issues, [emails](mailto:benny@bennythink.com) and [my blogsite](https://www.bennythink.com/).

License
====
MIT License. Use it whatever you want!
