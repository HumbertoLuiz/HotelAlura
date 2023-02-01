package hotel.project.core.exceptions;

public class TokenNaBlackListException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TokenNaBlackListException() {
        super("The given token is not valid");
    }

    public TokenNaBlackListException(String message) {
        super(message);
    }

}

