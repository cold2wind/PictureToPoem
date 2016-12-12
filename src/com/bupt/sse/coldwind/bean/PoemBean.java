package com.bupt.sse.coldwind.bean;

import java.util.HashMap;
import java.util.Map;

public class PoemBean {

	private Map<String, String> poems = new HashMap<>();
	
	/**
	 * �����µ����ݴ�ŵ�map��, ��:ʫ��  	ֵ:���Ŷ�
	 * 	0.45494_��������ѩ������һ���ޡ�#0.60426_��ȥѩ�绨����������ѩ��#0.51308_÷��ѷѩ���ְף�ѩȴ��÷һ���㡣#
	 * @param str
	 */
	public PoemBean(String str) {
		String[] s = str.split("#");
		for (String temp : s) {
			if (temp.trim().length() == 0) continue;
			
			String[] strs = temp.split("_");
			
			if (strs.length == 2) poems.put(strs[1], strs[0]);
		}
	}

	public Map<String, String> getPoems() {
		return this.poems;
	}
}
