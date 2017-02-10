package com.mqserver.factory ;
import com.mqserver.dao.*;
import com.mqserver.dao.proxy.*;
public class DAOFactory {
	public static IUserDAO getIUserDAOInstance(){
		return new UserDAOProxy() ;
	}
}