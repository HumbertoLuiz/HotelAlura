package hotel.project.core.exceptions;

import org.springframework.validation.FieldError;

public class IncorrectPasswordException extends ValidatingException {

	private static final long serialVersionUID = 1L;

	public IncorrectPasswordException(String message, FieldError fieldError) {
        super(message, fieldError);
    }
}
