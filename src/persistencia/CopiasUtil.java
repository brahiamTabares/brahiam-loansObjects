package persistencia;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Crea una copia de un archivo existente en la carpeta de copias anteponiendo
 * la fecha y hora al nombre del archivo copiado
 *
 */
public class CopiasUtil {
	private static final String CARPETA_DATOS = "datos/";
	private static final String COPIAS = CARPETA_DATOS+"copias";

	public static void crearCopia(String nombreArchivo) {
		File file = new File(nombreArchivo);
		// Formato de fecha añomesdiahoramilietarminutos
		DateFormat formato = new SimpleDateFormat("yyyyMMddHHmm");
		String fechaHoraMinutos = formato.format(new Date());
		String nuevoNombre = fechaHoraMinutos + "-" + file.getName();
		
		try {
			Files.copy(file.toPath(),
					new File(COPIAS + "/" + nuevoNombre).toPath(),
					StandardCopyOption.REPLACE_EXISTING); //si el archivo ya existe entonces que lo reemplace
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
