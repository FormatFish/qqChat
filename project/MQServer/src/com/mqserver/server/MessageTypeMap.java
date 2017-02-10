package com.mqserver.server;

import java.util.HashMap;
import java.util.Map;

public class MessageTypeMap {
	Map<Integer, String> map = new HashMap<Integer, String>();
	public MessageTypeMap(){
		map.put(0, "��¼����");
		map.put(1, "ע������");
		map.put(2, "���������б�");
		map.put(3, "�������");
		map.put(4, "��������");
		map.put(5, "��������");
		map.put(6, "��¼�ɹ�");
		map.put(7, "��¼�ǰ�");
		map.put(8, "ע��ɹ�");
		map.put(9, "ע��ʧ��");
		map.put(10, "չʾ�����б�ɹ�	");
		map.put(11, "չʾ�����б�ʧ��	");
		map.put(12, "��Ӻ��ѳɹ�");
		map.put(13, "��Ӻ���ʧ��");
		map.put(14, "������Ϣ����");
		//map.put(15, "����");
		map.put(16, "����");
		//map.put(17, "ע������");
		map.put(18, "�����¼");
		map.put(19, "����������Ϣ");
		map.put(20, "ɾ������");
		map.put(21, "ɾ�����ѳɹ�");
		map.put(22, "ɾ������ʧ��");
		map.put(23, "�ظ��������");
		map.put(24, "�ظ��ܾ����");
		map.put(25, "��������");
		map.put(26, "����");
		map.put(27, "�޸�ͷ��");
		map.put(28, "�޸�ǩ��");
		map.put(29, "��ȡ�û���Ϣ");
		map.put(30, "�޸�Ƥ��");
		map.put(31, "�޸��û�����");
		map.put(32, "���ļ�����");
		map.put(33, "�Ƿ�����ļ�");
		map.put(34, "ͬ������ļ�");
		map.put(35, "�ܾ������ļ�");
	}
	public String getString(int id){
		return this.map.get(id);
	}
}
