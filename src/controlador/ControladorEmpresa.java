package controlador;

import java.util.Collections;
import java.util.List;

import excepciones.AutenticacionException;
import excepciones.ClienteExisteException;
import excepciones.ClienteNoExisteException;
import excepciones.EmpleadoExisteException;
import excepciones.EmpleadoNoExisteException;
import excepciones.ObjetoExisteException;
import excepciones.ObjetoNoExisteException;
import excepciones.PrestamoExisteException;
import logs.Seguimiento;
import modelo.Cliente;
import modelo.Empleado;
import modelo.Empresa;
import modelo.Objeto;
import modelo.Prestamo;

public class ControladorEmpresa {

	private Empresa empresa;

	private ControladorEmpresa() {

		empresa = new Empresa();
		leerDatos();

	}

	/**
	 * Clase auxiliar para la implementación del patron singleton
	 *
	 */
	private static class SingletonHelper {

		private static final ControladorEmpresa instance = new ControladorEmpresa();
	}

	public static ControladorEmpresa getInstance() {
		return SingletonHelper.instance;

	}

	public Cliente crearCliente(String nombre, String tipoDocumento, String numeroDocumento, String telefonoResidencia,
			String telefonoCelular, String direccion, String ciudadR, String departamento, String pais,
			String profesion, String email) throws ClienteExisteException {

		return empresa.crearCliente(nombre, tipoDocumento, numeroDocumento, telefonoResidencia, telefonoCelular,
				direccion, ciudadR, departamento, pais, profesion, email);

	}

	public void buscarCliente(String cedulaCliente) {

		empresa.buscarCliente(cedulaCliente);

	}

	public Cliente eliminarCliente(String cedulaCliente) throws ClienteNoExisteException {

		return empresa.eliminarCliente(cedulaCliente);

	}

	public void actualizarCliente(String nombre, String tipoDocumento, String numeroDocumento, String telefonoResidencia,
			String telefonoCelular, String direccion, String ciudadR, String departamento, String pais,
			String profesion, String email)
			throws ClienteNoExisteException {

		empresa.actualizarCliente(nombre,tipoDocumento, numeroDocumento,  telefonoResidencia, telefonoCelular, direccion, ciudadR, departamento, pais, email);

	}

	public List<Cliente> listarClientles() {

		return Collections.unmodifiableList(empresa.getListClientes());

	}

	public List<Empleado> listarEmpleados() {

		return Collections.unmodifiableList(empresa.getListaEmpleados());

	}

	public List<Objeto> listarObjetos() {

		return Collections.unmodifiableList(empresa.getListObjetos());

	}

	public Empleado crearEmpleado(String nombre, String tipoDocumento, String numeroDocumento, String telefonoResidencia,
			String telefonoCelular, String direccion, String ciudadR, String departamento, String pais,
			String tipoEmpleado, String email)
			throws EmpleadoExisteException {

		return empresa.crearEmpleado(tipoDocumento, numeroDocumento, nombre, telefonoResidencia, telefonoCelular, direccion, ciudadR, departamento, pais, tipoEmpleado, email);

	}

	public void buscarEmpleado(String cedulaEmpleado) {

		empresa.buscarEmpleado(cedulaEmpleado);

	}

	public Empleado eliminarEmpleado(String cedulaEmpleado) throws EmpleadoNoExisteException {

		return empresa.eliminarEmpleado(cedulaEmpleado);

	}

	public void actualizarEmpleado(String nombre, String tipoDocumento, String numeroDocumento, String telefonoResidencia,
			String telefonoCelular, String direccion, String ciudadR, String departamento, String pais,
			String tipoEmpleado, String email)
			throws EmpleadoNoExisteException {

		empresa.actualizarEmpleado(nombre,tipoDocumento, numeroDocumento, telefonoResidencia, telefonoCelular, direccion, ciudadR, departamento, pais, email);

	}

	public List<Empleado> listarEmpleado() {

		return Collections.unmodifiableList(empresa.getListaEmpleados());

	}

	public Objeto crearObjeto(String nombre, String descripcion, String codigo, String estado)
			throws ObjetoExisteException {

		return empresa.crearObjeto(nombre, descripcion, codigo, estado);
	}

	public void buscarObjeto(String codigo) {

		empresa.buscarObjeto(codigo);

	}

	public Objeto eliminarObjeto(String codigo) throws ObjetoNoExisteException {

		return empresa.eliminarObjeto(codigo);

	}

	public void actualizarObjeto(String codigo, String nombre, String descripcion, String estado)
			throws ObjetoNoExisteException {

		empresa.actualizarObjeto(codigo, nombre, descripcion, estado);

	}

	public Prestamo crearPrestamon(int numero, String fechaPrestamo, String fechaEntrega, int valorPrestamo,
			String estado, Cliente cliente, Empleado empleado, List<Objeto> listaObjetosPrestar)
			throws PrestamoExisteException {

		return empresa.crearPrestamo(numero, fechaPrestamo, fechaEntrega, valorPrestamo, estado, cliente, empleado,
				listaObjetosPrestar);

	}

	public List<Objeto> getListaObjetosDisponibles() {
		return empresa.getListaObjetosDisponibles();
	}

	public List<Prestamo> listarPrestamos() {
		return Collections.unmodifiableList(empresa.getListPrestamos());
	}

	public boolean autenticar(String login, String password) throws AutenticacionException {
		Seguimiento.getInstance().log("Ingreso al sistema " + login);
		return empresa.autenticar(login, password);
	}

	public void guardarDatos() {
		System.out.println("guardando datos");
		empresa.guardarUsuarios();
		empresa.guardarClientes();
		empresa.guardarEmpleados();
		empresa.guardarObjetos();
		empresa.guardarPrestamos();
	}

	private void leerDatos() {
		empresa.leerUsuarios();
		empresa.leerClientes();
		empresa.leerEmpleados();
		empresa.leerObjetos();
		empresa.leerPrestamos();
	}

}
