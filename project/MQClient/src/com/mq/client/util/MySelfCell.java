package com.mq.client.util;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.*;
import javax.swing.border.Border;
public class MySelfCell extends JPanel implements ListCellRenderer{

	private Image image;
	private String str = null;//�û���Ϣ
    private Border selectedBorder = BorderFactory.createLineBorder(Color.blue,1);
    private Border emptyBorder = BorderFactory.createEmptyBorder(1,1,1,1);
	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		MyPane myPane = (MyPane)value;
		this.setImage(myPane.getImage());
		this.setBackground(new Color(255 , 255 , 255));
		this.setStr(myPane.getStr());
		
		if ( isSelected ) setBorder (selectedBorder);
        else setBorder(emptyBorder);
		return this;
	}
	

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
		this.repaint();
	}
	
	public Dimension getPreferredSize()//����Cell�ĳߴ�
    {
       return new Dimension(30, 50);   
    }
	
	protected void paintComponent(Graphics g)//�����ػ�����
    {
        super.paintComponent(g);
        
        //��������˱���ͼƬ����ʾ
        if(image != null)
        {
            int width = this.getWidth();//panel�ĳ���
            int height = this.getHeight();
            int imageWidth = image.getWidth(this);//ͼƬ�ĳ���
            int imageHeight = image.getHeight(this);
            int myWidth = (height * imageWidth)/(imageHeight);

            g.drawImage(image, 0, 0, myWidth, height, this);
            g.drawString(str, myWidth+10, height/2);                       
            
        }
    }

}
