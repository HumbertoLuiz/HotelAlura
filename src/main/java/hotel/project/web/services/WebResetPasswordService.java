package hotel.project.web.services;

import hotel.project.web.dtos.ResetConfirmPasswordForm;
import jakarta.validation.Valid;

public interface WebResetPasswordService {

	void confirmResetPassword(String token, @Valid ResetConfirmPasswordForm form);

}
