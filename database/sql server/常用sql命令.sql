/*==========================ɾ����=====================*/
drop table chat;
drop table client;
/*==========================������=====================*/
CREATE TABLE Client(
	userid			VARCHAR(30)		PRIMARY KEY ,
	name			VARCHAR(30)		NOT NULL ,
	password		VARCHAR(32)		NOT NULL,
	sex			VARCHAR(4)		default '��',
	age			int,
	signature		VARCHAR(40),
	touxiangpath		VARCHAR(20)		default 'Tx/01.jpg',
	e_mail			VARCHAR(20),
	realname		VARCHAR(10),
	isonline			int 			default 0 , 
	ztpath			varchar(20)         default '1.jpg'
) ;





CREATE TABLE chat(
	userid			VARCHAR(30)		,
	friendid		VARCHAR(30)		,
	filepath		VARCHAR(50)		,
	friendtype		int		default 1,
	offlinepath		VARCHAR(50)		,			
	PRIMARY KEY(userid, friendid)  , 
	foreign key (userid) references Client(userid) , 
	foreign key (friendid) references Client(userid));
/*======================= ����������� =======================*/

INSERT INTO Client(userid,name,password,age,signature,e_mail,realname) VALUES ('001','����ΰ','123',21,'�ճ�������Ϊ�Ҳ���','1245268285@qq.com','����ΰ') ;
INSERT INTO Client(userid,name,password,age,signature,e_mail,realname) VALUES ('002','������','123',21,'Contracted and not simple','691228551@qq.com','������') ;
INSERT INTO Client(userid,name,password,age,signature,e_mail,realname) VALUES ('003','��ǿ','123',21,'��ʼ���У�','1245268285@qq.com','��ǿ') ;
INSERT INTO Client(userid,name,password,age,signature,e_mail,realname) VALUES ('004','����','123',21,'���������ͻ���ϸ��Ǿޱ��','313597957@qq.com','����') ;
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