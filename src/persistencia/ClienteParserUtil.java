package persistencia;

import modelo.Cliente;

public class ClienteParserUtil {
	private static final String SEPARADOR = ",";

	public static String toString(Cliente cliente) {
		return cliente.getCedula() + SEPARADOR + cliente.getNombre() + SEPARADOR + cliente.getApellido() + SEPARADOR
				+ cliente.getDireccion();
	}

	public static Cliente parse(String texto) {
		// String.split parte una cadena en varias partes (arreglo) donde cada una de
		// las partes corresponde a un segmento de la cadana original
		// 123,sandra,quintero,gaviota
		// crear un arreglo arreglo = { "123","sandra","quintero","gaviota" } 
		String[] arreglo = texto.split(SEPARADOR);
		
		Cliente cliente = new Cliente(arreglo[1],arreglo[2], arreglo[0],arreglo[3]);
		return cliente;
	}
}
