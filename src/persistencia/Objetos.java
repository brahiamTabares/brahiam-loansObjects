package persistencia;

import java.util.List;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import modelo.Objeto;

//RootElement es lo que a a contener un solo arcivo por concepto (Objeto, objeto, usuario)
//Elemento contenedor de varios elementos individuales

/**
 * El primero (<objetos>) es el root element
 * <objetos>
 * 	<Objeto>...</Objeto>
 *  <Objeto>...</Objeto>
 *  <Objeto>...</Objeto>
 * </objetos> 
 */
@XmlRootElement(name = "objetos")
@XmlAccessorType(XmlAccessType.FIELD)
public class Objetos {
	@XmlElement(name = "objeto")
	private List<Objeto> objetos;

	public Objetos() {
	}

	public Objetos(List<Objeto> objetos) {
		this.objetos = objetos;
	}

	/**
	 * Metodo que permite obtener el valor del atributo objetos
	 * 
	 * @return El valor del atributo objetos
	 */
	public List<Objeto> getObjetos() {
		return objetos;
	}

	/**
	 * Metodo que permite asignar un valor al atributo objetos
	 * 
	 * @param objetos Valor a ser asignado al atributo objetos
	 */
	public void setObjetos(List<Objeto> objetos) {
		this.objetos = objetos;
	}

}
