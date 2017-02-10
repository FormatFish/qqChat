package com.mq.client.view;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.mq.client.common.Message;
import com.mq.client.common.User;
import com.mq.client.util.FillWidth;
import com.mq.client.util.JImagePane;


/**
* 新用户注册类。
*/
public class ShowView extends JFrame implements ActionListener{
	
	
	public static void main(String[] args)
	{
		ShowView abc = new ShowView(new Message());
	}
	
	
	
	private JLabel lblNUmber = new JLabel("用户帐号:");
	private JLabel lblName = new JLabel("用户昵称:");
	private JLabel lblEmail = new JLabel("电子邮箱:");
	private JLabel lblSex = new JLabel("性         别:");
	private JLabel lblAge = 	new JLabel("年         龄:");
	private JLabel lblRealName = new JLabel("姓         名:");
	private JLabel lblSignature = new JLabel("个性签名:");
	
	
	//以下是使用的标签
	
	private JLabel txtNUmber = new JLabel();
	private JLabel txtName= new JLabel();
	private JLabel txtEmail = new JLabel();
	private JLabel txtSex = new JLabel();
	private JLabel  txtAge = new JLabel();
	private JLabel txtRealName = new JLabel();
	private JLabel txtSignature = new JLabel();
	private JImagePane lblPhoto = new JImagePane();
	
	
	private JLabel lblTitle= new JLabel("QQ用户资料                                                     ");
	
	private JButton btnCancle = new JButton("取消");
	private Message msg = null;
	private User userSelf = null;
	
	public ShowView(Message msg) {
		this.msg = msg;
		this.userSelf = (User)this.msg.getContext();
		setTitle("QQ用户资料");
		setSize(330,343);
		setResizable(false);
		Toolkit tk=Toolkit.getDefaultToolkit();
		setLocation((tk.getScreenSize().width-getSize().width)/2,(tk.getScreenSize().height-getSize().height)/2);
		
		init();
		
		btnCancle.addActionListener(this);
	
		setVisible(true);
	}
	

	private void init(){
		setLayout(null);
	
		lblNUmber.setPreferredSize(new Dimension(60,20));
		lblName.setPreferredSize(new Dimension(60,20));
		lblEmail.setPreferredSize(new Dimension(60,20));
		lblSex.setPreferredSize(new Dimension(60,20));
		lblAge.setPreferredSize(new Dimension(60,20));
		lblRealName.setPreferredSize(new Dimension(60,20));
		lblSignature.setPreferredSize(new Dimension(60,20));	
		
		txtNUmber.setPreferredSize(new Dimension(120,20));
		txtNUmber.setText(this.userSelf.getUserId());
		txtName.setPreferredSize(new Dimension(120,20));
		txtName.setText(this.userSelf.getUserName());
		txtEmail.setPreferredSize(new Dimension(120,20));
		txtEmail.setText(this.userSelf.getEmail());
		txtSex.setPreferredSize(new Dimension(120,20));
		txtSex.setText(this.userSelf.getSex());
		txtAge.setPreferredSize(new Dimension(120,20));
		txtAge.setText(this.userSelf.getAge()+"");
		txtRealName.setPreferredSize(new Dimension(120,20));
		txtRealName.setText(this.userSelf.getName());
		txtSignature.setPreferredSize(new Dimension(120,20));
		txtSignature.setText(this.userSelf.getSignature());
		
		lblPhoto.setOpaque(true);
		lblPhoto.setBackground(Color.WHITE);
		lblPhoto.setPreferredSize(new Dimension(85,85));
		lblPhoto.setBorder(new LineBorder(Color.DARK_GRAY));

		JPanel paneRequire = new JPanel();
		
		lblTitle.setFont(new   java.awt.Font("Dialog",   1,   15));  

	     lblTitle.setForeground(Color.blue);
	      
	      
		paneRequire.add(lblTitle);
		
		
		paneRequire.setSize(210,335);
		paneRequire.setLocation(10, 10);
		paneRequire.setLayout(new FlowLayout(FlowLayout.LEFT,14,6));
	
		
		paneRequire.add(lblNUmber);
		paneRequire.add(txtNUmber);
		paneRequire.add(lblName);
		paneRequire.add(txtName);
		paneRequire.add(lblEmail);
		paneRequire.add(txtEmail);
		paneRequire.add(lblSex);
		paneRequire.add(txtSex);
		paneRequire.add(lblAge);
		paneRequire.add(txtAge);
		paneRequire.add(lblRealName);
		paneRequire.add(txtRealName);
		paneRequire.add(lblSignature);
		paneRequire.add(txtSignature);
		
	
		JPanel panePhoto = new JPanel();
		panePhoto.setSize(85,135);
		panePhoto.setLocation(230, 10);
		panePhoto.setLayout(new FlowLayout(FlowLayout.CENTER,5,8));
		panePhoto.add(new FillWidth(50,4));
		panePhoto.add(lblPhoto);
		lblPhoto.setBackgroundImage(new ImageIcon(this.userSelf.getTxpath()).getImage());
		lblPhoto.setImageDisplayMode(JImagePane.SCALED);
		
		
		JPanel paneBottom = new JPanel();
		paneBottom.setSize(305,30);
		paneBottom.setLocation(10, 275);
		paneBottom.setLayout(new FlowLayout(FlowLayout.LEFT,2,5));
		paneBottom.add(new FillWidth(100,20));
		paneBottom.add(new FillWidth(8,20));
		paneBottom.add(btnCancle);
	
		
		add(paneRequire);
		add(panePhoto);
		add(paneBottom);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnCancle){
			dispose();
		
		}	
			
	}
		
}

	
	

