package zhtest.dal;

import java.rmi.*;
import java.sql.*;
import java.util.*;
import zhtest.model.*;
import zhtest.connect.database.*;
import zhtest.rmiinterface.ConferenceInterface;


public class UserLogin extends java.rmi.server.UnicastRemoteObject implements ConferenceInterface{
	
	private SqlConnect con;

    public UserLogin() throws Exception{
	    con=new SqlConnect("123.57.70.62","1433","Rmi","sa","z53121171");	
	}	
	
	public  UserModel login(UserModel user) throws Exception{
		
		UserModel retUser=new UserModel();
		retUser.setName(user.getName());
		
		ResultSet rs=null;
		try{
			String sql=String.format("select id from dt_user where name='%s' and password='%s'",user.getName(),user.getPassword());
			//System.out.println(sql);
			if((rs=con.executeQuery(sql))!=null){
				rs.next();
				if(rs.getInt("id")!=0){
					retUser.setId(rs.getInt("id"));
					return retUser;
				}			
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return null;
	}
	public  int addUser(UserModel user) throws Exception{
		return 0;	
	} 
	public   boolean isExist(UserModel user) throws Exception{
		return false;
	}
    public Vector<Vector<String>> getConferenceListById(int id) throws Exception{return null;}
	public boolean isCash(ConferenceModel model) throws Exception{return false;}
	public boolean addConference(ConferenceModel model) throws Exception{return false;}
	public boolean deleteConference(int id) throws Exception{return false;}
}