package persistencia;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import modelo.Cliente;

//RootElement es lo que a a contener un solo arcivo por concepto (cliente, objeto, usuario)
//Elemento contenedor de varios elementos individuales


@XmlRootElement(name = "clientes")
@XmlAccessorType(XmlAccessType.FIELD)
public class Clientes {
	@XmlElement(name = "cliente")
	private List<Cliente> clientes;

	public Clientes() {
	}
	public Clientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	/**
	 * Metodo que permite obtener el valor del atributo clientes
	 * 
	 * @return El valor del atributo clientes
	 */
	public List<Cliente> getClientes() {
		return clientes;
	}

	/**
	 * Metodo que permite asignar un valor al atributo clientes
	 * 
	 * @param clientes Valor a ser asignado al atributo clientes
	 */
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

}
