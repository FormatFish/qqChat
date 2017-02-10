package com.mq.client.util;
import javax.swing.border.LineBorder;
import java.awt.*;
import javax.swing.*;
public class IconRenderer extends JLabel implements ListCellRenderer{
   public Component getListCellRendererComponent(JList list, Object obj, int row, boolean sel, boolean hasFocus) 
   {
      Object[] cell = (Object[])obj;   //得到行的参数
      setIcon((Icon)cell[0]);  //设置图标
      setText(cell[1].toString()); //设置文本
      setBorder(new LineBorder(Color.WHITE)); //设置边界
      if (sel){
        setForeground(Color.MAGENTA);  //如果选中了,设置文本颜色为品红色
      }
      else{
       setForeground(list.getForeground()); //如果未选中,设置文本颜色为默认色
      }
      return this;
   }
}