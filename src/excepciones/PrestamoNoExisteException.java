package excepciones;

public class PrestamoNoExisteException extends Exception {

	public PrestamoNoExisteException() {
		super("El prestamo no existe");
	}

	public PrestamoNoExisteException(String message) {
		super(message);
	}
	
}
