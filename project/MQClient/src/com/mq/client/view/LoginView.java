package com.mq.client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.mq.client.Manage.ManageAllView;
import com.mq.client.Manage.ManageClientThread;
import com.mq.client.Manage.ManageUserList;
import com.mq.client.common.Message;
import com.mq.client.common.MessageType;
import com.mq.client.common.User;
import com.mq.client.thread.ClientSocketThread;
import com.mq.client.util.FillWidth;
import com.mq.client.util.IClientView;
import com.mq.client.view.LoginView;
import com.mq.client.model.*;

public class LoginView extends JFrame implements ActionListener,IClientView,WindowListener{
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoginView mqClientLogin=new LoginView();
		
	}	
	private User u=new User();
	private JLabel lblImg = new JLabel();
	private JLabel lblQQNum = new JLabel("QQ�˺�");
	private JTextField boxQQNum = new JTextField();
	private JLabel lblPassword = new JLabel("QQ����");
	private JPasswordField pfPassword = new JPasswordField();
	private JLabel lblState = new JLabel("״̬:");	
	private JComboBox boxState = new JComboBox();
	private JCheckBox boxAutoLogin = new JCheckBox("�Զ���¼");	
	private JButton btnRegister = new JButton("�����˺�");
	private JButton btnLogin = new JButton("��¼");
	
	/**
	 * ��½���塣
	 * 
	 */
	public LoginView() {
		//����ʱ���˽��������������
		System.out.println("loginView1"+"    add");
		ManageAllView.addClientView("loginView1", this);
		this.addWindowListener(this);
		
		setTitle("�û���¼");
		setSize(324,235);
		setResizable(false);
		Toolkit tk=Toolkit.getDefaultToolkit();
		setLocation((tk.getScreenSize().width-getSize().width)/2,(tk.getScreenSize().height-getSize().height)/2);
		setBackground(new Color(224,244,251));
		
		init();
		btnLogin.addActionListener(this);
		btnRegister.addActionListener(this);
		
		this.setIconImage(new ImageIcon("Tx/05.jpg").getImage());
		this.getRootPane().setDefaultButton(this.btnLogin);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	/**
	 * ��ʼ����½�������塣
	 */
	private void init(){
		lblImg.setIcon(new ImageIcon("images/1.gif"));
		lblImg.setPreferredSize(new Dimension(325,47));
		
		boxState.addItem("����");
		boxState.addItem("����");
		boxState.addItem("����");

		boxState.setBackground(new Color(240,250,255));
		boxState.setPreferredSize(new Dimension(60,20));//Dimension   java��һ���࣬��װ��һ�������ĸ߶ȺͿ�� 
		boxAutoLogin.setBackground(new Color(240,250,255));
		
		boxQQNum.setEditable(true);
		boxQQNum.setPreferredSize(new Dimension(140,20));
		pfPassword.setPreferredSize(new Dimension(140,20));
		
		JPanel pane = new JPanel();
		pane.setBackground(new Color(240,250,255));
		pane.setBorder(new LineBorder(new Color(144,185,215)));
		pane.setLayout(new FlowLayout(FlowLayout.CENTER,15,12));
		pane.setPreferredSize(new Dimension(300,110));
		
		
		pane.add(new FillWidth(20,20,new Color(240,250,255)));
		pane.add(lblQQNum);
		pane.add(boxQQNum);
		pane.add(new FillWidth(20,20,new Color(240,250,255)));
		pane.add(new FillWidth(20,20,new Color(240,250,255)));
		pane.add(lblPassword);
		pane.add(pfPassword);
		pane.add(new FillWidth(20,20,new Color(240,250,255)));
		pane.add(lblState);
		pane.add(boxState);
		pane.add(boxAutoLogin);
		
		
		
		setLayout(new FlowLayout(FlowLayout.CENTER,5,0));
		add(lblImg);
		add(new FillWidth(100,8));
		add(pane);
		add(new FillWidth(300,8));
		add(btnRegister);

		add(new FillWidth(65,20));
		add(btnLogin);
		add(new FillWidth(300,8));

		
	}
	
	/**
	 * ���ð�ť����½��ť��ע�ᰴť���¼���
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnLogin) {

			u.setUserId(boxQQNum.getText().trim());
			u.setPasswd(pfPassword.getText().trim());

			Message m = new Message();
			m.setSender(u.getUserId());
			m.setGetter(u.getUserId());
			m.setContext(u);
			m.setMessageType(MessageType.login);

			// �����̣߳������̹߳���
			ClientSocketThread clientthread = new ClientSocketThread();
			ManageClientThread.addClientSocketThread("socketThread",
					clientthread);
			// ���͵�½��Ϣ
			try {
				ManageClientThread.getClientSocketThread("socketThread")
						.send(m);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			// ����һ�ν��յ�½�ж���Ϣ
			ManageClientThread.getClientSocketThread("socketThread").accept();

		}
		if (e.getSource() == btnRegister) {
			dispose();
			new RegisterView();
		}

	}

	@Override
	public void procedureMsg(Message msg) {
		//��½�ɹ���Ϣ����
		if (msg.getMessageType() == MessageType.login_succ)
		{
			System.out.println("�û���"+this.u.getUserId()+"     ��½�ɹ�");
			ManageUserList.user = (User)msg.getContext();//�����ҵ���Ϣ
			//����ѭ�������߳�
			ManageClientThread.getClientSocketThread("socketThread").start();
		} else if (msg.getMessageType() == MessageType.login_fail) {		
			JOptionPane.showMessageDialog(this,msg.getContext().toString());
			
			try {
				//�ر��߳������Ƴ��̹߳���
				ManageClientThread.getClientSocketThread("socketThread")
						.closeSocket();
				ManageClientThread.removeClientSocketThread("socketThread");
			} catch (Exception e) {
				e.printStackTrace();
			}
			/*//�õ���ʾ�����б����Ϣ
			this.dispose();
			new LoginView();*/
		} else if(msg.getMessageType()==MessageType.show_succ){
			try {
				//����������
				ManageUserList.setUserList(((User)msg.getContext()).getUserList());
				
				
				System.out.println("========="+QQMainFaceView.uid);
				QQMainFaceView.thisClass.show();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			//��½����ر�
			this.dispose();
			
			ManageAllView.removeClientView("loginView1");

		}
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
		ManageAllView.removeClientView("loginView1");
		System.out.println("loginView1"+"      remove");
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		ManageAllView.removeClientView("loginView1");
		System.out.println("loginView1"+"      remove");
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
