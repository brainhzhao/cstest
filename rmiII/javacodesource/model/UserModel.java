package zhtest.model;
import java.io.*;

public class UserModel implements Serializable{
	private String name;
	private String password;
	private int id;
	public UserModel(){
		this("","");
	}
	public UserModel(String name,String password){
		this.name=name;
		this.password=password;		
	}
	
	public void setName(String name){
		this.name=name;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public String getName(){
		return this.name;
	}
	public String getPassword(){
		return this.password;
	}
	
	public void setId(int id){
		this.id=id;
	}
	public int getId(){
		return this.id;
	}
	
}