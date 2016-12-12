package com.bupt.sse.coldwind.baidu;

import java.io.UnsupportedEncodingException;

public class TranslateEnglishToChinese {
	/**
	 * 将英文转换成汉字
	 * 
	 * @param query
	 * @return
	 */
	public static String translateWord(String query){
		TransApi transApi = new TransApi(Constant.APP_ID, Constant.SECURITY_KEY);
		String hanzi = null;
		
		try {
			String ci = transApi.getTransResult(query, "en", "zh");
			hanzi = new String(ci.getBytes(),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return  hanzi; 
	}
	
	public static void main(String[] mh) {
		
	}
}
