package dietiEstates.backendDietiEstates.Exception;

public class SQLDataAccessException extends DataAccessException {
	
	public SQLDataAccessException(String message) {
        super(message);
    }

    public SQLDataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
