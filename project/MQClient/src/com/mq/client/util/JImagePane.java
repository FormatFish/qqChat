package com.mq.client.util;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/** *//**
 * �����ñ���ͼƬ��JPanel���ṩ��������ʾ����ͼƬ�ķ�ʽ�����С�ƽ�̺����졣
 * δ���ñ���ͼƬ������£�ͬJPanel��
 * 
 * @author 003
 */
public class JImagePane extends JPanel
{
	private String path;
	
    private static final long serialVersionUID = -8251916094895167058L;
    
    /** *//**
     * ����
     */
    public static final String CENTRE = "Centre";
    
    /** *//**
     * ƽ��
     */
    public static final String TILED = "Tiled";

    /** *//**
     * ����
     */
    public static final String SCALED = "Scaled";

    /** *//**
     * ����ͼƬ
     */
    private Image backgroundImage;
    
    /** *//**
     * ����ͼƬ��ʾģʽ
     */
    private String imageDisplayMode;

    /** *//**
     * ����ͼƬ��ʾģʽ��������������������ڱ�Ҫʱ��չ��
     */
    private int modeIndex;

    /** *//**
     * ����һ��û�б���ͼƬ��JImagePane
     */
    public JImagePane()
    {
        this(null, CENTRE , "images/01.jpg");
    }
    
    /** *//**
     * ����һ������ָ������ͼƬ��ָ����ʾģʽ��JImagePane
     * @param image ����ͼƬ
     * @param modeName ����ͼƬ��ʾģʽ
     */
    public JImagePane(Image image, String modeName , String path)
    {
        super();
        this.path = path;
        setBackgroundImage(image);
        setImageDisplayMode(modeName);
    }
    
    /** *//**
     * ���ñ���ͼƬ
     * @param image ����ͼƬ
     */
    public void setBackgroundImage(Image image)
    {
        this.backgroundImage = image;
        this.repaint();
    }

    /** *//**
     * ��ȡ����ͼƬ
     * @return ����ͼƬ
     */
    public Image getBackgroundImage()
    {
        return backgroundImage;
    }

    /** *//**
     * ���ñ���ͼƬ��ʾģʽ
     * @param modeName ģʽ���ƣ�ȡֵ������ImagePane.TILED  ImagePane.SCALED  ImagePane.CENTRE
     */
    public void setImageDisplayMode(String modeName)
    {
        if(modeName != null)
        {
            modeName = modeName.trim();
            
            //����
            if(modeName.equalsIgnoreCase(CENTRE))
            {
                this.imageDisplayMode = CENTRE;
                modeIndex = 0;
            }
            //ƽ��
            else if(modeName.equalsIgnoreCase(TILED))
            {
                this.imageDisplayMode = TILED;
                modeIndex = 1;
            }
            //����
            else if(modeName.equalsIgnoreCase(SCALED))
            {
                this.imageDisplayMode = SCALED;
                modeIndex = 2;
            }
            
            this.repaint();
        }
    }

    /** *//**
     * ��ȡ����ͼƬ��ʾģʽ
     * @return ��ʾģʽ
     */
    public String getImageDisplayMode()
    {
        return imageDisplayMode;
    }

    /** *//**
     * �������
     * @see javax.swing.JComponent#paintComponent(Graphics)
     */
    
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        //��������˱���ͼƬ����ʾ
        if(backgroundImage != null)
        {
            int width = this.getWidth();
            int height = this.getHeight();
            int imageWidth = backgroundImage.getWidth(this);
            int imageHeight = backgroundImage.getHeight(this);

            switch(modeIndex)
            {
                //����
                case 0:
                {
                    int x = (width - imageWidth) / 2;
                    int y = (height - imageHeight) / 2;
                    g.drawImage(backgroundImage, x, y, this);
                    break;
                }
                //ƽ��
                case 1:
                {
                    for(int ix = 0; ix < width; ix += imageWidth)
                    {
                        for(int iy = 0; iy < height; iy += imageHeight)
                        {
                            g.drawImage(backgroundImage, ix, iy, this);
                        }
                    }
                    break;
                }
                //����
                case 2:
                {
                    g.drawImage(backgroundImage, 0, 0, width, height, this);
                    break;
                }
            }
        }
    }

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}

