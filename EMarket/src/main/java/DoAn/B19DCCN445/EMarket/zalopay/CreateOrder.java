package DoAn.B19DCCN445.EMarket.zalopay;

//Java version "1.8.0_201"
import org.apache.http.NameValuePair; // https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject; // https://mvnrepository.com/artifact/org.json/json

import DoAn.B19DCCN445.EMarket.dto.ZaloPayOrder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

public class CreateOrder {
	private static Map<String, String> config = new HashMap<String, String>() {
		{
			put("app_id", "2554");
			put("key1", "sdngKKJmqEMzvh5QQcdD2A9XBSKUNaYn");
			put("key2", "trMrHtvjo6myautxDUiAcYsVtaeQ8nhf");
			put("endpoint", "https://sb-openapi.zalopay.vn/v2/create");
		}
	};

	public static void main(ZaloPayOrder zaloPayOrder) throws Exception {
//	 System.out.println(zaloPayOrder.getEmbed_data());
//	 System.out.println(zaloPayOrder.getItem());
		final Map embed_data = new HashMap() {
			{
			}
		};
		final Map[] item = { new HashMap() {
			{
			}
		} };
		
		Map<String, Object> order = new HashMap<String, Object>() {
			{
				put("app_id", zaloPayOrder.getApp_id());
				put("app_user", zaloPayOrder.getApp_user());
				put("app_trans_id", zaloPayOrder.getApp_trans_id()); // translation missing:
																		// vi.docs.shared.sample_code.comments.app_trans_id
				put("app_time", zaloPayOrder.getApp_time()); // miliseconds

				put("amount", zaloPayOrder.getAmount());
				put("item", new JSONObject(item).toString());
				put("description", "EMarket - Payment for the order #" + zaloPayOrder.getApp_trans_id());
				put("embed_data", new JSONObject(embed_data).toString());
				put("bank_code", "");
				put("mac", zaloPayOrder.getMac());

			}
		};
		System.out.println(order.get("embed_data"));
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(config.get("endpoint"));

		List<NameValuePair> params = new ArrayList<>();
		for (Map.Entry<String, Object> e : order.entrySet()) {
			params.add(new BasicNameValuePair(e.getKey(), e.getValue().toString()));
		}

		// Content-Type: application/x-www-form-urlencoded
		post.setEntity(new UrlEncodedFormEntity(params));

		CloseableHttpResponse res = client.execute(post);
		BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
		StringBuilder resultJsonStr = new StringBuilder();
		String line;

		while ((line = rd.readLine()) != null) {
			resultJsonStr.append(line);
		}

		JSONObject result = new JSONObject(resultJsonStr.toString());
		for (String key : result.keySet()) {
			System.out.format("%s = %s\n", key, result.get(key));
		}
	}
}