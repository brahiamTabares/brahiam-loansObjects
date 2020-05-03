package excepciones;

public class UsuarioExisteException extends Exception {

	public UsuarioExisteException() {
		super("El usuario ya existe");
	}

	public UsuarioExisteException(String message) {
		super(message);
	}
	
}
