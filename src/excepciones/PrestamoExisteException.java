package excepciones;

public class PrestamoExisteException extends Exception {

	public PrestamoExisteException() {
		super("El prestamo ya existe");
	}

	public PrestamoExisteException(String message) {
		super(message);
	}
	
}
