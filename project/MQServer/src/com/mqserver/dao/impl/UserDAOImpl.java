package com.mqserver.dao.impl ;

import com.mq.client.common.User;
import com.mqserver.dao.*;

import java.sql.* ;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class UserDAOImpl implements IUserDAO {
	private Connection conn = null ;
	private PreparedStatement pstmt = null ;
	public UserDAOImpl(Connection conn){
		this.conn = conn ;
	}
	public boolean findLogin(User user) throws Exception{
		boolean flag = false ;
		String sql = "SELECT name FROM client WHERE userid=? AND password=?" ;
		this.pstmt = this.conn.prepareStatement(sql) ;
		this.pstmt.setString(1,user.getUserId()) ;
		this.pstmt.setString(2,user.getPasswd()) ;
		ResultSet rs = this.pstmt.executeQuery() ;
		if(rs.next()){
			flag = true ;
			String sql1 = "UPDATE client set isonline=? where userid=?";
			this.pstmt = this.conn.prepareStatement(sql1);
			this.pstmt.setInt(1, 1);
			this.pstmt.setString(2, user.getUserId());
			this.pstmt.executeUpdate();
		}
		this.pstmt.close() ;
		return flag ;
	}
	
	public String register(User user) throws Exception{
		boolean flag = true;
		String id = null;
		int idtemp;
		while(flag){
			idtemp = (int)(Math.random() * 1000);
			if(idtemp < 10){
				id = "00" + Integer.toString(idtemp);
			}else if(idtemp < 100){
				id = "0" + Integer.toString(idtemp);
			}else{
				id = Integer.toString(idtemp);
			}
			String sql = "SELECT * FROM client WHERE userid=?";
			this.pstmt = this.conn.prepareStatement(sql) ;
			this.pstmt.setString(1,id) ;
			ResultSet rs = this.pstmt.executeQuery() ;
			if(rs.next()){
				flag = true;
			}else{
				flag = false;
			}
		}
		String sql = "INSERT INTO client(userid, name, password, sex, age, signature, touxiangpath, e_mail, realname) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		this.pstmt = this.conn.prepareStatement(sql) ;
		this.pstmt.setString(1,id) ;
		this.pstmt.setString(2,user.getUserName()) ;
		this.pstmt.setString(3,user.getPasswd()) ;
		this.pstmt.setString(4, user.getSex());
		this.pstmt.setInt(5, user.getAge());
		this.pstmt.setString(6, user.getSignature());
		this.pstmt.setString(7, user.getTxpath());
		this.pstmt.setString(8, user.getEmail());
		this.pstmt.setString(9, user.getName());
		this.pstmt.executeUpdate();
		this.pstmt.close();
		return id;
	}
	@Override
	public List<User> searchFriend(String pro) throws Exception {
		// TODO Auto-generated method stub
		User user = null;
		List<User> userList = new ArrayList<User>();
		String sql = "SELECT name, userid FROM client WHERE userid=? or name=?" ;
		this.pstmt = this.conn.prepareStatement(sql) ;
		this.pstmt.setString(1,pro) ;
		this.pstmt.setString(2,pro);
		ResultSet rs = this.pstmt.executeQuery() ;
		while(rs.next()){
			user = new User();
			user.setUserName(rs.getString(1)) ;	// 取出一个用户的真实姓名
			user.setUserId(rs.getString(2));
			userList.add(user);
		}
		this.pstmt.close() ;
		return userList;
	}
	
	@Override
	public void addChat(String get, String send, String path) throws Exception {
		// TODO Auto-generated method stub
		String sql = "update chat set filepath=? where userid=? and friendid=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, path);
		this.pstmt.setString(2, get);
		this.pstmt.setString(3, send);
		this.pstmt.executeUpdate();
		
		this.pstmt.close();
	}
	@Override
	public List<User> showFriend(String pro) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select friendid, y.name, y.isonline, friendtype, y.touxiangpath from client x, client y, chat where x.userid=chat.userid and chat.friendid=y.userid and x.userid=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, pro);
		ResultSet rs = this.pstmt.executeQuery();
		
		User user = null;
		List<User> userList = new ArrayList<User>();
		while(rs.next()){
			user = new User();
			
			user.setUserId(rs.getString(1));
			user.setUserName(rs.getString(2)) ;	// 取出一个用户的真实姓名
			user.setType(rs.getInt(3));
			user.setUserType(rs.getInt(4));
			user.setTxpath(rs.getString(5));
			userList.add(user);
		}
		
		this.pstmt.close();
		return userList;
		
	}
	@Override
	public boolean isonline(String pro) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false ;
		String sql = "SELECT isonline FROM client WHERE USERID=?";
		this.pstmt= this.conn.prepareStatement(sql);
		this.pstmt.setString(1, pro);
		ResultSet rs = this.pstmt.executeQuery();
		while(rs.next()){
			if(rs.getInt(1) == 1){
				flag = true;
			}else
				flag = false;
		}
		return flag;
		
	}
	@Override
	public void addOfflineChat(String get, String send, String path) throws Exception {
		// TODO Auto-generated method stub
		String sql ="UPDATE chat SET offlinepath=? where userid=? and friendid=?";
		this.pstmt= this.conn.prepareStatement(sql);
		this.pstmt.setString(1, path);
		this.pstmt.setString(2, get);
		this.pstmt.setString(3, send);
		this.pstmt.executeUpdate();
		
		this.pstmt.close();
	}
	@Override
	public HashMap<String, String> getOfflineChat(String pro) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String,String> strMap = new HashMap<String, String>();
		String sql = "SELECT userid,offlinepath FROM chat WHERE friendid=? and offlinepath is not null";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, pro);
		ResultSet rs = this.pstmt.executeQuery();
		while(rs.next()){
			
			String id = rs.getString(1);
			String path = rs.getString(2);
			strMap.put(id, path);
		}
		return strMap;
	}
	@Override
	public void deleteOffline(String pro) throws Exception {
		// TODO Auto-generated method stub
		String sql = "UPDATE chat set offlinepath=null where friendid=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, pro);
		this.pstmt.executeUpdate();
		this.pstmt.close();
	}
	@Override
	public String showChatRecord(String get, String send) throws Exception {
		// TODO Auto-generated method stub
		String path = null;
		String sql = "SELECT FILEPATH FROM CHAT WHERE userid=? AND friendid=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, get);
		this.pstmt.setString(2, send);
		ResultSet rs = this.pstmt.executeQuery();
		while(rs.next()){
			path = rs.getString(1);
		}
		this.pstmt.close();
		return path;
	}
	@Override
	public User getUser(String pro) throws Exception {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUserId(pro);
		String sql = "SELECT name, userid, sex, age, signature, touxiangpath, e_mail, realname, ztpath , password FROM client WHERE userid=?";
		this.pstmt =  this.conn.prepareStatement(sql);
		this.pstmt.setString(1, pro);
		ResultSet rs = this.pstmt.executeQuery();
		while(rs.next()){
			user.setUserName(rs.getString(1));
			user.setUserId(rs.getString(2));
			user.setSex(rs.getString(3));
			user.setAge(rs.getInt(4));
			user.setSignature(rs.getString(5));
			user.setTxpath(rs.getString(6));
			user.setEmail(rs.getString(7));
			user.setName(rs.getString(8));
			user.setSkinPath(rs.getString(9));
			user.setPasswd(rs.getString(10));
		}
		this.pstmt.close();
		return user;
	}
	@Override
	public void addFriend(String get, String send, int type) throws Exception {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO chat(userid, friendid, friendtype) VALUES(?, ?, ?) ";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, get);
		this.pstmt.setString(2, send);
		this.pstmt.setInt(3, type);
		this.pstmt.executeUpdate();
		this.pstmt.close();
	}
	@Override
	public void delFriend(String get, String send) throws Exception {
		// TODO Auto-generated method stub
		String sql = "delete from chat where userid=? and friendid=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, get);
		this.pstmt.setString(2, send);
		this.pstmt.executeUpdate();
		this.pstmt.close();
	}
	@Override
	public void Offline(String pro) throws Exception {
		// TODO Auto-generated method stub
		String sql = "update client set isonline=0 where userid=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, pro);
		this.pstmt.executeUpdate();
		this.pstmt.close();
	}
	@Override
	public void updateSignature(String pro,String get) throws Exception {
		// TODO Auto-generated method stub
		String sql = "update client set signature=? where userid=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, pro);
		this.pstmt.setString(2, get);
		this.pstmt.executeUpdate();
		this.pstmt.close();
	}
	@Override
	public void updateTX_image(String get, String im) throws Exception {
		// TODO Auto-generated method stub
		String sql = "update client set touxiangpath=? where userid=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, im);
		this.pstmt.setString(2, get);
		this.pstmt.executeUpdate();
		this.pstmt.close();
	}
	@Override
	public boolean isFriend(String get, String send) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		String sql = "SELECT * FROM chat WHERE userid=? and friendid=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, get);
		this.pstmt.setString(2, send);
		ResultSet rs = this.pstmt.executeQuery();
		if(rs.next()){
			flag = false;
		}else{
			flag = true;
		}
		return flag;
	}
	@Override
	public void updateSkinPath(String get, String im) throws Exception {
		// TODO Auto-generated method stub
		String sql = "update client set ztpath=? where userid=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, im);
		this.pstmt.setString(2, get);
		this.pstmt.executeUpdate();
		this.pstmt.close();
	}
	@Override
	public void updateUser(User user) throws Exception {
		// TODO Auto-generated method stub
		String sql = "update client set name=? ,password=? ,sex=? ,age=? ,signature=? ,touxiangpath=? ,e_mail=? ,realname=? where userid=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, user.getUserName());
		this.pstmt.setString(2, user.getPasswd());
		this.pstmt.setString(3, user.getSex());
		this.pstmt.setInt(4, user.getAge());
		this.pstmt.setString(5, user.getSignature());
		this.pstmt.setString(6, user.getTxpath());
		this.pstmt.setString(7, user.getEmail());
		this.pstmt.setString(8, user.getName());
		this.pstmt.setString(9, user.getUserId());
		this.pstmt.executeUpdate();
		this.pstmt.close();
	}
	
} 