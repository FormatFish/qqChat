/*==========================删除表=====================*/
drop table chat purge;
drop table client purge;
/*==========================创建表=====================*/
CREATE TABLE Client(
	userid			VARCHAR2(30)		PRIMARY KEY ,
	name			VARCHAR2(30)		NOT NULL ,
	password		VARCHAR2(32)		NOT NULL,
	sex			VARCHAR2(4)		default '男',
	age			NUMBER,
	signature		VARCHAR2(40),
	touxiangpath		VARCHAR2(20)		default 'Tx/01.jpg',
	e_mail			VARCHAR2(20),
	realname		VARCHAR2(10),
	ztpath			VARCHAR2(20)		default '1.jpg',
	isonline			number 			default 0
) ;





CREATE TABLE chat(
	userid			VARCHAR2(30)		,
	friendid		VARCHAR2(30)		,
	filepath		VARCHAR2(50)		,
	friendtype		number		default 1,
	offlinepath		VARCHAR2(50)		,			
	PRIMARY KEY(userid, friendid)  , 
	foreign key (userid) references Client(userid) , 
	foreign key (friendid) references Client(userid));
/*======================= 插入测试数据 =======================*/

INSERT INTO Client(userid,name,password,age,signature,e_mail,realname) VALUES ('001','张雄伟','123',21,'日出东方，为我不败','1245268285@qq.com','张雄伟') ;
INSERT INTO Client(userid,name,password,age,signature,e_mail,realname) VALUES ('002','成泽岩','123',21,'Contracted and not simple','691228551@qq.com','成泽岩') ;
INSERT INTO Client(userid,name,password,age,signature,e_mail,realname) VALUES ('003','崔强','123',21,'开始考研！','1245268285@qq.com','崔强') ;
INSERT INTO Client(userid,name,password,age,signature,e_mail,realname) VALUES ('004','郭浩','123',21,'我心中有猛虎，细嗅蔷薇！','313597957@qq.com','郭浩') ;
INSERT INTO CHAT(userid, friendid) VALUES('001','002');
INSERT INTO CHAT(userid, friendid) VALUES('001','003');
INSERT INTO CHAT(userid, friendid) VALUES('001','004');

INSERT INTO CHAT(userid, friendid) VALUES('002','001');
INSERT INTO CHAT(userid, friendid) VALUES('002','003');
INSERT INTO CHAT(userid, friendid) VALUES('002','004');

INSERT INTO CHAT(userid, friendid) VALUES('003','001');
INSERT INTO CHAT(userid, friendid) VALUES('003','002');
INSERT INTO CHAT(userid, friendid) VALUES('003','004');

INSERT INTO CHAT(userid, friendid) VALUES('004','001');
INSERT INTO CHAT(userid, friendid) VALUES('004','002');
INSERT INTO CHAT(userid, friendid) VALUES('004','003');