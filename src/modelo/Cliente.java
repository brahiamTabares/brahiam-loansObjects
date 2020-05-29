package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cliente")
@XmlAccessorType(XmlAccessType.FIELD)


public class Cliente extends Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Prestamo> listaPrestamosAsociados = new ArrayList<>();
	private String profesion;
	public Cliente() {
		super();
	}

	public Cliente(String tipoDocumento, String numeroDocumento, String nombre, String telefonoResidencia,
			String telefonoCelular, String direccion, String ciudadR, String departamento, String pais,String profesion, String email) {
		
		super(tipoDocumento, numeroDocumento, nombre, telefonoResidencia, telefonoCelular, direccion, ciudadR,
				departamento, pais,email);
		this.profesion=profesion;
	}

	public List<Prestamo> getListaPrestamosAsociados() {
		return listaPrestamosAsociados;
	}

	public void setListaPrestamosAsociados(List<Prestamo> listaPrestamosAsociados) {
		this.listaPrestamosAsociados = listaPrestamosAsociados;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}
	


}
