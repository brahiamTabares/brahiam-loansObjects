package excepciones;

public class ClienteNoExisteException extends Exception {

	public ClienteNoExisteException() {
		super("El cliente no existe");
	}

	public ClienteNoExisteException(String message) {
		super(message);
	}
	
}
