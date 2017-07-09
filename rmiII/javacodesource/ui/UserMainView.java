package zhtest.ui;


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

public class UserMainView extends JFrame{
	
	private JPanel contentPanel;
	private JButton submit;
	private JButton see;
	private JButton add;
	private Container container;
	private JTextField conId;
	private JDialog dialog;
	private JLabel alertString;
	private UserRegisterDb uRegister;
	private JButton dialogButton;
	private UserModel user;
	private ConferenceRecord cRecord;
	private AddConference addConference;
	private ConferenceInterface deleteCon;
	public UserMainView(UserModel user){		
		super("�û���ҳ");
		this.user=user;
		container=this.getContentPane();
		this.setBounds(570,100,400,370);
		this.add("South",new JPanel(new GridLayout(1,3)).add(new JLabel("                                           ��ӭʹ�û�����ԤԼϵͳ")));
               		
	    contentPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		conId=new JTextField(20);
		contentPanel.add(new JLabel("��������Ҫɾ���Ļ����"));
		contentPanel.add(new JLabel("                                                "));
		contentPanel.add(conId);
		contentPanel.add(new JLabel("                                                "));
		
		
		
		submit=new JButton("ɾ��");
		submit.setBackground(Color.RED);
		submit.addActionListener(new EventHandler());
		
		see=new JButton("�ο������¼");
		see.setBackground(Color.RED);
		see.addActionListener(new EventHandler());
		
		add=new JButton("��ӻ���");
		add.setBackground(Color.RED);
		add.addActionListener(new EventHandler());
		
		contentPanel.add(submit);
		contentPanel.add(add);
		contentPanel.add(see);
	    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(contentPanel);
		this.setVisible(true);
		this.setResizable(false);
		
		alertString=new JLabel("");
		dialog=new JDialog(UserMainView.this,"��ʾ",true);
		dialog.setBounds(590,130,265,220);
		dialog.setResizable(false);
		dialog.add(alertString);
		dialogButton=new JButton("ȷ��");
		dialogButton.addActionListener(new EventHandler());
		dialog.add("South",new JPanel(new FlowLayout(FlowLayout.RIGHT)).add(dialogButton));
		dialog.setDefaultCloseOperation(HIDE_ON_CLOSE  );
		
		try{
			deleteCon=(ConferenceInterface)Naming.lookup("rmi://localhost:9999/addConference");	
			//rowData=getData.getConferenceListById(userid);
			//System.out.print(userLogin.print());
		}
		catch(Exception e){
			
			//alert(String.format("�쳣��Ϣ:\n%s",e.getMessage()));
		} 
		
	}
	//�¼������ڲ���
	private class EventHandler implements ActionListener{	
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==dialogButton){
				dialog.setVisible(false);
				return;
				
			}
			if(e.getSource()==submit){
                try{
					
					
               				
						///container.setVisible(false);	
						if(conId.getText().equals("")){
							alertString.setText("               ������Ҫɾ���Ļ������!");
							dialog.setVisible(true);	
							return;		
						}
						else{
							if(deleteCon.deleteConference(Integer.parseInt(conId.getText()))){
								alertString.setText("               ɾ���ɹ���");
								dialog.setVisible(true);	
								return;	
							} 
						}
				}
				catch(Exception ex){
					
				}
			}	
            if(e.getSource()==see){
				cRecord=new ConferenceRecord(user.getId());
			}	
            if(e.getSource()==add){
				addConference=new AddConference(user);
			}			
		}		
	}		
}

