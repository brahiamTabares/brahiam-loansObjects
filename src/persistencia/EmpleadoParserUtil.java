package persistencia;

import modelo.Empleado;

public class EmpleadoParserUtil {
	private static final String SEPARADOR = ",";

	public static String toString(Empleado empleado) {
		return empleado.getNombre() + SEPARADOR + empleado.getTipoDocumento() + SEPARADOR + empleado.getNumeroDocumento()
		+ SEPARADOR + empleado.getTelefonoResidencia()+ SEPARADOR +empleado.getTelefonoCelular()+ SEPARADOR +
		empleado.getDireccion()+ SEPARADOR + empleado.getCiudadR()+ SEPARADOR +empleado.getDepartamento()+ SEPARADOR +
		empleado.getPais()+ SEPARADOR +empleado.getTipoEmpleado()+ SEPARADOR +empleado.getEmail();
	}

	public static Empleado parse(String texto) {
		// String.split parte una cadena en varias partes (arreglo) donde cada una de
		// las partes corresponde a un segmento de la cadana original
		// 123,sandra,quintero,gaviota
		// crear un arreglo arreglo = { "123","sandra","quintero","gaviota" } 
		String[] arreglo = texto.split(SEPARADOR);
		
		Empleado empleado = new Empleado(arreglo[0],arreglo[1], arreglo[2],arreglo[3], arreglo[4], arreglo[5], arreglo[6], arreglo[7],
				arreglo[8], arreglo[9], arreglo[10]);
		return empleado;
	}
}
