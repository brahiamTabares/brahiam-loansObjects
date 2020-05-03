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
	// prueba

	private static final long serialVersionUID = 1L;
	private List<Prestamo> listaPrestamosAsociados = new ArrayList<>();

	public Empleado() {
		super();
	}

	public Empleado(String nombre, String apellido, String cedula, String direccion) {
		super(nombre, apellido, cedula, direccion);
	}

	public Empleado(String nombre, String apellido, String cedula) {
		super(nombre, apellido, cedula);

		// ClaseUnica.getInstancia().
	}

	public List<Prestamo> getListaPrestamosAsociados() {
		return listaPrestamosAsociados;
	}

	public void setListaPrestamosAsociados(List<Prestamo> listaPrestamosAsociados) {
		this.listaPrestamosAsociados = listaPrestamosAsociados;
	}

}
