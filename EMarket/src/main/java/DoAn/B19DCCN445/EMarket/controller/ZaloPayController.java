package DoAn.B19DCCN445.EMarket.controller;

import org.json.JSONObject; // https://mvnrepository.com/artifact/org.json/json
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.util.logging.Logger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DoAn.B19DCCN445.EMarket.common.ApiResponse;
import DoAn.B19DCCN445.EMarket.model.Order;
import DoAn.B19DCCN445.EMarket.service.OrderService;

@RestController
@RequestMapping("/api/zaloPay")
public class ZaloPayController {
	private Logger logger = Logger.getLogger(this.getClass().getName());
	private String key2 = "eG4r0GcoNtRGbO8";
	private Mac HmacSHA256;
	@Autowired
	private OrderService service;

	public ZaloPayController() throws Exception {
		HmacSHA256 = Mac.getInstance("HmacSHA256");
		HmacSHA256.init(new SecretKeySpec(key2.getBytes(), "HmacSHA256"));
	}

	@PostMapping("/callback")
	public String callback(@RequestBody String jsonStr) {
		JSONObject result = new JSONObject();
		System.out.println("call callBack");
		try {
			JSONObject cbdata = new JSONObject(jsonStr);
			String dataStr = cbdata.getString("data");
			String reqMac = cbdata.getString("mac");

			byte[] hashBytes = HmacSHA256.doFinal(dataStr.getBytes());
			String mac = DatatypeConverter.printHexBinary(hashBytes).toLowerCase();

			// kiểm tra callback hợp lệ (đến từ ZaloPay server)
			if (!reqMac.equals(mac)) {
				// callback không hợp lệ
				result.put("return_code", -1);
				result.put("return_message", "mac not equal");
			} else {
				// thanh toán thành công
				// merchant cập nhật trạng thái cho đơn hàng
				JSONObject data = new JSONObject(dataStr);
				logger.info("update order's status = success where app_trans_id = " + data.getString("app_trans_id"));

				result.put("return_code", 1000);
				result.put("return_message", "success");
			}
		} catch (Exception ex) {
			result.put("return_code", 0); // ZaloPay server sẽ callback lại (tối đa 3 lần)
			result.put("return_message", ex.getMessage());
		}

		// thông báo kết quả cho ZaloPay server
		return "-1";
//		return result.toString();
		
	}
}
