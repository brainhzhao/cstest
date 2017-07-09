package zhtest.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.rmi.*;
import java.rmi.registry.*;
import java.net.*;
//import zhtest.dal.*;
import zhtest.rmiinterface.*;

public class ConferenceRecord extends JFrame{
	private ConferenceInterface getData;

	public ConferenceRecord(int userid){
		super("查看已添加的会议记录");
		this.setBounds(570,100,700,500);
		Vector<Vector<String>> rowData=null;
		try{
			getData=(ConferenceInterface)Naming.lookup("rmi://localhost:9999/addConference");	
			rowData=getData.getConferenceListById(userid);
			//System.out.print(userLogin.print());
		}
		catch(Exception e){
			
			//alert(String.format("异常消息:\n%s",e.getMessage()));
		} 
		
		
		
        Vector<String> columnNames=new Vector<String>();
		columnNames.add(0,"id");
		columnNames.add(1,"title");
		columnNames.add(2,"person");
		columnNames.add(3,"start_time");
		columnNames.add(4,"end_time");
		columnNames.add(5,"user_id");
		final JTable table=new JTable(rowData,columnNames);
		table.setBackground(Color.YELLOW);
		//table.setFillsViewportHeight(true);
		JScrollPane scrollPane=new JScrollPane(table);		
		this.add(scrollPane);
		this.setVisible(true);	
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {  
              public void windowClosing(WindowEvent e) {  
                    ConferenceRecord.this.setVisible(false);
                               //加入动作   
     		   }  
  
         });
	}
}