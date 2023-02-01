package hotel.project.token.exception;

public class TokenServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TokenServiceException() {}

    public TokenServiceException(String message) {
        super(message);
    }

}