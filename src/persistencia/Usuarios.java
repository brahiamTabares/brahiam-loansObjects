package persistencia;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import modelo.Usuario;

@XmlRootElement(name = "usuarios")
@XmlAccessorType (XmlAccessType.FIELD)
public class Usuarios {
	@XmlElement(name = "usuario")
	private List<Usuario> usuarios;
	
	public Usuarios() {
	}
	
	public Usuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	/**
	 * Metodo que permite obtener el valor del atributo usuarios
	 * @return El valor del atributo usuarios
	 */
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	/**
	 * Metodo que permite asignar un valor al atributo usuarios
	 * @param usuarios Valor a ser asignado al atributo usuarios
	 */
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	
}
