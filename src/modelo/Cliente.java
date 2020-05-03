package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cliente")
@XmlAccessorType(XmlAccessType.FIELD)
//prueba

public class Cliente extends Persona implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private List<Prestamo> listaPrestamosAsociados = new ArrayList<>();
	
	public Cliente() {
		super();
	}

	public Cliente(String nombre, String apellido, String cedula, String direccion) {
		super(nombre, apellido, cedula, direccion);
	}

	public Cliente(String nombre, String apellido, String cedula) {
		super(nombre, apellido, cedula);
	}
	
	
	public List<Prestamo> getListaPrestamosAsociados() {
		return listaPrestamosAsociados;
	}


	public void setListaPrestamosAsociados(List<Prestamo> listaPrestamosAsociados) {
		this.listaPrestamosAsociados = listaPrestamosAsociados;
	}
	


}
