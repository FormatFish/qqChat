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
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractButton;
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

import com.mq.client.Manage.ManageAllView;
import com.mq.client.Manage.ManageClientThread;
import com.mq.client.common.DateTime;
import com.mq.client.common.Message;
import com.mq.client.common.MessageType;
import com.mq.client.common.User;
import com.mq.client.thread.ClientSocketThread;
import com.mq.client.util.FillWidth;
import com.mq.client.util.IClientView;
import com.mq.client.util.JImagePane;

 /**
 * 新用户注册类。
 */
public class RegisterView extends JFrame implements IClientView ,ActionListener{
	
	public static void main(String[] args)
	{
		RegisterView abc = new RegisterView();
	}
	
	
	private JLabel lblNickName = new JLabel("用户昵称:");
	private JLabel lblEmail = new JLabel("E-mail:");
	private JLabel lblPassword = new JLabel("登录密码:");
	private JLabel lblRePass = new JLabel("重复输入:");
	private JLabel lblSex = 	new JLabel("性    别:");
	private JLabel lblAge = new JLabel("年龄:");
	private JLabel lblRealName = new JLabel("姓名:");
	private JLabel lblSignature = new JLabel("个性签名:");
	
	private JTextField txtNickName = new JTextField();
	private JTextField txtEmail= new JTextField();
	private JPasswordField pfPassword = new JPasswordField();
	private JPasswordField pfRePass = new JPasswordField();
	private JComboBox  boxSex = new JComboBox();
	private JTextField txtAge = new JTextField();
	private JTextField txtRealName = new JTextField();
	private JTextArea areaSignature = new JTextArea();
	
	public  JImagePane lblPhoto = new JImagePane();
	
	private JButton btnChange = new JButton("更改头像");
	
	private JButton btnLogin = new JButton("登陆");
	private JButton btnOk = new JButton("注册");
	private JButton btnCancle = new JButton("取消");
	
	private ChooseProtraitView chooseProtrait = null;
	public String TxPath = "Tx/01.jpg";
	
	
	
	public RegisterView() {
		System.out.println("registerView2"+"    add");
		ManageAllView.addClientView("registerView2", this);
		setTitle("QQ新用户注册");
		setSize(330,343);
		setResizable(false);
		Toolkit tk=Toolkit.getDefaultToolkit();
		setLocation((tk.getScreenSize().width-getSize().width)/2,(tk.getScreenSize().height-getSize().height)/2);
		
		init();
		
		btnCancle.addActionListener(this);
		btnChange.addActionListener(this);
		btnOk.addActionListener(this);
		btnLogin.addActionListener(this);
		
		this.getRootPane().setDefaultButton(this.btnOk);
		setVisible(true);
	}
	
	/**
	 * 初始化面板
	 */
	private void init(){
		setLayout(null);
		lblNickName.setPreferredSize(new Dimension(60,20));
		lblEmail.setPreferredSize(new Dimension(60,20));
		lblPassword.setPreferredSize(new Dimension(60,20));
		lblRePass.setPreferredSize(new Dimension(60,20));
		lblSex.setPreferredSize(new Dimension(60,20));
		lblAge.setPreferredSize(new Dimension(30,20));
		lblRealName.setPreferredSize(new Dimension(30,20));
		lblSignature.setPreferredSize(new Dimension(60,20));
		
		txtNickName.setPreferredSize(new Dimension(120,20));
		txtEmail.setPreferredSize(new Dimension(120,20));
		pfPassword.setPreferredSize(new Dimension(120,20));
		pfRePass.setPreferredSize(new Dimension(120,20));
		boxSex.setPreferredSize(new Dimension(40,20));
		txtAge.setPreferredSize(new Dimension(40,20));
		txtRealName.setPreferredSize(new Dimension(60,20));
		JScrollPane sp = new JScrollPane(areaSignature);
		sp.setPreferredSize(new Dimension(220,60));
		
		lblPhoto.setOpaque(true);
		lblPhoto.setBackground(Color.WHITE);
		lblPhoto.setPreferredSize(new Dimension(50,50));
		lblPhoto.setBorder(new LineBorder(Color.DARK_GRAY));
		lblPhoto.setBackgroundImage(new ImageIcon("Tx/01.jpg").getImage());
		this.TxPath = "Tx/01.jpg";
		lblPhoto.setImageDisplayMode(JImagePane.SCALED);
		btnChange.setPreferredSize(new Dimension(60,20));
		btnChange.setMargin(new Insets(0,0,0,0));
		
		boxSex.addItem("男");
		boxSex.addItem("女");
		
		JPanel paneRequire = new JPanel();
		paneRequire.setBorder(new TitledBorder(new LineBorder(Color.GRAY),"必填选项"));
		paneRequire.setSize(210,135);
		paneRequire.setLocation(10, 10);
		paneRequire.setLayout(new FlowLayout(FlowLayout.LEFT,5,6));
		paneRequire.add(lblNickName);
		paneRequire.add(txtNickName);
		paneRequire.add(lblEmail);
		paneRequire.add(txtEmail);
		paneRequire.add(lblPassword);
		paneRequire.add(pfPassword);
		paneRequire.add(lblRePass);
		paneRequire.add(pfRePass);
		
		JPanel paneUnRequire = new JPanel();
		paneUnRequire.setBorder(new TitledBorder(new LineBorder(Color.GRAY),"选填选项"));
		paneUnRequire.setSize(305,125);
		paneUnRequire.setLocation(10, 150);
		paneUnRequire.setLayout(new FlowLayout(FlowLayout.LEFT,5,6));
		paneUnRequire.add(lblSex);
		paneUnRequire.add(boxSex);
		paneUnRequire.add(lblAge);
		paneUnRequire.add(txtAge);
		paneUnRequire.add(lblRealName);
		paneUnRequire.add(txtRealName);
		paneUnRequire.add(lblSignature);
		paneUnRequire.add(sp);
		
		JPanel panePhoto = new JPanel();
		panePhoto.setBorder(new TitledBorder(new LineBorder(Color.GRAY),"头像"));
		panePhoto.setSize(85,135);
		panePhoto.setLocation(230, 10);
		panePhoto.setLayout(new FlowLayout(FlowLayout.CENTER,5,8));
		panePhoto.add(new FillWidth(50,4));
		panePhoto.add(lblPhoto);
		panePhoto.add(btnChange);
		
		JPanel paneBottom = new JPanel();
		paneBottom.setSize(305,30);
		paneBottom.setLocation(10, 275);
		paneBottom.setLayout(new FlowLayout(FlowLayout.LEFT,2,5));
		paneBottom.add(btnLogin);
		paneBottom.add(new FillWidth(100,20));
		paneBottom.add(btnOk);
		paneBottom.add(new FillWidth(8,20));
		paneBottom.add(btnCancle);
		
	
		JPanel paneSet = new JPanel();
		paneSet.setSize(305,60);
		paneSet.setLocation(10,313);
		paneSet.add(new FillWidth(30,20));
		add(paneRequire);
		add(paneUnRequire);
		add(panePhoto);
		add(paneBottom);
	
	}
	
