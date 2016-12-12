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
	private List<BaiduEnToZnBean> trans_result;

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

	public List<BaiduEnToZnBean> getTrans_result() {
		return trans_result;
	}

	public void setTrans_result(List<BaiduEnToZnBean> trans_result) {
		this.trans_result = trans_result;
	}
}
