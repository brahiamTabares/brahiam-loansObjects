package persistencia;

import modelo.Cliente;

public class ClienteParserUtil {
	private static final String SEPARADOR = ",";

	public static String toString(Cliente cliente) {
		
		return cliente.getNombre() + SEPARADOR + cliente.getTipoDocumento() + SEPARADOR + cliente.getNumeroDocumento()
				+ SEPARADOR + cliente.getTelefonoResidencia()+ SEPARADOR +cliente.getTelefonoCelular()+ SEPARADOR +
				cliente.getDireccion()+ SEPARADOR + cliente.getCiudadR()+ SEPARADOR +cliente.getDepartamento()+ SEPARADOR +
				cliente.getPais()+ SEPARADOR +cliente.getProfesion()+ SEPARADOR +cliente.getEmail();
				

	}

	public static Cliente parse(String texto) {
		// String.split parte una cadena en varias partes (arreglo) donde cada una de
		// las partes corresponde a un segmento de la cadana original
		// 123,sandra,quintero,gaviota
		// crear un arreglo arreglo = { "123","sandra","quintero","gaviota" } 
		String[] arreglo = texto.split(SEPARADOR);
		
		Cliente cliente = new Cliente(arreglo[0],arreglo[1], arreglo[2],arreglo[3], arreglo[4], arreglo[5], arreglo[6], arreglo[7],
				arreglo[8], arreglo[9], arreglo[10]);
		return cliente;
	}
}
