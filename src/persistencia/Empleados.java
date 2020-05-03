package persistencia;

import java.util.List;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import modelo.Empleado;

//RootElement es lo que a a contener un solo arcivo por concepto (Empleado, objeto, usuario)
//Elemento contenedor de varios elementos individuales

/**
 * El primero (<empleados>) es el root element
 * <empleados>
 * 	<Empleado>...</Empleado>
 *  <Empleado>...</Empleado>
 *  <Empleado>...</Empleado>
 * </empleados> 
 */
@XmlRootElement(name = "empleados")
@XmlAccessorType(XmlAccessType.FIELD)
public class Empleados {
	@XmlElement(name = "empleado")
	private List<Empleado> empleados;

	public Empleados() {
	}

	public Empleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	/**
	 * Metodo que permite obtener el valor del atributo empleados
	 * 
	 * @return El valor del atributo empleados
	 */
	public List<Empleado> getEmpleados() {
		return empleados;
	}

	/**
	 * Metodo que permite asignar un valor al atributo empleados
	 * 
	 * @param empleados Valor a ser asignado al atributo empleados
	 */
	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

}
