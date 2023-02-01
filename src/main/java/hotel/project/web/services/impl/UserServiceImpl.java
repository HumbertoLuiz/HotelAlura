package hotel.project.web.services.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import hotel.project.core.exceptions.IncorrectPasswordException;
import hotel.project.core.exceptions.PasswordDoesntMatchException;
import hotel.project.core.exceptions.UserNotFoundException;
import hotel.project.core.mappers.UserMapper;
import hotel.project.core.models.Role;
import hotel.project.core.models.User;
import hotel.project.core.repository.RoleRepository;
import hotel.project.core.repository.UserRepository;
import hotel.project.core.validators.UserValidator;
import hotel.project.web.dtos.ChangePasswordForm;
import hotel.project.web.dtos.UserDto;
import hotel.project.web.dtos.UserUpdateDto;
import hotel.project.web.interfaces.IConfirmPassword;
import hotel.project.web.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserValidator validator;
	
	
	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User save(UserDto form) {
		validateConfirmPassword((IConfirmPassword) form);

		
		var model = userMapper.toModel(form);

		var passwordHash = passwordEncoder.encode(model.getPassword());

		Set<Role> admin = roleRepository.findByName("ROLE_ADMIN");
		Set<Role> user = roleRepository.findByName("ROLE_USER");
		Set<Role> guest = roleRepository.findByName("ROLE_GUEST");
		
		model.setPassword(passwordHash);
		model.setRoles(admin);
		model.setPassword(passwordHash);
		model.setRoles(user);
		model.setPassword(passwordHash);
		model.setRoles(guest);

		validator.validate(model);

		return userRepository.save(model);
	}

	public User findById(Long id) {
		var message = String.format("User with ID %d not found", id);

		return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(message));
	}

	public User findByEmail(String email) {
		var message = String.format("User with email %s not found", email);

		return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(message));
	}

	public UserUpdateDto findFormById(Long id) {
		var user = findById(id);

		return userMapper.toForm(user);
	}

	public User update(UserUpdateDto form, Long id) {
		var user = findById(id);

		var model = userMapper.toModel(form);
		model.setId(user.getId());
		model.setPassword(user.getPassword());
		model.setRoles(user.getRoles());

		validator.validate(model);

		return userRepository.save(model);
	}

    public void deleteById(Long id) {
        var user = findById(id);
        userRepository.delete(user);
    }

	public void changePassword(ChangePasswordForm form, String email) {
		var user = findByEmail(email);

		validateConfirmPassword(form);

		var currentPassword = user.getPassword();
		var oldPassword = form.getOldPassword();
		var password = form.getPassword();

		if (!passwordEncoder.matches(oldPassword, currentPassword)) {
			var message = "The old password is incorrect";
			var fieldError = new FieldError(form.getClass().getName(), "oldPassword", oldPassword, false, null, null,
					message);

			throw new IncorrectPasswordException(message, fieldError);
		}

		var newPasswordHash = passwordEncoder.encode(password);
		user.setPassword(newPasswordHash);
		userRepository.save(user);
	}

	private void validateConfirmPassword(IConfirmPassword obj) {
		var password = obj.getPassword();
		var confirmPassword = obj.getConfirmPassword();

		if (!password.equals(confirmPassword)) {
			var message = "fields doesn't match";
			var fieldError = new FieldError(obj.getClass().getName(), "confirmPassword", obj.getConfirmPassword(),
					false, null, null, message);

			throw new PasswordDoesntMatchException(message, fieldError);
		}
	}
}