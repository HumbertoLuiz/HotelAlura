package hotel.project.web.dtos;


import hotel.project.web.interfaces.IConfirmPassword;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordForm implements IConfirmPassword {

    @NotNull
    @NotEmpty
    private String oldPassword;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String confirmPassword;

}
