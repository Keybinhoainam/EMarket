package DoAn.B19DCCN445.EMarket.zalopay;

import org.apache.http.NameValuePair; // https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject; // https://mvnrepository.com/artifact/org.json/json

import DoAn.B19DCCN445.EMarket.model.Order;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class GetZaloOrderStatus {
	private static final Map<String, Object> config = new HashMap<String, Object>() {
		{
			put("app_id", 2554);
			put("key1", "sdngKKJmqEMzvh5QQcdD2A9XBSKUNaYn");
			put("key2", "trMrHtvjo6myautxDUiAcYsVtaeQ8nhf");
			put("endpoint", "https://sb-openapi.zalopay.vn/v2/query");
		}
	};

	public static int main(Order order) throws Exception {
		String app_trans_id = order.getId();
		String data = config.get("app_id") + "|" + app_trans_id + "|" + config.get("key1"); // appid|app_trans_id|key1
		String mac = HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256, config.get("key1").toString(), data);

		List<NameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("app_id", config.get("app_id").toString()));
		params.add(new BasicNameValuePair("app_trans_id", app_trans_id));
		params.add(new BasicNameValuePair("mac", mac));
//		System.out.println(params);
		URIBuilder uri = new URIBuilder(config.get("endpoint").toString());
		uri.addParameters(params);

		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(uri.build());
		post.setEntity(new UrlEncodedFormEntity(params));

		CloseableHttpResponse res = client.execute(post);
		BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
		StringBuilder resultJsonStr = new StringBuilder();
		String line;

		while ((line = rd.readLine()) != null) {
			resultJsonStr.append(line);
		}

		JSONObject result = new JSONObject(resultJsonStr.toString());
		Integer return_code=null;
		for (String key : result.keySet()) {
//			System.out.format("%s = %s\n", key, result.get(key));
			if(key.equals("return_code")) {
				return_code=(int) result.get(key);
			}
		}
		return return_code;
	}
}