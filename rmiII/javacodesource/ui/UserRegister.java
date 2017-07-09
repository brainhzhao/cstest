package zhtest.ui;


import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import zhtest.model.*;
import java.rmi.*;
import java.rmi.registry.*;
import java.net.*;
//import zhtest.dal.*;
import zhtest.rmiinterface.*;

public class UserRegister extends JFrame{
	
	private JPanel contentPanel;
	private JButton submit;
	private Container container;
	private JTextField userName;
	private JPasswordField password;
	private JPasswordField secPassword;
	private String passwordString;
	private String secPasswordString;
	private JDialog dialog;
	private JLabel alertString;
	//private UserRegisterDb uRegister;
	private JButton dialogButton;
	private ConferenceInterface userRegister;
	public UserRegister(){		
		super("新用户注册");
		container=this.getContentPane();
		this.setBounds(570,100,400,370);
		this.add("South",new JPanel(new GridLayout(1,3)).add(new JLabel("                                           欢迎使用会议室预约系统")));
               		
	    contentPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		userName=new JTextField(20);
		password=new JPasswordField(20);
		secPassword=new JPasswordField(20);
		password.addActionListener(new EventHandler());
		secPassword.addActionListener(new EventHandler());
		contentPanel.add(new JLabel("用户名"));
		contentPanel.add(new JLabel("                                                "));
		contentPanel.add(userName);
		contentPanel.add(new JLabel("*请输入用户名                       "));
		contentPanel.add(new JLabel("密码")); 
		contentPanel.add(new JLabel("                                               "));
		contentPanel.add(password);
		contentPanel.add(new JLabel("*请输入密码             "));
		contentPanel.add(new JLabel("确认密码"));
		contentPanel.add(new JLabel("                                                    "));
		contentPanel.add(secPassword);
		contentPanel.add(new JLabel("*请再次输入密码         "));
		submit=new JButton("注册");
		submit.setBackground(Color.RED);
		submit.addActionListener(new EventHandler());
		contentPanel.add(submit);
	    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(contentPanel);
		this.setVisible(true);
		this.setResizable(false);
		alertString=new JLabel("");
		dialog=new JDialog(UserRegister.this,"提示",true);
		dialog.setBounds(590,130,265,220);
		dialog.setResizable(false);
		dialog.add(alertString);
		dialogButton=new JButton("确定");
		dialogButton.addActionListener(new EventHandler());
		dialog.add("South",new JPanel(new FlowLayout(FlowLayout.RIGHT)).add(dialogButton));
		dialog.setDefaultCloseOperation(HIDE_ON_CLOSE  );
		
		try{
			userRegister=(ConferenceInterface)Naming.lookup("rmi://localhost:9999/uRegister");	
			
			//System.out.print(userRegister.print());
		}
		catch(Exception e){
			
			alert(String.format("异常消息:\n%s",e.getMessage()));
		} 
		
	}
	public void alert(String content){
		alertString.setText(content);
	    dialog.setVisible(true);			
	}
	//事件处理内部类
	private class EventHandler implements ActionListener{	
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==dialogButton){
				dialog.setVisible(false);
				return;
				
			}
			if(e.getSource()==submit){				
				///container.setVisible(false);	
				if(userName.getText().equals("")){
					alertString.setText("               请输入用户名!");
				    dialog.setVisible(true);	
					return;
				}
				if(password.getText().equals("")||secPassword.getText().equals("")){
					alertString.setText("               请输入密码!");
					dialog.setVisible(true);
					return;
				}
				if(!password.getText().equals(secPassword.getText())){
				    alertString.setText("               两次密码不一致!");
				    dialog.setVisible(true);
                    return;					
				}
				try{
					UserModel user=new UserModel(userName.getText(),password.getText());
					//userRegister=new UserRegisterDb();
					if(userRegister.isExist(user)){
						alertString.setText("               用户名已被注册!");
						dialog.setVisible(true);
						
						return;	
					}
					if(userRegister.addUser(user)==1){
						alertString.setText("               注册用户成功!");
				        dialog.setVisible(true);
						dialog.setVisible(false);
						UserRegister.this.setVisible(false);
                        new UserLogin();
						
					}
				}
                catch(Exception ex){
					//alert()
					System.out.print(ex.getMessage());
				}			
			}			
		}		
	}	
}

