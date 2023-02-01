 package hotel.project.web.dtos;

import java.util.Set;

import hotel.project.core.models.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {

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
	private Set<Role> roles;
    
}