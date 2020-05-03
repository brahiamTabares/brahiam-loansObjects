package persistencia;

import java.io.FileWriter;
import java.util.List;

import modelo.Cliente;
import modelo.Empleado;
import modelo.Objeto;
import modelo.Prestamo;

public class PersistenciaComas {
	private static final String CARPETA_DATOS = "datos/";
	private static final String CLIENTE_COMA = CARPETA_DATOS + "cliente.cvs";
	private static final String EMPLEADO_COMA = CARPETA_DATOS + "empelado.cvs";
	private static final String OBJETO_COMA = CARPETA_DATOS + "objeto.cvs";
	private static final String PRESTAMO_COMA = CARPETA_DATOS + "prestamo.cvs";

	public static void guardarClientes(List<Cliente> clientes) {
		try (FileWriter archivo = new FileWriter(CLIENTE_COMA)) {
			for (Cliente cliente : clientes) {
				archivo.write(ClienteParserUtil.toString(cliente) + "\n");
			}
		} catch (Exception e) {
		}
	}

	public static void guardarObjetos(List<Objeto> objetos) {
		try (FileWriter archivo = new FileWriter(OBJETO_COMA)) {
			for (Objeto objeto : objetos) {
				archivo.write(ObjectoParserUtil.toString(objeto) + "\n");
			}
		} catch (Exception e) {
		}
	}

	public static void guardarEmpleados(List<Empleado> empleados) {
		try (FileWriter archivo = new FileWriter(EMPLEADO_COMA)) {
			for (Empleado empleado : empleados) {
				archivo.write(EmpleadoParserUtil.toString(empleado) + "\n");
			}
		} catch (Exception e) {
		}
	}

	public static void guardarPrestamo(List<Prestamo> prestamos) {
		CopiasUtil.crearCopia(PRESTAMO_COMA);
		try (FileWriter archivo = new FileWriter(PRESTAMO_COMA)) {
			for (Prestamo prestamo : prestamos) {
				archivo.write(PrestamoParserUtil.toString(prestamo) + "\n");
			}
		} catch (Exception e) {
		}
	}

}
