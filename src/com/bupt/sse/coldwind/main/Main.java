package com.bupt.sse.coldwind.main;

import com.bupt.sse.coldwind.ms.ImageKeyAndConfidence;

public class Main {
	public static void main(String[] mh) {
		String imgUrl1 = "http://imga1.pic21.com/bizhi/131129/04564/s10.jpg";//�ɹ�,ѩ
		String imgUrl2 = "http://imga1.pic21.com/bizhi/131118/04004/s01.jpg";//�ɹ�,��
		String imgUrl3 = "http://m2.quanjing.com/2m/nature008/nature1336052.jpg";//��
		
    	System.out.println(ImageKeyAndConfidence.getKeyAndConfidence(imgUrl3));
    }
}
