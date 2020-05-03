package persistencia;

import java.util.List;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import modelo.Prestamo;

//RootElement es lo que a a contener un solo arcivo por concepto (Prestamo, Prestamo, usuario)
//Elemento contenedor de varios elementos individuales

/**
 * El primero (<prestamos>) es el root element
 * <prestamos>
 * 	<Prestamo>...</Prestamo>
 *  <Prestamo>...</Prestamo>
 *  <Prestamo>...</Prestamo>
 * </prestamos> 
 */
@XmlRootElement(name = "prestamos")
@XmlAccessorType(XmlAccessType.FIELD)
public class Prestamos {
	@XmlElement(name = "prestamo")
	private List<Prestamo> prestamos;

	public Prestamos() {
	}

	public Prestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}

	/**
	 * Metodo que permite obtener el valor del atributo prestamos
	 * 
	 * @return El valor del atributo prestamos
	 */
	public List<Prestamo> getPrestamos() {
		return prestamos;
	}

	/**
	 * Metodo que permite asignar un valor al atributo prestamos
	 * 
	 * @param prestamos Valor a ser asignado al atributo prestamos
	 */
	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}

}
