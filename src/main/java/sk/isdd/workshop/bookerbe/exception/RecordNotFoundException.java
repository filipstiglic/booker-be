package sk.isdd.workshop.bookerbe.exception;

/**
 * @Author Filip Stiglic
 */
public class RecordNotFoundException extends RuntimeException{

	private boolean isLogged;

	public RecordNotFoundException(String message) {
		super(message);
		isLogged = true;
	}

	public RecordNotFoundException(String message, boolean isLogged) {
		super(message);
		this.isLogged = isLogged;
	}

	public boolean isLogged() {
		return isLogged;
	}

	public void setLogged(boolean logged) {
		isLogged = logged;
	}
}