	/**
	 * 取消按钮、更改头像按钮、注册按钮、设置按钮事件。
	 */
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==btnCancle){
			dispose();
			new LoginView();
			
		}
		if(e.getSource()==btnChange){
			if(chooseProtrait==null)
				
				chooseProtrait = new ChooseProtraitView(this , true);
			else
				
				chooseProtrait.setVisible(true);
			
		}
		
		if(e.getSource()==btnOk){
			
				try {
					//JOptionPane.showMessageDialog(null, this.lblPhoto.getBackgroundImage().toString());
					String pass = new String(pfPassword.getPassword());
					String repass = new String(pfRePass.getPassword());
					
					String name  = new String(txtNickName.getText());
					String pfPassword1  = new String(pfPassword.getText());
					String pfRePass2  = new String(pfRePass.getText());
				
					
					String textFile =name;
					String email ="^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
					Pattern p = Pattern.compile(email);
					Matcher match = p.matcher(this.txtEmail.getText());

					
					if(textFile ==null || textFile.equals(""))
					{
						JOptionPane.showMessageDialog(null,"昵称输入为空！！请重新注册");
						return;
					}
					else if(pfPassword1 ==null || pfPassword1.equals(""))
					{
						JOptionPane.showMessageDialog(null,"密码输入为空！！请重新注册");
						return;
					}
					else if(!match.find())
					{
						JOptionPane.showMessageDialog(this, "邮箱格式不正确");
						return;
						
					}
					else if(this.txtAge.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "请填入年龄");
						return;
					}
					else if(this.txtRealName.getText().equals(""))
					{
						JOptionPane.showMessageDialog(this, "请填入真实姓名");
						return;
					}
					else if(pass.equals(repass)){
						//btnOk.setEnabled(false);						
						//此处打包注册信息
						Message m = new Message();
						User user = new User();
						
					 user.setUserName(txtNickName.getText().trim());
					 user.setPasswd(pfPassword.getText().trim());
					 try
					 {
						 user.setAge(Integer.parseInt((txtAge.getText().trim())));
					 }
					 catch(Exception ex)
					 {
						 JOptionPane.showMessageDialog(this, "年龄必须为数值");
						 return;
					 }
					 
					 user.setSignature(areaSignature.getText().trim());
					 user.setSex(boxSex.getSelectedItem().toString());
					 user.setName(txtRealName.getText().trim());
					 user.setEmail(this.txtEmail.getText().trim());
					 user.setTxpath(this.TxPath);
					 
					 
					m.setSender(user.getUserId());
					m.setSendTime((DateTime.getTimer()));
					m.setGetter(user.getUserId());
				    m.setContext(user);
					m.setMessageType(MessageType.register);
						
					//发送消息
					ClientSocketThread clientthread = new ClientSocketThread();
                     ManageClientThread.addClientSocketThread("socketThread",
							clientthread);
                    //发送注册消息
					try {
						ManageClientThread.getClientSocketThread("socketThread")
								.send(m);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//启动一次接收注册判断消息
						ManageClientThread.getClientSocketThread("socketThread").accept();
						
					
					}
					
					else
						JOptionPane.showMessageDialog(null, "2次密码输入的不一致,请重新输入!");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "错误:"+e1.getMessage());
				}
			
		}
		if(e.getSource()==btnLogin){
			dispose();
			new LoginView();
			}
		}

	@Override
	public void procedureMsg(Message msg) {
		// TODO Auto-generated method stub
		if(msg.getMessageType() == MessageType.register_succ){
			String tip = "恭喜你注册成功！ 你的QQ号码是" + msg.getContext();
			JOptionPane.showMessageDialog(null, tip);
			
			//返回登录
			RegisterView.this.dispose();
			
			new LoginView();
		}else if(msg.getMessageType() == MessageType.register_fail){
			String tip = "很遗憾，注册失败";
			JOptionPane.showMessageDialog(null, tip);
		}
		//关闭线程流，移除线程管理
		try {
			ManageClientThread.getClientSocketThread("socketThread")
					.closeSocket();
			ManageClientThread.removeClientSocketThread("socketThread");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	}

	
