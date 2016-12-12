package com.bupt.sse.coldwind.bean;

/**
 * 微软服务分析出来的一个关键字和一个自信度
 * 
 * @author Administrator
 *
 */
public class ImageKeyBean {
	/*
	 * 分析出的的一个关键字
	 */
	private String name;
	/*
	 * 关键字对应的自信度
	 */
	private String confidence;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConfidence() {
		return confidence;
	}

	public void setConfidence(String confidence) {
		this.confidence = confidence;
	}
}
