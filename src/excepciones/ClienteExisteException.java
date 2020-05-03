package excepciones;

public class ClienteExisteException extends Exception {

	public ClienteExisteException() {
		super("El cliente ya existe");
	}

	public ClienteExisteException(String message) {
		super(message);
	}
	
}
