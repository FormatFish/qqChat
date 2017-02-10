package com.mqserver.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ShowServerView extends JDialog implements ActionListener {

	public static void main(String[] args) {
		ShowServerView ac = new ShowServerView();
	}

	private JTabbedPane paneFindWay = new JTabbedPane();

	private JPanel paneBtn = new JPanel();
	private JButton btnFind = new JButton("查找");
	private JButton btnClose = new JButton("关闭");

	private JButton btnAddFriend = new JButton("加为好友");

	private JPanel paneBaseFind = new JPanel();


	private CardLayout card = new CardLayout();

	private JPanel paneBaseSecond = new JPanel();

	private JLabel lblInfo = new JLabel("以下是服务器为您查询到的日志：");
	private JLabel btnNameExactFind = new JLabel("查询好友");
	private JTextField txtQQ = new JTextField();

	private JLabel lblExactFind = new JLabel();

	private JLabel lblInfo2 = new JLabel("以下是QQ为您查找到的服务器日志。");

	public JTable tableUser = null;

	private Color bgColor = new Color(252, 254, 252);

	public ShowServerView() {

		setTitle("服务器日志");
		setSize(400, 325);
		setResizable(false);
		Toolkit tk = Toolkit.getDefaultToolkit();
		setLocation((tk.getScreenSize().width - getSize().width) / 2,
				(tk.getScreenSize().height - getSize().height) / 2);
		init();

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);

	}

	private void init() {

		paneBtn.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		paneBtn.add(btnFind);

		paneBtn.add(btnClose);

		lblInfo.setPreferredSize(new Dimension(350, 34));
		lblInfo.setHorizontalAlignment(SwingConstants.LEFT);

		btnNameExactFind.setBackground(bgColor);
		btnNameExactFind.setPreferredSize(new Dimension(218, 20));
		txtQQ.setPreferredSize(new Dimension(218, 20));
	

		lblInfo2.setPreferredSize(new Dimension(360, 34));
		lblInfo2.setOpaque(true);
		lblInfo2.setBackground(bgColor);

		String[] columnNames = { "日志文件" };
		File f = new File("server_notes" + File.separator);
		File file[] = f.listFiles();
		File filelist[][] = new File[file.length][1];
		for (int i = 0; i < file.length; i++) {
			filelist[i][0] = file[i];
		}
		this.tableUser = new JTable(filelist, columnNames);

		this.tableUser.setPreferredScrollableViewportSize(new Dimension(360,
				160));

		this.tableUser
				.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		this.tableUser.setPreferredScrollableViewportSize(new Dimension(360,
				160));
	

		paneBaseSecond.setOpaque(true);
		paneBaseSecond.setBackground(new Color(252, 254, 252));
		paneBaseSecond.add(lblInfo2);
		paneBaseSecond.add(new JScrollPane(this.tableUser));

		paneBaseFind.setLayout(card);
		paneBaseFind.add("second", paneBaseSecond);

		paneFindWay.add("基本查找", paneBaseFind);

		add(new FillWidth(5, 5), BorderLayout.NORTH);

		add(paneFindWay, BorderLayout.CENTER);
		add(new FillWidth(5, 5), BorderLayout.EAST);
		add(new FillWidth(5, 5), BorderLayout.WEST);
		add(paneBtn, BorderLayout.SOUTH);

		btnFind.addActionListener(this);
		btnClose.addActionListener(this);
		btnAddFriend.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnFind) {

			String path = this.tableUser.getValueAt(this.tableUser.getSelectedRow(), 0).toString();
			String temp = null;
			String str = null;
			BufferedReader br = null;
			StringBuilder sb = new StringBuilder();
			try {
				br = new BufferedReader(new InputStreamReader(
						new FileInputStream(path)));
				while ((temp = br.readLine()) != null) {
					sb.append(temp);
					sb.append(System.getProperty("line.separator"));
				}
				br.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			str = sb.toString();
			ShowServerLogView sslv = new ShowServerLogView(str);
			if (!this.tableUser.isFocusable()) {
				JOptionPane.showMessageDialog(this, "请选定一个日志!");
				return;
			}
		}

		if (e.getSource() == btnClose) {
			dispose();
			return;
		}

	}

	

}
