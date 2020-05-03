package excepciones;

public class AutenticacionException extends Exception {

	public AutenticacionException() {
		super("ERROR: Usuario/Clave Incorrectas");
	}

}
