package zhtest.model;
import java.io.*;

public class ConferenceModel implements Serializable{
	private String title;
	private String startTime;
	private String endTime;
	private String peopleIds;
	private int userId;
	public ConferenceModel(){
		this("","","","");
	}
	public ConferenceModel(String title,String startTime,String endTime,String peopleIds){
		this.title=title;
		this.startTime=startTime;		
		this.endTime=endTime;
		this.peopleIds=peopleIds;
	}
	public void setUserId(int id){
		this.userId=id;
	}
	
	public void setTitle(String title){
		this.title=title;
	}
	public void setStartTime(String startTime){
		this.startTime=startTime;
	}
	public void setEndTime(String endTime){
		this.endTime=endTime;
	}
	public void setPeopleIds(String peopleIds){
		this.peopleIds=peopleIds;
	}
	public String getTitle(){
		return this.title;
	}
	public String getStartTime(){
		return this.startTime;
	}
	public String getEndTime(){
		return this.endTime;
	}
	public String getPeopleIds(){
		return this.peopleIds;
	}
	public int getUserId(){
		return this.userId;
	}
	
}