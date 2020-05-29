package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "empleado")
@XmlAccessorType(XmlAccessType.FIELD)
public class Empleado extends Persona implements Serializable {


	private static final long serialVersionUID = 1L;
	private  String tipoEmpleado;
	private List<Prestamo> listaPrestamosAsociados = new ArrayList<>();

	public Empleado() {
		super();
	}

	public Empleado( String nombre,String tipoDocumento, String numeroDocumento, String telefonoResidencia,
			String telefonoCelular, String direccion, String ciudadR, String departamento, String pais,String tipoEmpleado,String email) {
		  
		super(nombre,tipoDocumento, numeroDocumento,  telefonoResidencia, telefonoCelular, direccion, ciudadR,
				departamento, pais, email);
		this.tipoEmpleado=tipoEmpleado;
	}
	public Empleado(String tipoEmpleado) {
		
		this.setTipoEmpleado(tipoEmpleado);
	}

	public List<Prestamo> getListaPrestamosAsociados() {
		return listaPrestamosAsociados;
	}

	public void setListaPrestamosAsociados(List<Prestamo> listaPrestamosAsociados) {
		this.listaPrestamosAsociados = listaPrestamosAsociados;
	}

	public String getTipoEmpleado() {
		return tipoEmpleado;
	}

	public void setTipoEmpleado(String tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
	}

}
