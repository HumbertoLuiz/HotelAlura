package hotel.project.web.dtos;

import java.util.Set;

import hotel.project.core.models.Role;
import hotel.project.web.interfaces.IConfirmPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements IConfirmPassword {

	@NotNull
	@Size(min = 3, max = 255)
	private String firstName;
	
	@NotNull
	@Size(min = 3, max = 255)
	private String lastName;

	@NotNull
	@Size(min = 3, max = 255)
	@Email
	private String email;

	@NotNull
	@NotEmpty
	private String password;

	@NotNull
	@NotEmpty
	private String confirmPassword;

	@NotNull
	private Set<Role> roles;

}