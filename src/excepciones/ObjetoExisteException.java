package excepciones;

public class ObjetoExisteException extends Exception {

	public ObjetoExisteException() {
		super("El objeto ya existe");
	}

	public ObjetoExisteException(String message) {
		super(message);
	}
	
}
