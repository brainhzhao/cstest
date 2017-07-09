package zhtest.ui;

import java.text.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import zhtest.model.*;
import zhtest.dal.*;
import java.rmi.*;
import java.rmi.registry.*;
import java.net.*;
//import zhtest.dal.*;
import zhtest.rmiinterface.*;

public class AddConference extends JFrame{
	
	private JPanel contentPanel;
	private JButton submit;   
	private Container container;
	private JTextArea conferenceTitle;
	private JDialog dialog;
	private JLabel alertString;
	private UserRegisterDb uRegister;
	private JButton dialogButton;
	private JTextField joinPerson;
	private JSpinner.DateEditor startDatetime;
	private JSpinner.DateEditor endDatetime;
	private SpinnerDateModel startDateModel;
	private SpinnerDateModel endDateModel;
	private JSpinner startJspinner;
	private JSpinner endJspinner;
	private SimpleDateFormat sdf;
	private Date start;
	private Date end;
	private ConferenceInterface addConference;
	private UserModel user;
	public AddConference(UserModel user){		
		super("添加新会议信息");
		container=this.getContentPane();
		this.setBounds(570,100,700,500);
		this.add("South",new JPanel(new GridLayout(1,3)).add(new JLabel("                                                                       欢迎使用会议室预约系统")));
               		
	    contentPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		conferenceTitle=new JTextArea(10,40);
		
		joinPerson=new JTextField(30);
		joinPerson.setText("");
		
		startDateModel=new SpinnerDateModel();
		endDateModel=new SpinnerDateModel();
		startJspinner=new JSpinner(startDateModel);
		endJspinner=new JSpinner(endDateModel);
		
		startJspinner.setValue(new Date());
		endJspinner.setValue(new Date());
		
		startDatetime= new JSpinner.DateEditor(startJspinner, "yyyy-MM-dd HH:mm");
		endDatetime= new JSpinner.DateEditor(endJspinner, "yyyy-MM-dd HH:mm");
	    sdf=new SimpleDateFormat("yyyy MM dd HH:mm");
		
		
		contentPanel.add(new JLabel("描述本次会议的标签"));
		contentPanel.add(new JLabel("                                                "));
		contentPanel.add(conferenceTitle);
		contentPanel.add(new JLabel("                                                               "));
		contentPanel.add(new JLabel("会议开始时间："));
		contentPanel.add(new JLabel("                 "));
		contentPanel.add(startJspinner);
		contentPanel.add(new JLabel("                                                                                                                        "));
		contentPanel.add(new JLabel("会议结束时间："));
		contentPanel.add(new JLabel("                 "));
		contentPanel.add(endJspinner);
		contentPanel.add(new JLabel("                                                                                                                       "));
		contentPanel.add(new JLabel("参加会议人员："));
		contentPanel.add(new JLabel("                 "));
		contentPanel.add(joinPerson);
		contentPanel.add(new JLabel("                                              "));
		submit=new JButton("提交");
		submit.setBackground(Color.RED);
		submit.addActionListener(new EventHandler());
		contentPanel.add(submit);
	    this.addWindowListener(new WindowAdapter() {  
            public void windowClosing(WindowEvent e) {  
                AddConference.this.setVisible(false);
                               //加入动作   
			}  
        });
		this.add(contentPanel);
		this.setVisible(true);
		this.setResizable(false);
		
		
		alertString=new JLabel("");
		dialog=new JDialog(AddConference.this,"提示",true);
		dialog.setBounds(590,130,265,220);
		dialog.setResizable(false);
		dialog.add(alertString);
		dialogButton=new JButton("确定");
		dialogButton.addActionListener(new EventHandler());
		dialog.add("South",new JPanel(new FlowLayout(FlowLayout.RIGHT)).add(dialogButton));
		dialog.setDefaultCloseOperation(HIDE_ON_CLOSE );
		
		this.user=user;
		try{
			addConference=(ConferenceInterface)Naming.lookup("rmi://localhost:9999/addConference");	
			
			//System.out.print(userLogin.print());
		}
		catch(Exception e){
			
			alert(String.format("异常消息:\n%s",e.getMessage()));
		} 
	}
	public static void main(String[] args){
		
		new AddConference(new UserModel("赵恒","123"));
	}
	public void alert(String content){
		alertString.setText(content);
	    dialog.setVisible(true);			
	}
	//事件处理内部类
	private class EventHandler implements ActionListener{	
		public void actionPerformed(ActionEvent e){
		     if(e.getSource()==submit){
				 if(conferenceTitle.getText().equals("")){
					 alert("请填写会议标题");
					 return;
				 }
				 try{
					 start=sdf.parse(sdf.format(startJspinner.getValue()));
					 end=sdf.parse(sdf.format(endJspinner.getValue()));
					 if(start.getTime()>end.getTime()){
						 alert("起始时间大于终止时间");
						 return;
					 }
				 }
				 catch(Exception ex){
					 return;
				 }
				 if(joinPerson.getText().equals("")){
					 alert("请输入至少一个参会人员");
					 return;
				 }
				 ConferenceModel model=new ConferenceModel(conferenceTitle.getText(),sdf.format(startJspinner.getValue()),sdf.format(endJspinner.getValue()),joinPerson.getText());
				 model.setUserId(user.getId());
				 try{
					 if(addConference.isCash(model)){
						 alert("时间上发生冲突");
						 return;
					 }
					 else{
						 if(addConference.addConference(model)){
							 alert("添加成功!");
							 //return;
						 }
					 }
				 }
				 catch(Exception ex){
					 System.out.print(ex.getMessage());
				 }
			 }
			 
			 if(e.getSource()==dialogButton){
				 dialog.setVisible(false);
			 }
		}		
	}	
}

