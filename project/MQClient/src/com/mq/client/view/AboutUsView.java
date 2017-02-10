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
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.mq.client.util.JImagePane;

public class AboutUsView extends JFrame implements ActionListener{
	
	public static void main(String[] args)
	{
		AboutUsView abc = new AboutUsView();
	}
	
	private JImagePane lblPhoto = new JImagePane();
	private JImagePane lblPhoto1 = new JImagePane();
	private JImagePane lblPhoto2 = new JImagePane();
	private JImagePane lblPhoto3 = new JImagePane();
	
	
	private JLabel KongGe = new JLabel("        ");
	private JLabel KongGe1 = new JLabel("        ");
	private JLabel KongGe2 = new JLabel("        ");
	private JLabel KongGe3 = new JLabel("        ");
	private JLabel KongGe4 = new JLabel("               ");
	private JLabel KongGe5 = new JLabel("                    ");
	private JLabel KongGe6 = new JLabel("                   ");
	private JLabel KongGe7 = new JLabel("             ");
	
	private JLabel ZhangXiongWei = new JLabel("张雄伟");
	private JLabel GuoHao = new JLabel("郭   浩");
	private JLabel ChengZeYan = new JLabel("成泽岩");
	private JLabel CuiQiang = new JLabel("崔   强");
	
	private JLabel ZongJieShao = new JLabel("关于我们：以下是此软件设计者的相关信息");
	private JTable myTable = null;
	private JLabel lblG = new JLabel("郭浩	1130班	服务器与数据库设计者");
	private JLabel lblC = new JLabel("崔强	1127班	用户界面设计者           ");
	private JLabel lblCh = new JLabel("成泽岩	1127班	业务逻辑设计者\n");
	private JLabel lblZ = new JLabel("张雄伟	1127班	模块接口设计者");
	
	public AboutUsView() 
	{
		setTitle("关于我们");
		setSize(480,280);
		setResizable(false);
		Toolkit tk=Toolkit.getDefaultToolkit();
		setLocation((tk.getScreenSize().width-getSize().width)/2,(tk.getScreenSize().height-getSize().height)/2);
		init();
		setVisible(true);
	}
	
	
	
	private void init(){
		
		
		String[] columnNames = {"姓名","班级","任务/角色"};
		Object[][] data =
			{
		             {"郭浩", "1130班","服务器与数据库设计者"},
			         {"崔强", "1127班","用户界面设计者"},
			         {"成泽岩","1127班","业务逻辑设计者"},
			         {"张雄伟","1127班","模块接口设计者"},
			};
		
		
		this.myTable = new JTable(data , columnNames);
		
		//myTable.setLocation(400, 50);
		//myTable.setSize(200,200);
		
		JPanel pane = new JPanel();
		
		myTable.setPreferredSize(new Dimension(400,65));
		ZhangXiongWei.setPreferredSize(new Dimension(50,30));

		ZhangXiongWei.setLocation(0, 20);
		
		
		GuoHao.setPreferredSize(new Dimension(50,30));

		GuoHao.setLocation(110, 20);
		
		ChengZeYan.setPreferredSize(new Dimension(50,30));
		
		ChengZeYan.setLocation(220, 20);
		
		CuiQiang.setPreferredSize(new Dimension(50,30));
		
		CuiQiang.setLocation(330, 20);
		
		
		lblPhoto.setOpaque(true);
		lblPhoto.setLocation(0, 30);
		lblPhoto.setBackground(Color.WHITE);
		lblPhoto.setPreferredSize(new Dimension(85,85));
		lblPhoto.setBorder(new LineBorder(Color.DARK_GRAY));
		
		

		lblPhoto1.setOpaque(true);
		lblPhoto1.setLocation(110, 30);
		lblPhoto1.setBackground(Color.WHITE);
		lblPhoto1.setPreferredSize(new Dimension(85,85));
		lblPhoto1.setBorder(new LineBorder(Color.DARK_GRAY));
		
		
		lblPhoto2.setOpaque(true);
		lblPhoto2.setLocation(220, 30);
		lblPhoto2.setBackground(Color.WHITE);
		lblPhoto2.setPreferredSize(new Dimension(85,85));
		lblPhoto2.setBorder(new LineBorder(Color.DARK_GRAY));
		
		
		
		lblPhoto3.setOpaque(true);
		lblPhoto2.setLocation(330, 30);
		lblPhoto3.setBackground(Color.WHITE);
		lblPhoto3.setPreferredSize(new Dimension(85,85));
		lblPhoto3.setBorder(new LineBorder(Color.DARK_GRAY));
		
	      
		
		JPanel panePhoto = new JPanel();
		panePhoto.setSize(450,500);

		
		panePhoto.add(lblPhoto);
		panePhoto.add(KongGe);
		panePhoto.add(lblPhoto1);
		panePhoto.add(KongGe1);
		panePhoto.add(lblPhoto2);
		panePhoto.add(KongGe2);
		panePhoto.add(lblPhoto3);
		
		
		

		panePhoto.add(KongGe3);
		panePhoto.add(ZhangXiongWei);
		ZhangXiongWei.setFont(new   java.awt.Font("Dialog",   1,   15));  
		ZhangXiongWei.setForeground(Color.blue);
		

		panePhoto.add(KongGe4);
		panePhoto.add(GuoHao);
		GuoHao.setFont(new   java.awt.Font("Dialog",   1,   15));  
		GuoHao.setForeground(Color.blue);
		
		panePhoto.add(KongGe5);
		panePhoto.add(ChengZeYan);
		ChengZeYan.setFont(new   java.awt.Font("Dialog",   1,   15));  
		ChengZeYan.setForeground(Color.blue);
		
		panePhoto.add(KongGe6);
		panePhoto.add(CuiQiang);
		CuiQiang.setFont(new   java.awt.Font("Dialog",   1,   15)); 
		CuiQiang.setForeground(Color.blue);
		panePhoto.add(CuiQiang);
		
		
		panePhoto.add(KongGe7);
		panePhoto.add(ZongJieShao);
		

		pane.add(this.myTable);
		panePhoto.add(pane);

		pane.add(new JScrollPane(this.myTable));
		
		
		ZongJieShao.setForeground(Color.red);
		
		
		
		
		lblPhoto.setBackgroundImage(new ImageIcon("images/zxw.jpg").getImage());
		lblPhoto.setImageDisplayMode(JImagePane.SCALED);
		lblPhoto1.setBackgroundImage(new ImageIcon("images/gh.jpg").getImage());
		lblPhoto1.setImageDisplayMode(JImagePane.SCALED);
		lblPhoto2.setBackgroundImage(new ImageIcon("images/czy.jpg").getImage());
		lblPhoto2.setImageDisplayMode(JImagePane.SCALED);
		lblPhoto3.setBackgroundImage(new ImageIcon("images/cq.jpg").getImage());
		lblPhoto3.setImageDisplayMode(JImagePane.SCALED);	
		
		add(panePhoto);
		
	}
	
	/**
	 * 取消按钮事件。
	 */
	public void actionPerformed(ActionEvent e) {
	//	if(e.getSource()==btnCancle){
	//		dispose();
		
	//	}	
	//		
		}
		
		}

	
	

