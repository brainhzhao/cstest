package zhtest.server;


import java.rmi.*;
import java.rmi.registry.*;
import java.net.*;
import zhtest.dal.*;


public class ConferenceServer{
	public static void main(String[] args){
		try{
			UserLogin uLogin=new UserLogin();
			UserRegisterDb uRegister=new UserRegisterDb();
			Conference addConference=new Conference();
			LocateRegistry.createRegistry(9999);
			Naming.bind("rmi://localhost:9999/uLogin",uLogin);
			System.out.println(">>Info:远程uLogin对象绑定成功");
            Naming.bind("rmi://localhost:9999/uRegister",uRegister);
			System.out.println(">>Info:远程uRegister对象绑定成功");	
            Naming.bind("rmi://localhost:9999/addConference",addConference);
			System.out.println(">>Info:远程addConference对象绑定成功");			
		}
		catch(Exception e){		
			System.out.println("异常消息:"+e.getMessage());
		}		
	}
	
	
}