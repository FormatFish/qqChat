# description
the project coded by java and use the factory mode and proxy mode , the whole structure is MVC which simulate the django, also we use the multi-thread to process the instant information. this project made by four person who are my university-mate. And my work is make the whole project structure and make sure how to interaction between the users. And the main interface writed by me.

# function
- Register
- login
- friend manage
- chat
- user information manage
- tips
- personalization
- file transfer

# structure
![main structure](http://image.cethik.vip/images/2017/02/10/2017-02-10_13-05-06.png)
![client struct](http://image.cethik.vip/images/2017/02/10/2017-02-10_13-05-16.png)
![server struct](http://image.cethik.vip/images/2017/02/10/2017-02-10_13-05-29.png)


# install and deploy
1. open folder
![open folder](http://image.cethik.vip/images/2017/02/10/2017-02-10_13-23-56.png)
2. open the "project" and open the eclipse. and "Create a new java Project"
![create a new java pro](http://image.cethik.vip/images/2017/02/10/2017-02-10_13-25-13.png)
3. The server folder ops like the 2
4. file introduce
![file introduce](http://image.cethik.vip/images/2017/02/10/2017-02-10_13-26-26.png)
5. add the self-jar from self-jar folder
![self-jar](http://image.cethik.vip/images/2017/02/10/2017-02-10_13-28-08.png)
![add lib](http://image.cethik.vip/images/2017/02/10/2017-02-10_13-28-48.png)
![add lib2](http://image.cethik.vip/images/2017/02/10/2017-02-10_13-29-19.png)
![add self jar](http://image.cethik.vip/images/2017/02/10/2017-02-10_13-30-03.png)

6. the server project operate like 5, you should add the self-jar in the server project.
7. this project's default database is SQL Server , if you want to use other database, you should find the jar and the sql to create ,this is a introduce.
![database](http://image.cethik.vip/images/2017/02/10/2017-02-10_13-33-14.png)

8. you should modify the java file ,find com.mqserver.dbc in MQServer , and exchange the database java file by DatebaseConnection.java.
![change database](http://image.cethik.vip/images/2017/02/10/2017-02-10_13-35-12.png)

9. there are sql cmd file in the database folder ,you can copy to the target database terminal. the default database is "JavaChat" , if you don't like this name ,you can change in here:
![change database name](http://image.cethik.vip/images/2017/02/10/2017-02-10_13-37-23.png)

![SQLSever](http://image.cethik.vip/images/2017/02/10/2017-02-10_13-37-49.png)

this is result:
![effect](http://image.cethik.vip/images/2017/02/10/2017-02-10_13-38-20.png)

10. OK, It's time to check the project , first of all , you should run the server , which is main file in com.mqserver.main
![runserver](http://image.cethik.vip/images/2017/02/10/2017-02-10_13-39-54.png)

11. And as 10 , you should run the main file in MQClient's com.mq.client.main
![run client](http://image.cethik.vip/images/2017/02/10/2017-02-10_13-41-09.png)

12. ok , enjoy it .


# how to use
## start server
![start](http://image.cethik.vip/images/2017/02/10/2017-02-10_13-13-17.png)
## Register a account
![register](http://image.cethik.vip/images/2017/02/10/2017-02-10_13-14-01.png)
## login
![login](http://image.cethik.vip/images/2017/02/10/2017-02-10_13-14-34.png)

## personalization
![info_change](http://image.cethik.vip/images/2017/02/10/2017-02-10_13-15-12.png)
![skinChange](http://image.cethik.vip/images/2017/02/10/2017-02-10_13-12-23.png)

## add friend
![add friend](http://image.cethik.vip/images/2017/02/10/2017-02-10_13-16-04.png)

## tips and friend reward
![tips](http://image.cethik.vip/images/2017/02/10/2017-02-10_13-17-12.png)
## online and offline
![line](http://image.cethik.vip/images/2017/02/10/2017-02-10_13-17-40.png)

## chat record and online/offline informatin tips
![information tips online/offline](http://image.cethik.vip/images/2017/02/10/2017-02-10_13-18-27.png)

## chat interface
![chat interface](http://image.cethik.vip/images/2017/02/10/2017-02-10_13-20-12.png)

## delete friends
![delete](http://image.cethik.vip/images/2017/02/10/2017-02-10_13-20-34.png)

## server log
![server log](http://image.cethik.vip/images/2017/02/10/2017-02-10_13-21-14.png)

## file transer
![file transer](http://image.cethik.vip/images/2017/02/10/2017-02-10_13-21-41.png)
![file transer2](http://image.cethik.vip/images/2017/02/10/2017-02-10_13-22-09.png)
![file transer3](http://image.cethik.vip/images/2017/02/10/2017-02-10_13-22-31.png)