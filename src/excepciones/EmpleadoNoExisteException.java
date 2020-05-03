package excepciones;

public class EmpleadoNoExisteException extends Exception {

	public EmpleadoNoExisteException() {
		super("El empleado no existe");
	}

	public EmpleadoNoExisteException(String message) {
		super(message);
	}
	
}
