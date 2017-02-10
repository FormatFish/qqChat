package com.mqserver.dbc ;
import java.sql.* ;
public class DatabaseConnection {
	private static final String DBDRIVER = "oracle.jdbc.driver.OracleDriver" ;
 	private static final String DBURL = "jdbc:oracle:thin:@localhost:1521:orcl" ;
	private static final String DBUSER = "scott" ;
	private static final String DBPASSWORD = "tiger" ;
	private Connection conn = null ;
	public DatabaseConnection() throws Exception{
		try{
			Class.forName(DBDRIVER) ;
			this.conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD) ;
		}catch(Exception e){
			throw e ;
		}
	}
	public Connection getConnection(){
		return this.conn ;
	}
	public void close() throws Exception{
		if(this.conn != null){
			try{
				this.conn.close() ;
			}catch(Exception e){
				throw e ;
			}
		}
	}
}