package hotel.project.web.services;

import java.util.List;

import hotel.project.core.models.User;
import hotel.project.web.dtos.ChangePasswordForm;
import hotel.project.web.dtos.UserDto;
import hotel.project.web.dtos.UserUpdateDto;

public interface UserService {

	List<User> findAll();

	User save(UserDto form);

	User findById(Long id);

	User update(UserUpdateDto updateForm, Long id);

	void deleteById(Long id);

	void changePassword(ChangePasswordForm changePasswordForm, String email);
}
