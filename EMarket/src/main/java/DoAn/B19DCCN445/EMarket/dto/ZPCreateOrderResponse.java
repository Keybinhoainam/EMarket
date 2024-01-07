package DoAn.B19DCCN445.EMarket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ZPCreateOrderResponse {
	private int return_code;
	private String return_message;
	private int  sub_return_code;
	private String sub_return_message;
	private String order_url;
	private String zp_trans_token;
	private String order_token;
	private String qr_code;
}
