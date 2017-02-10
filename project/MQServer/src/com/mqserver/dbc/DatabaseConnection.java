package com.mqserver.dbc ;
import java.sql.* ;
public class DatabaseConnection {
	//private static final String DBDRIVER = "oracle.jdbc.driver.OracleDriver" ;
	
	
	public static void main(String args[]) throws Exception{
		
		new DatabaseConnection();
	}
	
	
	
	
	
	
	private static final String DBDRIVER = "com.mysql.jdbc.Driver" ;
	
  

	
	
 	//private static final String DBURL = "jdbc:oracle:thin:@localhost:1521:orcl" ;
 	//changing
 	private static final String DBURL = "jdbc:mysql://127.0.0.1/mqdatabase" ;
 	
 
 	
	/*private static final String DBUSER = "scott" ;
	private static final String DBPASSWORD = "tiger" ;*/
	
		private static final String DBUSER = "root" ;
	private static final String DBPASSWORD = "000000" ;
	
	
	private Connection conn = null ;
	
	 
	
	
	public DatabaseConnection() throws Exception{
		try{
			Class.forName(DBDRIVER) ;
			this.conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD) ;
			System.out.println("��ϲ�㣬���ӳɹ�������");
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