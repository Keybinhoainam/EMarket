package DoAn.B19DCCN445.EMarket.common;

import DoAn.B19DCCN445.EMarket.model.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
	private String accessToken;
	private Store store;
}
