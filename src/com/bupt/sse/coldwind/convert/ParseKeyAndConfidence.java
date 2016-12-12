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
 * ��Ҫ����
 * 	1.��΢���ص�json���(name : confidence) ת���� ImageResultBean
 * 
 * @author Administrator
 *
 */
public class ParseKeyAndConfidence {
	private Gson gson = new Gson();
	
	/**
	 * �ⲿ���ô˷����õ�ʫ��
	 * @param json
	 * @return
	 */
	public String getPoem(String json) {
		//Ӣ�ĵļ�
		Map<String, String> enTags = getEnTagMap(json);
		//���ĵļ�
		Map<String, String> zhTags = transEnTags(enTags);
		
		for (String str : zhTags.keySet()) {
			System.out.println("Result key: " + str + " value:" + zhTags.get(str));
		}
			
		return analyze(zhTags);
	}
	
	/**
	 * ƥ���㷨
	 * 
	 * @param input
	 * @return
	 */
	private String analyze(Map<String, String> input) {// �����㷨�õ���ƥ��ʫ��
		//���ݿ��ļ�
		Map<String, PoemBean> poemMap = OperationToDataBaseUtils.getDataFromDataBase();
		
		Map<String, String> resultMap = new HashMap<>();
		//�������ؼ��ֵ�ʫ��ȫ������resultMap��
		for(Entry<String, String> entry : input.entrySet()) {
			String key = entry.getKey();
			if(!poemMap.containsKey(key)) continue;
			
			resultMap.putAll(poemMap.get(key).getPoems());
		}
		
		double maxConfidence = 0;
		String res = "�Բ�����ƥ��";
		//�ҵ�������Ŷȵ�ʫ��
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
	 * ��Ӣ������ת������������
	 * 
	 * @param enTags
	 * @return
	 */
	private Map<String, String> transEnTags(Map<String, String> enTags) {
		//�洢���
		Map<String, String> zhTags = new HashMap<>();

		for (Entry<String, String> entry : enTags.entrySet())
			zhTags.put(translateCi(entry.getKey()), entry.getValue());
		
		return zhTags;
	}
	
	/**
	 * ������Ӣ�ĵ���ת���ɺ���
	 * 
	 * @param json
	 * @return
	 */
	private String translateCi(String json) {
		String result = TranslateEnglishToChinese.translateWord(json);

		BaiduTransResultBean baiduTrans = gson.fromJson(result, BaiduTransResultBean.class);
		
		List<BaiduEnToZnBean> transResultList = baiduTrans.getTransResult();

		//���溺��
		StringBuilder sb = new StringBuilder();
		for (BaiduEnToZnBean transResult : transResultList)
			sb.append(transResult.getDst());

		return sb.toString();
	}
	
	/**
	 * 
	 * ��΢���ص���json�ַ���������ImageResultBean
	 * 
	 * @param json
	 * @return
	 */
	private Map<String, String> getEnTagMap(String json) {
		ImageResultBean imgKeys = gson.fromJson(json, ImageResultBean.class);
		//�洢���
		Map<String, String> enTag = new HashMap<>();
		//��name��confidence�ֱ��ó�����ŵ�Map
		for (ImageKeyBean key : imgKeys.getTags())
			enTag.put(key.getName(), key.getConfidence());
		
		return enTag;
	}
}
