package com.mqserver.server;

import java.util.HashMap;
import java.util.Map;

public class MessageTypeMap {
	Map<Integer, String> map = new HashMap<Integer, String>();
	public MessageTypeMap(){
		map.put(0, "登录命令");
		map.put(1, "注册命令");
		map.put(2, "更新朋友列表");
		map.put(3, "添加朋友");
		map.put(4, "查找朋友");
		map.put(5, "聊天命令");
		map.put(6, "登录成功");
		map.put(7, "登录是吧");
		map.put(8, "注册成功");
		map.put(9, "注册失败");
		map.put(10, "展示好友列表成功	");
		map.put(11, "展示好友列表失败	");
		map.put(12, "添加好友成功");
		map.put(13, "添加好友失败");
		map.put(14, "备用消息命令");
		//map.put(15, "下线");
		map.put(16, "下线");
		//map.put(17, "注册命令");
		map.put(18, "聊天记录");
		map.put(19, "接收离线消息");
		map.put(20, "删除好友");
		map.put(21, "删除好友成功");
		map.put(22, "删除好友失败");
		map.put(23, "回复接受添加");
		map.put(24, "回复拒绝添加");
		map.put(25, "致命错误");
		map.put(26, "备用");
		map.put(27, "修改头像");
		map.put(28, "修改签名");
		map.put(29, "获取用户信息");
		map.put(30, "修改皮肤");
		map.put(31, "修改用户资料");
		map.put(32, "传文件命令");
		map.put(33, "是否接受文件");
		map.put(34, "同意接收文件");
		map.put(35, "拒绝接收文件");
	}
	public String getString(int id){
		return this.map.get(id);
	}
}
