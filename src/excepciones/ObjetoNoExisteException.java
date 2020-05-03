package excepciones;

public class ObjetoNoExisteException extends Exception {

	public ObjetoNoExisteException() {
		super("El objeto no existe");
	}

	public ObjetoNoExisteException(String message) {
		super(message);
	}
	
}
