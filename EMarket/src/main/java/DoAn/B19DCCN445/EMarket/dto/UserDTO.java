package DoAn.B19DCCN445.EMarket.dto;

import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class UserDTO {
	private Long id;
//	@NotBlank
//	@NotNull(message = "username shouldn't be null")
	private String username;
//	@NotNull(message = "password shouldn't be null")
//	@NotBlank
//	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", 
//			message = "password should use 8 or more character with a mix of letters, numbers & symbols.")
	private String password;
//	@NotBlank
//	@NotNull(message = "email shouldn't be null")
	private String email;
//	@NotBlank
//	@NotNull(message = "fullname shouldn't be null")
	private String fullname;
//	@NotBlank
//	@NotNull(message = "gender shouldn't be null")
	private String gender;
//	@NotNull(message = "dateOfBirth shouldn't be null")
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date birthday;
//	private MultipartFile avatar;
	
	
//	@NotNull(message = "phone shouldn't be null")
//	@NotBlank
//	@Pattern(regexp = "[0-9]{10}")
	private String phone;
	
	private String accessToken;
}
