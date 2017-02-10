package com.mq.client.method;

import com.mq.client.Manage.ManageAllView;
import com.mq.client.Manage.ManageNoReadMsg;
import com.mq.client.common.Message;
import com.mq.client.factory.ImessageMethod;
import com.mq.client.util.IClientView;


public class ChatMethod implements ImessageMethod {

	private Message message;

	@Override
	public void setMessage(Message message) {
		// TODO Auto-generated method stub
		this.message = message;
	}

	@Override
	public Message getMessage() {
		// TODO Auto-generated method stub
		return this.message;
	}

	@Override
	public void Deal_Msg() throws Exception {
		// TODO Auto-generated method stub

		System.out.println("第三包："+this.message.getContext().toString());
		String viewName = message.getSender().trim()+ message.getGetter().trim();
			
		
		System.out.println("要发送的聊天窗口 "+viewName);
		
		if(!ManageAllView.isFound(viewName))
		{
			/*if(!ManageNoReadMsg.isFound(viewName))
			     ManageNoReadMsg.addnoReadChat(viewName, this.message);
			else{
				
				ManageNoReadMsg.setMsg(viewName, this.message);
			}*/
			
			ManageNoReadMsg.addnoReadChat(viewName, this.message);
			
			
			IClientView iClientView =  ManageAllView.getClientView("QQMainFace");
	        
			iClientView.procedureMsg(getMessage());
			
		}
		else{
		IClientView iClientView =  ManageAllView.getClientView(viewName);
        
		iClientView.procedureMsg(getMessage());
		}
	}

}
