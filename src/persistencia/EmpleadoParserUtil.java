package persistencia;

import modelo.Empleado;

public class EmpleadoParserUtil {
	private static final String SEPARADOR = ",";

	public static String toString(Empleado empleado) {
		return empleado.getCedula() + SEPARADOR + empleado.getNombre() + SEPARADOR + empleado.getApellido() + SEPARADOR
				+ empleado.getDireccion();
	}

	public static Empleado parse(String texto) {
		// String.split parte una cadena en varias partes (arreglo) donde cada una de
		// las partes corresponde a un segmento de la cadana original
		// 123,sandra,quintero,gaviota
		// crear un arreglo arreglo = { "123","sandra","quintero","gaviota" } 
		String[] arreglo = texto.split(SEPARADOR);
		
		Empleado empleado = new Empleado(arreglo[1],arreglo[2], arreglo[0],arreglo[3]);
		return empleado;
	}
}
