package hotel.project.core.exceptions;

import org.springframework.validation.FieldError;

public class ValidatingException extends RuntimeException {
    
	private static final long serialVersionUID = 1L;
	
	private FieldError fieldError;

    public ValidatingException(String message, FieldError fieldError) {
        super(message);
        this.fieldError = fieldError;
    }

    public FieldError getFieldError() {
        return fieldError;
    }

}