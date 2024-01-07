package DoAn.B19DCCN445.EMarket.zalopay;

import org.apache.http.NameValuePair; 
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import DoAn.B19DCCN445.EMarket.dto.ZaloPayOrder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

public class CreateZaloPayOrder {
	private static Map<String, Object> config = new HashMap<String, Object>() {
		{
			put("app_id", 2554);
			put("key1", "sdngKKJmqEMzvh5QQcdD2A9XBSKUNaYn");
			put("key2", "trMrHtvjo6myautxDUiAcYsVtaeQ8nhf");
			put("endpoint", "https://sb-openapi.zalopay.vn/v2/create");
		}
	};

	public static String main(ZaloPayOrder zaloPayOrder) throws Exception {
//	 System.out.println(zaloPayOrder.getEmbed_data());
//	 System.out.println(zaloPayOrder.getItem());
//		final Map embed_data = new HashMap() {
//			{
//			}
//		};
//		final Map[] item = { new HashMap() {
//			{
//			}
//		} };

		Map<String, Object> order = new HashMap<String, Object>() {
			{
				put("app_id", zaloPayOrder.getApp_id());
				put("app_user", zaloPayOrder.getApp_user());
				put("app_trans_id", zaloPayOrder.getApp_trans_id());
				put("app_time", zaloPayOrder.getApp_time()); // miliseconds
			
				put("amount", zaloPayOrder.getAmount());
				put("item", zaloPayOrder.getItem());
				put("description", "EMarket - Payment for the order #" + zaloPayOrder.getApp_trans_id());
				put("embed_data", zaloPayOrder.getEmbed_data());
				put("bank_code", "");
				put("callback_url","http://localhost:8085/api/zaloPay/callback");

			}
		};
		String data = order.get("app_id") + "|" + order.get("app_trans_id") + "|" + order.get("app_user") + "|"
				+ order.get("amount") + "|" + order.get("app_time") + "|" + order.get("embed_data") + "|"
				+ order.get("item");
		order.put("mac", HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256, config.get("key1").toString(), data));
//		System.out.println(order);
		
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(config.get("endpoint").toString());

		List<NameValuePair> params = new ArrayList<>();
		for (Map.Entry<String, Object> e : order.entrySet()) {
			params.add(new BasicNameValuePair(e.getKey(), e.getValue().toString()));
		}
//		System.out.println(params);

		// Content-Type: application/x-www-form-urlencoded
		post.setEntity(new UrlEncodedFormEntity(params));

		CloseableHttpResponse res = client.execute(post);
		BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
		StringBuilder resultJsonStr = new StringBuilder();
		String line;

		while ((line = rd.readLine()) != null) {
			resultJsonStr.append(line);
		}
		String order_url=null;
		JSONObject result = new JSONObject(resultJsonStr.toString());
		for (String key : result.keySet()) {
//			System.out.format("%s = %s\n", key, result.get(key));
			if(key.equals("order_url")) {
				order_url=(String) result.get(key);
			}
		}
		return order_url;
	}
}
