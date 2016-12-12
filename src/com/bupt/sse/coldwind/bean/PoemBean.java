package com.bupt.sse.coldwind.bean;

import java.util.HashMap;
import java.util.Map;

public class PoemBean {

	private Map<String, String> poems = new HashMap<>();
	
	/**
	 * 将如下的内容存放到map中, 键:诗句  	值:自信度
	 * 	0.45494_晚来天欲雪，能饮一杯无。#0.60426_昔去雪如花，今来花似雪。#0.51308_梅须逊雪三分白，雪却输梅一段香。#
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
