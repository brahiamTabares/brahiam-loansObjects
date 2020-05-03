package excepciones;

public class EmpleadoExisteException extends Exception {

	public EmpleadoExisteException() {
		super("El empleado ya existe");
	}

	public EmpleadoExisteException(String message) {
		super(message);
	}
	
}
