package DoAn.B19DCCN445.EMarket.dto;

import java.util.Date;

import DoAn.B19DCCN445.EMarket.model.Product;
import DoAn.B19DCCN445.EMarket.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDetailDTO {
	
	private Long id;
	private int quantity;
	private Date create_at;
	private Date update_at;
	
	
	private User user;
	private Product product;
}
