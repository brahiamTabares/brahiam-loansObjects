package modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "persona")
@XmlAccessorType(XmlAccessType.FIELD)
public class Persona {

	private static final String TIPO_CONTRASENIA="Contraseña de identidad";
    private static  final String TIPO_CEDULA="Cedula de ciudadania ";
    private static  final String TIPO_TARJETA="Tarjeta de identidad"; 
    private static final  String TIPO_EXTRANJERA= "Cedula extranjera";

	private String tipoDocumento;
	private String numeroDocumento;
	private String nombre;
	private String telefonoResidencia;
	private String telefonoCelular;
	private String direccion;
	private String ciudadR;
	private String Departamento;
	private String pais;
	private String email;

	public Persona() {

	}
	public Persona(String nombre,String tipoDocumento, String numeroDocumento, String telefonoResidencia,
			String telefonoCelular, String direccion, String ciudadR, String departamento, String pais, String email) {
		super();
		this.nombre = nombre;
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.telefonoResidencia = telefonoResidencia;
		this.telefonoCelular = telefonoCelular;
		this.direccion = direccion;
		this.ciudadR = ciudadR;
		Departamento = departamento;
		this.pais = pais;
		
		this.email = email;
	}
	//hola


	/*
	 * (non-Javadoc) * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return numeroDocumento + " - " + nombre;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefonoResidencia() {
		return telefonoResidencia;
	}

	public void setTelefonoResidencia(String telefonoResidencia) {
		this.telefonoResidencia = telefonoResidencia;
	}

	public String getTelefonoCelular() {
		return telefonoCelular;
	}

	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudadR() {
		return ciudadR;
	}

	public void setCiudadR(String ciudadR) {
		this.ciudadR = ciudadR;
	}

	public String getDepartamento() {
		return Departamento;
	}

	public void setDepartamento(String departamento) {
		Departamento = departamento;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static String getTipoContrasenia() {
		return TIPO_CONTRASENIA;
	}
	public static String getTipoCedula() {
		return TIPO_CEDULA;
	}
	public static String getTipoTarjeta() {
		return TIPO_TARJETA;
	}
	public static String getTipoExtranjera() {
		return TIPO_EXTRANJERA;
	}



}
