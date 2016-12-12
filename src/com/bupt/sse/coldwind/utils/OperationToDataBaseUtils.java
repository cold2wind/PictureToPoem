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
        //�洢���ݿ��ļ�
        Map<String, PoemBean> keyAndPoem = new HashMap<String, PoemBean>();
        try {
			FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String str = null;
            while ((str = br.readLine()) != null) {
                String[] tmpContent = str.split(":");
                if (tmpContent.length == 2) {
                	//ѩ:0.45494_��������ѩ������һ���ޡ�#0.60426_��ȥѩ�绨����������ѩ��#0.51308_÷��ѷѩ���ְף�ѩȴ��÷һ���㡣#
                    PoemBean poem = new PoemBean(tmpContent[1]);
                    //ѩ�Ǽ�
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
