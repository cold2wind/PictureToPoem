package com.bupt.sse.coldwind.bean;

import java.util.List;

/**
 * 多个单词转换
 * 
 * @author Administrator
 *
 */
public class BaiduTransResultBean {
	private String from;
	private String to;
	private List<BaiduEnToZnBean> transResult;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public List<BaiduEnToZnBean> getTransResult() {
		return transResult;
	}

	public void setTransResult(List<BaiduEnToZnBean> transResult) {
		this.transResult = transResult;
	}
}
