package com.mqserver.dao.proxy ;

import com.mq.client.common.User;
import com.mqserver.dao.*;
import com.mqserver.dao.impl.*;
import com.mqserver.dbc.*;

import java.util.HashMap;
import java.util.List;
public class UserDAOProxy implements IUserDAO {
	private DatabaseConnection dbc = null ;
	private IUserDAO dao = null ;
	public UserDAOProxy(){
		try{
			this.dbc = new DatabaseConnection() ;
		}catch(Exception e){
			e.printStackTrace() ;
		}
		this.dao = new UserDAOImpl(dbc.getConnection()) ;
	}
	public boolean findLogin(User user) throws Exception{
		boolean flag = false ;
		try{
			flag = this.dao.findLogin(user) ;	// 调用真实主题，完成操作
		}catch(Exception e){
			throw e ;
		}finally{
			this.dbc.close() ;
		}
		return flag ;
	}
	public String register(User user) throws Exception{
		try{
			String id = this.dao.register(user);
			return id;
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
	}
	@Override
	public List<User> searchFriend(String pro) throws Exception {
		// TODO Auto-generated method stub
		try{
			List<User> userList = this.dao.searchFriend(pro);
			return userList;
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
	}
	@Override
	public void addChat(String get, String send, String path) throws Exception {
		// TODO Auto-generated method stub
		try{
			this.dao.addChat(get, send, path);
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
	}
	@Override
	public List<User> showFriend(String pro) throws Exception {
		// TODO Auto-generated method stub
		try{
			List<User> userList = this.dao.showFriend(pro);
			return userList;
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
	}
	@Override
	public boolean isonline(String pro) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		try{
			flag = this.dao.isonline(pro);
			return flag;
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
	}
	@Override
	public void addOfflineChat(String get, String send, String path) throws Exception {
		// TODO Auto-generated method stub
		try{
			this.dao.addOfflineChat(get, send, path);
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
	}
	@Override
	public HashMap<String,String> getOfflineChat(String pro) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String,String> strMap = new HashMap<String, String>();
		try{
			strMap = this.dao.getOfflineChat(pro);
			return strMap;
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
	}
	@Override
	public void deleteOffline(String pro) throws Exception {
		// TODO Auto-generated method stub
		try{
			this.dao.deleteOffline(pro);
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
	}
	@Override
	public String showChatRecord(String get, String send) throws Exception {
		// TODO Auto-generated method stub
		String str = null;
		try{
			str = this.dao.showChatRecord(get, send);
			return str;
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
		
	}
	@Override
	public User getUser(String pro) throws Exception {
		// TODO Auto-generated method stub
		User user = null;
		try{
			user = this.dao.getUser(pro);
			return user;
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
		
	}
	@Override
	public void addFriend(String get, String send, int type) throws Exception {
		// TODO Auto-generated method stub
		try{
			this.dao.addFriend(get, send, type);
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
	}
	@Override
	public void delFriend(String get, String send) throws Exception {
		// TODO Auto-generated method stub
		try{
			this.dao.delFriend(get, send);
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
	}
	@Override
	public void Offline(String pro) throws Exception {
		// TODO Auto-generated method stub
		try{
			this.dao.Offline(pro);
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
	}
	@Override
	public void updateSignature(String pro, String get) throws Exception {
		// TODO Auto-generated method stub
		try{
			this.dao.updateSignature(pro, get);
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
	}
	@Override
	public void updateTX_image(String get, String im) throws Exception {
		// TODO Auto-generated method stub
		try{
			this.dao.updateTX_image(get, im);
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
	}
	@Override
	public boolean isFriend(String get, String send) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		try{
			flag = this.dao.isFriend(get, send);
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
		return flag;
	}
	@Override
	public void updateSkinPath(String get, String im) throws Exception {
		// TODO Auto-generated method stub
		try{
			this.dao.updateSkinPath(get, im);
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
	}
	@Override
	public void updateUser(User user) throws Exception {
		// TODO Auto-generated method stub
		try{
			this.dao.updateUser(user);
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
	}
	
} 