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

 /**
 * ���û�ע���ࡣ
 */
public class ModifyInfoView extends JFrame implements IClientView ,ActionListener{
	
	public static void main(String[] args)
	{
		ModifyInfoView abc = new ModifyInfoView(new User());
	}
	
	
	private JLabel lblNickName = new JLabel("�û��ǳ�:");
	private JLabel lblEmail = new JLabel("E-mail:");
	private JLabel lblPassword = new JLabel("��¼����:");
	private JLabel lblRePass = new JLabel("�ظ�����:");
	private JLabel lblSex = 	new JLabel("��    ��:");
	private JLabel lblAge = new JLabel("����:");
	private JLabel lblRealName = new JLabel("����:");
	
	private JTextField txtNickName = new JTextField();
	private JTextField txtEmail= new JTextField();
	private JPasswordField pfPassword = new JPasswordField();
	private JPasswordField pfRePass = new JPasswordField();
	private JComboBox  boxSex = new JComboBox();
	private JTextField txtAge = new JTextField();
	private JTextField txtRealName = new JTextField();
	
	
	private JButton btnLogin = new JButton("ȷ���ύ");
	//private JButton btnOk = new JButton("ע��");
	private JButton btnCancle = new JButton("ȡ��");
	
	private User me = null;
	
	
	
	public ModifyInfoView(User me) {
		this.me = me;
		System.out.println("ModifyInfoView"+"    add");
		ManageAllView.addClientView("ModifyInfoView", this);
		setTitle("�ҵ���Ϣ�޸�");
		setSize(330,343);
		setResizable(false);
		Toolkit tk=Toolkit.getDefaultToolkit();
		setLocation((tk.getScreenSize().width-getSize().width)/2,(tk.getScreenSize().height-getSize().height)/2);
		
		init();
		
		btnCancle.addActionListener(this);
		btnLogin.addActionListener(this);
		
		
		this.getRootPane().setDefaultButton(this.btnLogin);
		setVisible(true);
	}
	
