/*****************************************
数据库操作工具类，执行sql语句、存储过程等
create by zhaoheng
datetime:2017/4/14
javafile:SqlConnect.java
package:zhtest.connect.database
site:d:\zhjava\RmiII\javacodesource\dbhelper
reference jar file:sqljdbc4.jar;site:d:\zhjava\jar
******************************************/


package zhtest.connect.database;

import java.lang.*;
import java.sql.*;


public class SqlConnect{
	private final static String cfn="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String url=null;
	private String userID=null;
	private String password=null;
	private Connection con=null;
	private PreparedStatement statement=null;
	public SqlConnect(String ipAdd,String port,String DatabaseName,String userID,String password){
		this.url=String.format("jdbc:sqlserver://%s:%s;DatabaseName=%s",ipAdd,port,DatabaseName);
		this.userID=userID;
		this.password=password;
		
		try{
			//加载sqlserver驱动程序
			Class.forName(cfn);
		    //连接sqlserver返回Connection接口的子类型对象
		    con=DriverManager.getConnection(url,this.userID,this.password);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}		
	}
	//执行一条sql语句并返回结果集
	public ResultSet executeQuery(String sql){
		try{
			statement=con.prepareStatement(sql);
		    return statement.executeQuery();	
		}
		catch(Exception ex){
			System.out.println("消息："+ex.getMessage());
			ex.printStackTrace();
		}	
		return null;
	}
	
	public int executeNonQuery(String sql){
		try{
			statement=con.prepareStatement(sql);
		    return statement.executeUpdate();	
		}
		catch(Exception ex){
			System.out.println("消息："+ex.getMessage());
			ex.printStackTrace();
		}	
		return 0;
	}
	
    
	public void disposeSource(){
		try{
			if(statement!=null){
				statement.close();
			}
			if(con!=null){
				con.close();
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}				
	}
}