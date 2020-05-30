package persistencia;

import modelo.Prestamo;

public class PrestamoParserUtil {
	private static final String SEPARADOR = ",";

	public static String toString(Prestamo prestamo) {
		return prestamo.getCodigo()+ SEPARADOR + prestamo.getEstado() + SEPARADOR
				+ prestamo.getValorPrestamo() + SEPARADOR + prestamo.getFechaPrestamo() + SEPARADOR
				+ prestamo.getFechaEntrega() + SEPARADOR + prestamo.getClienteAsociado().getNumeroDocumento() + SEPARADOR
				+ prestamo.getEmpleadoAsociado().getNumeroDocumento();
	}

	public static Prestamo parse(String texto) {
		// String.split parte una cadena en varias partes (arreglo) donde cada una de
		// las partes corresponde a un segmento de la cadana original
		// 123,sandra,quintero,gaviota
		// crear un arreglo arreglo = { "123","sandra","quintero","gaviota" }
		//String[] arreglo = texto.split(SEPARADOR);

		// Prestamo prestamo = new Prestamo(arreglo[1],arreglo[2],
		// arreglo[0],arreglo[3],arreglo[3],arreglo[3],arreglo[3]);
		return null;
	}
}
