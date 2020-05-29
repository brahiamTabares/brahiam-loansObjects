package persistencia;

import modelo.Objeto;

public class ObjectoParserUtil {
	private static final String SEPARADOR = ",";

	public static String toString(Objeto objeto) {
		return objeto.getCodigo() + SEPARADOR + objeto.getNombre() + SEPARADOR + objeto.getDescripcion() + SEPARADOR
				+ objeto.getEstado();
	}

	public static Objeto parse(String texto) {
		// String.split parte una cadena en varias partes (arreglo) donde cada una de
		// las partes corresponde a un segmento de la cadana original
		// 123,sandra,quintero,gaviota
		// crear un arreglo arreglo = { "123","sandra","quintero","gaviota" } 
		String[] arreglo = texto.split(SEPARADOR);
		
		Objeto objeto = new Objeto(arreglo[0],arreglo[1], arreglo[2],arreglo[3], arreglo[4], arreglo[5], arreglo[6], arreglo[7],arreglo[8]);
		return objeto;
}
}
