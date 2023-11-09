package DoAn.B19DCCN445.EMarket.dto;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonFormat;

import DoAn.B19DCCN445.EMarket.model.Cart_detail;
import DoAn.B19DCCN445.EMarket.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CartDTO {
	List<Cart_detail> cart_details;
}
