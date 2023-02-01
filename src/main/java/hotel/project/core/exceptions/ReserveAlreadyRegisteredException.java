package hotel.project.core.exceptions;

import org.springframework.validation.FieldError;

public class ReserveAlreadyRegisteredException extends ValidatingException {

	private static final long serialVersionUID = 1L;

	public ReserveAlreadyRegisteredException(String message, FieldError fieldError) {
		super(message, fieldError);
	}

}
