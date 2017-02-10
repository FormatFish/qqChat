package com.mq.client.util;
import javax.swing.border.LineBorder;
import java.awt.*;
import javax.swing.*;
public class IconRenderer extends JLabel implements ListCellRenderer{
   public Component getListCellRendererComponent(JList list, Object obj, int row, boolean sel, boolean hasFocus) 
   {
      Object[] cell = (Object[])obj;   //�õ��еĲ���
      setIcon((Icon)cell[0]);  //����ͼ��
      setText(cell[1].toString()); //�����ı�
      setBorder(new LineBorder(Color.WHITE)); //���ñ߽�
      if (sel){
        setForeground(Color.MAGENTA);  //���ѡ����,�����ı���ɫΪƷ��ɫ
      }
      else{
       setForeground(list.getForeground()); //���δѡ��,�����ı���ɫΪĬ��ɫ
      }
      return this;
   }
}