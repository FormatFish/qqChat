package com.mq.client.util;

import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import com.mq.client.common.Message;

public interface IClientView {

	public void procedureMsg(Message msg);
}
/*备注：
 * 每个界面要实现 ActionListener,IClientView,WindowListener三个接口
 * 
 * 界面  构造时候 将界面加入 ManageAllView 类中
 *    销毁时  从 ManageAllView 类中 去除
 * 
 * 
 * 
 * */
 