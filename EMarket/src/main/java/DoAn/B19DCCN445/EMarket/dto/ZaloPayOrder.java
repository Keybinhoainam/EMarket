package DoAn.B19DCCN445.EMarket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ZaloPayOrder {
	private int app_id;
	private String app_user;
	private Long app_time;
	private String app_trans_id;
	private Double amount;
	private String bank_code;
	private String embed_data;
	private String item;
	private String callback_url;
	private String description;
	private String mac;

}
