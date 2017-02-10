package com.mq.client.util;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.*;
public class MyPane extends JPanel{
	private Image image;
	private String str = null;//用户信息
	
	public MyPane(Image image  , String str)
	{
		super();
        this.str = str;
        setImage(image);
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
	
	protected void paintComponent(Graphics g)//自制重画函数
    {
        super.paintComponent(g);
        
        //如果设置了背景图片则显示
        if(image != null)
        {
            int width = this.getWidth();//panel的长宽
            int height = this.getHeight();
            int imageWidth = image.getWidth(this);//图片的长宽
            int imageHeight = image.getHeight(this);

            g.drawImage(image, 0, 0, width/2, height, this);
            g.drawString(str, width/2+10, height/2);                       
            
        }
    }
	
}
