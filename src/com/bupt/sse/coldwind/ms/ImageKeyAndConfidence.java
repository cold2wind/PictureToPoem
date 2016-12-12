package com.bupt.sse.coldwind.ms;

import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * ΢���ṩ�ķ��񣬷���ͼƬ����ȡ�ؼ��ʲ���ȡ��Ӧ�����Ŷ�
 * 
 * @author Administrator
 *
 */
public class ImageKeyAndConfidence {
	private static final String TARGET_URL = "https://api.projectoxford.ai/vision/v1.0/tag";
	private static final String SUBSCRIPTION_KEY = "d13e2d7ca1664dc39f27c43b5023ecb4";
	
	/**
	 * ��ȡ�ؼ��ʺ����Ŷ�
	 * 
	 * @param imageUrl
	 */
    public static String getKeyAndConfidence(String imageUrl) {
        HttpClient httpclient = HttpClients.createDefault();
        try {
            URIBuilder builder = new URIBuilder(TARGET_URL);
            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", SUBSCRIPTION_KEY);

            // Request body
            StringEntity reqEntity = new StringEntity("{\"url\":\"" + imageUrl + "\"}");
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            return entity == null ? null : EntityUtils.toString(entity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
