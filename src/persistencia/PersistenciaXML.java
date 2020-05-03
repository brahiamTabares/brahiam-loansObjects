package persistencia;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class PersistenciaXML {
	public static Object leer(String nombreArchivo,Class<?> clase) {
		try {
			JAXBContext contexto = JAXBContext.newInstance(clase);  // establece el ambiente de trabajo a partir de una clase

			Unmarshaller deserializacion = contexto.createUnmarshaller(); // unmarsaller convierte de xml a objeto

			return deserializacion.unmarshal(new File(nombreArchivo));

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void guardar(Object p,String nombreArchivo) {
		//CopiasUtil.crearCopia(nombreArchivo);
		try {
			JAXBContext contexto = JAXBContext.newInstance(p.getClass());
			Marshaller serializador = contexto.createMarshaller(); // convierte de objeto a xml
			serializador.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			serializador.marshal(p, new File(nombreArchivo));

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
