package com.bupt.sse.coldwind.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import com.bupt.sse.coldwind.bean.PoemBean;
import com.bupt.sse.coldwind.constant.Constant;

public class OperationToDataBaseUtils {

    public static Map<String, PoemBean> getDataFromDataBase() {
        File file = new File(Constant.PATH_DATABASE);
        if (!file.exists()) {
            return null;
        }
        //存储数据库文件
        Map<String, PoemBean> keyAndPoem = new HashMap<String, PoemBean>();
        try {
			FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String str = null;
            while ((str = br.readLine()) != null) {
                String[] tmpContent = str.split(":");
                if (tmpContent.length == 2) {
                	//雪:0.45494_晚来天欲雪，能饮一杯无。#0.60426_昔去雪如花，今来花似雪。#0.51308_梅须逊雪三分白，雪却输梅一段香。#
                    PoemBean poem = new PoemBean(tmpContent[1]);
                    //雪是键
                    keyAndPoem.put(tmpContent[0], poem);
                }
            }
            
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return keyAndPoem;
    }
}