	/**
	 * ��ʼ�����
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
		
		//lblSignature.setPreferredSize(new Dimension(60,20));
		
		txtNickName.setPreferredSize(new Dimension(120,20));
		txtNickName.setText(me.getUserName());
		txtEmail.setPreferredSize(new Dimension(120,20));
		txtEmail.setText(me.getEmail());
		pfPassword.setPreferredSize(new Dimension(120,20));
		pfPassword.setText(me.getPasswd());
		pfRePass.setPreferredSize(new Dimension(120,20));
		pfRePass.setText(me.getPasswd());
		boxSex.setPreferredSize(new Dimension(40,20));
		
		boxSex.setSelectedItem(me.getSex());
		txtAge.setPreferredSize(new Dimension(40,20));
		txtAge.setText(new Integer(me.getAge()).toString());
		txtRealName.setPreferredSize(new Dimension(60,20));
		txtRealName.setText(me.getName());
		
		boxSex.addItem("��");
		boxSex.addItem("Ů");
		
		JPanel paneRequire = new JPanel();
		paneRequire.setBorder(new TitledBorder(new LineBorder(Color.GRAY),"����ѡ��"));
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
		paneUnRequire.setBorder(new TitledBorder(new LineBorder(Color.GRAY),"ѡ��ѡ��"));
		paneUnRequire.setSize(305,80);
		paneUnRequire.setLocation(10, 150);
		paneUnRequire.setLayout(new FlowLayout(FlowLayout.LEFT,5,6));
		paneUnRequire.add(lblSex);
		paneUnRequire.add(boxSex);
		paneUnRequire.add(lblAge);
		paneUnRequire.add(txtAge);
		paneUnRequire.add(lblRealName);
		paneUnRequire.add(txtRealName);
		
		JPanel paneBottom = new JPanel();
		paneBottom.setSize(305,30);
		paneBottom.setLocation(10, 255);
		paneBottom.setLayout(new FlowLayout(FlowLayout.LEFT,2,5));
		paneBottom.add(btnLogin);
		paneBottom.add(new FillWidth(100,20));
		paneBottom.add(new FillWidth(8,20));
		paneBottom.add(btnCancle);
		
	
		JPanel paneSet = new JPanel();
		paneSet.setSize(305,60);
		paneSet.setLocation(10,313);
		paneSet.add(new FillWidth(30,20));
		add(paneRequire);
		add(paneUnRequire);
		add(paneBottom);
	
	}
	
	/**
	 * ȡ����ť������ͷ��ť��ע�ᰴť�����ð�ť�¼���
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnCancle) {
			dispose();

		}

		if (e.getSource() == btnLogin) {

			try {
				String pass = new String(pfPassword.getPassword());
				String repass = new String(pfRePass.getPassword());

				String name = new String(txtNickName.getText());
				String pfPassword1 = new String(pfPassword.getText());
				String pfRePass2 = new String(pfRePass.getText());

				String textFile = name;
				String email = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
				Pattern p = Pattern.compile(email);
				Matcher match = p.matcher(this.txtEmail.getText());

				if (textFile == null || textFile.equals("")) {

					JOptionPane.showMessageDialog(null, "�ǳ�����Ϊ�գ���������ע��");
					return;
				} else if (pfPassword1 == null || pfPassword1.equals("")) {
					JOptionPane.showMessageDialog(null, "��������Ϊ�գ���������ע��");
					return;
				} else if (!match.find()) {
					JOptionPane.showMessageDialog(this, "�����ʽ����ȷ");
					return;

				} else if (this.txtRealName.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "��������ʵ����");
					return;
				} else if (pass.equals(repass)) {

					// �˴����ע����Ϣ
					Message m = new Message();

					me.setUserName(txtNickName.getText().trim());
					me.setPasswd(pfPassword.getText().trim());
					try
					{
						me.setAge(Integer.parseInt((txtAge.getText().trim())));
					}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(this, "�������Ϊ��ֵ");
					}
					
					// user.setSignature(areaSignature.getText().trim());
					me.setSex(boxSex.getSelectedItem().toString());
					me.setName(txtRealName.getText().trim());
					me.setEmail(this.txtEmail.getText().trim());

					m.setSender(me.getUserId());
					m.setSendTime((DateTime.getTimer()));
					m.setGetter(me.getUserId());
					m.setContext(me);
					m.setMessageType(MessageType.other);

					// ������Ϣ
					ClientSocketThread clientthread = new ClientSocketThread();
					ManageClientThread.addClientSocketThread("socketThread",
							clientthread);
					// ����ע����Ϣ
					try {
						ManageClientThread
								.getClientSocketThread("socketThread").send(m);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					// ����һ�ν���ע���ж���Ϣ
					ManageClientThread.getClientSocketThread("socketThread")
							.accept();

					this.dispose();

				}

				else
					JOptionPane.showMessageDialog(null, "2����������Ĳ�һ��,����������!");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "����:" + e1.getMessage());
			}

		}
	}

	@Override
	public void procedureMsg(Message msg) {
		// TODO Auto-generated method stub
		if(msg.getMessageType() == MessageType.register_succ){
			String tip = "��ϲ��ע��ɹ��� ���QQ������" + msg.getContext();
			JOptionPane.showMessageDialog(null, tip);
			
			//���ص�¼
			ModifyInfoView.this.dispose();
			
			new LoginView();
		}else if(msg.getMessageType() == MessageType.register_fail){
			String tip = "���ź���ע��ʧ��";
			JOptionPane.showMessageDialog(null, tip);
		}
		//�ر��߳������Ƴ��̹߳���
		try {
			ManageClientThread.getClientSocketThread("socketThread")
					.closeSocket();
			ManageClientThread.removeClientSocketThread("socketThread");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	}
	
	
	/**
	 * ѡ��ͷ�񴰿�
	 */
	
