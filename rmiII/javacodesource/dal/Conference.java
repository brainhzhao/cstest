package zhtest.dal;

import java.awt.*;
import javax.swing.*;
import java.rmi.*;
import java.sql.*;
import java.util.*;
import zhtest.model.*;
import zhtest.connect.database.*;
import zhtest.rmiinterface.ConferenceInterface;


public class Conference extends java.rmi.server.UnicastRemoteObject implements ConferenceInterface{
	
	private SqlConnect con;

    public Conference() throws Exception{
		con=new SqlConnect("123.57.70.62","1433","Rmi","sa","z53121171");	
	}	
	
	public  int addUser(UserModel user) throws Exception{return 0;} 
	public  boolean isExist(UserModel user) throws Exception{return false;}
	public  UserModel login(UserModel user) throws Exception{return null;}
	public boolean isCash(ConferenceModel model) throws Exception{
	    ResultSet rs=null;
		String sql="  select COUNT(*) sum from dt_confer where (start_time<='"+model.getStartTime()+"' and end_time>='"+model.getEndTime()+"') or (start_time>='"+model.getStartTime()+"' and end_time<='"+model.getEndTime()+"') or  (start_time>='"+model.getStartTime()+"' and '"+model.getEndTime()+"'>=start_time) or (end_time>='"+model.getStartTime()+"' and end_time<='"+model.getEndTime()+"')";
		//System.out.print(sql);
		if((rs=con.executeQuery(sql))!=null){
			rs.next();
			if(rs.getInt("sum")!=0){
				return true;
			}			
		}
		return false;
	}
	public Vector<Vector<String>> getConferenceListById(int id) throws Exception{
		ResultSet rs=null;
		Vector<Vector<String>> result=new Vector<Vector<String>>();
		Vector<String> row;
		int i=0;
		String sql=String.format("select * from dt_confer where user_id=%d",id);
		//System.out.print(sql);
		if((rs=con.executeQuery(sql))!=null){
			while(rs.next()){
				row=new Vector<String>();
				row.add(0,rs.getString("id"));
				row.add(1,rs.getString("title"));
				row.add(2,rs.getString("person"));
				row.add(3,rs.getString("start_time"));
				row.add(4,rs.getString("end_time"));
				row.add(5,rs.getString("user_id"));
				result.add(i,row);
				i++;
			}
            return result; 			
		}
		return null;
	}
	public boolean addConference(ConferenceModel model) throws Exception{
		String sql=String.format("insert into dt_confer(title,start_time,end_time,person,user_id) values('%s','%s','%s','%s',%d)",model.getTitle(),model.getStartTime(),model.getEndTime(),model.getPeopleIds(),model.getUserId());
		if(con.executeNonQuery(sql)>=1){
			return true;	
		}
		
		return false;
	}
	public boolean deleteConference(int id) throws Exception{
		String sql=String.format("delete from dt_confer where id =%d",id);
		if(con.executeNonQuery(sql)>=1){
			return true;	
		}
		
		return false;
    }
}