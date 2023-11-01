package DoAn.B19DCCN445.EMarket.common;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {
	private boolean success;
	private String message;
	
	public String getTimestamp() {
		return LocalDateTime.now().toString();
	}
}