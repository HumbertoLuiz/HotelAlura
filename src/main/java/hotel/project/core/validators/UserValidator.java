package hotel.project.core.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import hotel.project.core.exceptions.UserAlreadyRegisteredException;
import hotel.project.core.models.User;
import hotel.project.core.repository.UserRepository;

@Component
public class UserValidator {

    @Autowired
    private UserRepository userRepository;
    

    public void validate(User user) {
        validateEmail(user);
    }

    private void validateEmail(User user) {
        if (userRepository.isEmailAlreadyRegistered(user)) {
            var message = "User Registered Already exists with this e-mail";
            var fieldError = new FieldError(user.getClass().getName(), "email", user.getEmail(), false, null, null, message);

            throw new UserAlreadyRegisteredException(message, fieldError);
        }
    }

}