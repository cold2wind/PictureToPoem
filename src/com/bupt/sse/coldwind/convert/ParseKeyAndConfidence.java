package com.bupt.sse.coldwind.convert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.bupt.sse.coldwind.baidu.TranslateEnglishToChinese;
import com.bupt.sse.coldwind.bean.BaiduTransResultBean;
import com.bupt.sse.coldwind.bean.ImageKeyBean;
import com.bupt.sse.coldwind.bean.ImageResultBean;
import com.bupt.sse.coldwind.bean.BaiduEnToZnBean;
import com.bupt.sse.coldwind.bean.PoemBean;
import com.bupt.sse.coldwind.utils.OperationToDataBaseUtils;
import com.google.gson.Gson;

/**
 * 主要功能
 * 	1.将微软返回的json结果(name : confidence) 转换成 ImageResultBean
 * 
 * @author Administrator
 *
 */
public class ParseKeyAndConfidence {
	private Gson gson = new Gson();
	
	/**
	 * 外部调用此方法得到诗句
	 * @param json
	 * @return
	 */
	public String getPoem(String json) {
		//英文的键
		Map<String, String> enTags = getEnTagMap(json);
		//中文的键
		Map<String, String> zhTags = transEnTags(enTags);
		
		for (String str : zhTags.keySet()) {
			System.out.println("Result key: " + str + " value:" + zhTags.get(str));
		}
			
		return analyze(zhTags);
	}
	
	/**
	 * 匹配算法
	 * 
	 * @param input
	 * @return
	 */
	private String analyze(Map<String, String> input) {// 分析算法得到最匹配诗句
		//数据库文件
		Map<String, PoemBean> poemMap = OperationToDataBaseUtils.getDataFromDataBase();
		
		Map<String, String> resultMap = new HashMap<>();
		//将包含关键字的诗句全部放在resultMap中
		for(Entry<String, String> entry : input.entrySet()) {
			String key = entry.getKey();
			if(!poemMap.containsKey(key)) continue;
			
			resultMap.putAll(poemMap.get(key).getPoems());
		}
		
		double maxConfidence = 0;
		String res = "对不起，无匹配";
		//找到最大自信度的诗句
		for (String resultKey : resultMap.keySet()) {
			Double tmp = Double.valueOf(resultMap.get(resultKey));
			if (tmp > maxConfidence) {
				maxConfidence = tmp;
				res = resultKey;
			}
		}
		
		return res;
	}
	
	/**
	 * 将英文名称转换成中文名称
	 * 
	 * @param enTags
	 * @return
	 */
	private Map<String, String> transEnTags(Map<String, String> enTags) {
		//存储结果
		Map<String, String> zhTags = new HashMap<>();

		for (Entry<String, String> entry : enTags.entrySet())
			zhTags.put(translateCi(entry.getKey()), entry.getValue());
		
		return zhTags;
	}
	
	/**
	 * 将单个英文单词转换成汉字
	 * 
	 * @param json
	 * @return
	 */
	private String translateCi(String json) {
		String result = TranslateEnglishToChinese.translateWord(json);

		BaiduTransResultBean baiduTrans = gson.fromJson(result, BaiduTransResultBean.class);
		
		List<BaiduEnToZnBean> transResultList = baiduTrans.getTransResult();

		//保存汉字
		StringBuilder sb = new StringBuilder();
		for (BaiduEnToZnBean transResult : transResultList)
			sb.append(transResult.getDst());

		return sb.toString();
	}
	
	/**
	 * 
	 * 将微软返回的是json字符串解析成ImageResultBean
	 * 
	 * @param json
	 * @return
	 */
	private Map<String, String> getEnTagMap(String json) {
		ImageResultBean imgKeys = gson.fromJson(json, ImageResultBean.class);
		//存储结果
		Map<String, String> enTag = new HashMap<>();
		//将name和confidence分别拿出来存放到Map
		for (ImageKeyBean key : imgKeys.getTags())
			enTag.put(key.getName(), key.getConfidence());
		
		return enTag;
	}
}
