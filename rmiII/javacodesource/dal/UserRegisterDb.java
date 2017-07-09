package zhtest.dal;

import java.rmi.*;
import java.sql.*;
import java.util.*;
import zhtest.model.*;
import zhtest.connect.database.*;
import zhtest.rmiinterface.ConferenceInterface;


public class UserRegisterDb extends java.rmi.server.UnicastRemoteObject implements ConferenceInterface{
	
	private SqlConnect con;

    public UserRegisterDb() throws Exception{
		con=new SqlConnect("123.57.70.62","1433","Rmi","sa","z53121171");	
	}	
	
	public  int addUser(UserModel user) throws Exception{
		String sql=String.format("insert into dt_user(name,password) values('%s','%s')",user.getName(),user.getPassword());
		if(con.executeNonQuery(sql)>=1){
			return 1;	
		}
		return 0;	
	} 
	public   boolean isExist(UserModel user) throws Exception{
		ResultSet rs=null;
		String sql=String.format("select count(*) sum  from dt_user where name='%s'",user.getName());
		if((rs=con.executeQuery(sql))!=null){
			rs.next();
			if(rs.getInt("sum")!=0){
				return true;
			}			
		}
		return false;
	}
	public  UserModel login(UserModel user) throws Exception{
		return null;
	}
	public boolean isCash(ConferenceModel model) throws Exception{return false;}
	public Vector<Vector<String>> getConferenceListById(int id) throws Exception{return null;}
	public boolean addConference(ConferenceModel model) throws Exception{return false;}
	public boolean deleteConference(int id) throws Exception{return false;}
}