package com.bupt.sse.coldwind.bean;

import java.util.List;

public class ImageResultBean {
	/*
	 * 由微软提供的服务返回的结果
	 */
	private List<ImageKeyBean> tags;

	public List<ImageKeyBean> getTags() {
		return tags;
	}

	public void setTags(List<ImageKeyBean> tags) {
		this.tags = tags;
	}
}
