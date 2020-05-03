package modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "usuario")
@XmlAccessorType(XmlAccessType.FIELD)
public class Usuario {
	private String login;
	private String clave;

	public Usuario() {
	}
	
	public Usuario(String login, String clave) {
		this.login = login;
		this.clave = clave;
	}

	/**
	 * Metodo que permite obtener el valor del atributo login
	 * 
	 * @return El valor del atributo login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Metodo que permite asignar un valor al atributo login
	 * 
	 * @param login Valor a ser asignado al atributo login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Metodo que permite obtener el valor del atributo clave
	 * 
	 * @return El valor del atributo clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * Metodo que permite asignar un valor al atributo clave
	 * 
	 * @param clave Valor a ser asignado al atributo clave
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

}
