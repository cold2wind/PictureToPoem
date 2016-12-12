package com.bupt.sse.coldwind.main;

import com.bupt.sse.coldwind.convert.ParseKeyAndConfidence;
import com.bupt.sse.coldwind.ms.ImageKeyAndConfidence;

public class Main {
	/**
     * 返回诗句 :
     * 
     * @param imageUrl
     * @return
     */
    public static String getPoem(String imageUrl) {
    	/**
    	 * json : {"tags":[{"name":"water","confidence":0.99941468238830566},{"name":"person","confidence":0.93677496910095215},{"name":"sport","confidence":0.84868776798248291},{"name":"swimming","confidence":0.84544718265533447,"hint":"sport"},{"name":"water sport","confidence":0.82753580808639526,"hint":"sport"},{"name":"pool","confidence":0.80549460649490356}],
    	 * "requestId":"238bd65d-5aa5-4726-b1ec-8bb7791c542b","metadata":{"width":400,"height":400,"format":"Jpeg"}}
    	 */
    	String json = ImageKeyAndConfidence.getKeyAndConfidence(imageUrl);
    	
    	return new ParseKeyAndConfidence().getPoem(json);
    }
	
	public static void main(String[] mh) {
//		String imgUrl1 = "http://imga1.pic21.com/bizhi/131129/04564/s10.jpg";//成功,雪
//		String imgUrl2 = "http://imga1.pic21.com/bizhi/131118/04004/s01.jpg";//成功,花
		String imgUrl3 = "http://m2.quanjing.com/2m/nature008/nature1336052.jpg";//鸟
		
    	System.out.println(getPoem(imgUrl3));
    }
}
