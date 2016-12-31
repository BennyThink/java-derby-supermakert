# java-derby-supermakert
A java derby database demo program.

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
Default username and passowrd is Betty/960718 and Bunny/111111.<br>
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
