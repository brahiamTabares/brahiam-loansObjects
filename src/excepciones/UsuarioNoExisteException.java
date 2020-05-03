package excepciones;

public class UsuarioNoExisteException extends Exception {

	public UsuarioNoExisteException() {
		super("El usuario no existe");
	}

	public UsuarioNoExisteException(String message) {
		super(message);
	}
	
}
