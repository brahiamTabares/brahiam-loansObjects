package modelo;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import excepciones.AutenticacionException;
import excepciones.ClienteExisteException;
import excepciones.ClienteNoExisteException;
import excepciones.EmpleadoExisteException;
import excepciones.EmpleadoNoExisteException;
import excepciones.ObjetoExisteException;
import excepciones.ObjetoNoExisteException;
import excepciones.PrestamoExisteException;
import excepciones.PrestamoNoExisteException;
import logs.Seguimiento;
import persistencia.Clientes;
import persistencia.CopiasUtil;
import persistencia.Empleados;
import persistencia.Objetos;
import persistencia.PersistenciaComas;
import persistencia.PersistenciaXML;
import persistencia.Prestamos;
import persistencia.Usuarios;
//Papichulo
public class Empresa implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String CARPETA_DATOS = "datos/";
	
	private static final String ARCHIVO_USUARIOS = CARPETA_DATOS+"usuarios.xml";

	private static final String ARCHIVO_CLIENTES = CARPETA_DATOS+"clientes.xml";

	private static final String ARCHIVO_EMPLEADOS = CARPETA_DATOS+"empleados.xml";

	private static final String ARCHIVO_OBJETOS = CARPETA_DATOS+"objetos.xml";

	private static final String ARCHIVO_PRESTAMOS = CARPETA_DATOS+"prestamos.xml";


	private List<Cliente> listClientes = new ArrayList<Cliente>();
	private List<Empleado> listaEmpleados = new ArrayList<Empleado>();
	private List<Objeto> listObjetos = new ArrayList<Objeto>();
	private List<Prestamo> listPrestamos = new ArrayList<Prestamo>();
	private List<Objeto> listaObjetosAPrestar = new ArrayList<>();
	private List<Objeto> listaObjetosDisponibles = new ArrayList<>();
	private List<Usuario> listaUsuario = new ArrayList<Usuario>();

	private transient Usuario usuarioAutenticado;// transient es un modificador que indica que esa variable no debe ser
													// guardado

	public Usuario crearUsuario(String login, String clave) {
		Usuario nuevo = buscarUsuario(login);
		if (nuevo == null) {
			Seguimiento.getInstance().log("Usuario " + usuarioAutenticado.getLogin() + ", creó un usuario ");
			nuevo = new Usuario(login, clave);
			listaUsuario.add(nuevo);
		}
		return nuevo;
	}

	// Crear
	public Cliente crearCliente(String nombre, String apellido, String cedula, String direccion) throws ClienteExisteException {
		Seguimiento.getInstance().log("Usuario " + usuarioAutenticado.getLogin() + ", creó un cliente ");
		if( buscarCliente(cedula) != null  ) {
			throw new ClienteExisteException("ERROR: Ya existe un cliente con cédula "+cedula);
		}
		Cliente nuevoCliente = new Cliente(nombre, apellido, cedula, direccion);
		listClientes.add(nuevoCliente);
		return nuevoCliente;
	}

	public Empleado crearEmpleado(String nombre, String apellido, String cedula, String direccion) throws EmpleadoExisteException {
		Seguimiento.getInstance().log("Usuario " + usuarioAutenticado.getLogin() + ", creó un empleado ");
		if( buscarEmpleado(cedula) != null  ) {
			throw new EmpleadoExisteException("ERROR: Ya existe un empleado con cédula "+cedula);
		}
		Empleado nuevoEmpleado = new Empleado(nombre, apellido, cedula, direccion);
		listaEmpleados.add(nuevoEmpleado);
		return nuevoEmpleado;

	}

	public Objeto crearObjeto(String nombre, String descripcion, String codigo, String estado) throws ObjetoExisteException {
		Seguimiento.getInstance().log("Usuario " + usuarioAutenticado.getLogin() + ", creó un objeto ");
		if( buscarObjeto(codigo) != null  ) {
			throw new ObjetoExisteException("ERROR: Ya existe un objeto con código "+codigo);
		}
		Objeto nuevoObjeto = new Objeto(nombre, descripcion, codigo, estado);
		listObjetos.add(nuevoObjeto);
		return nuevoObjeto;

	}
	
	public Prestamo crearPrestamo(int numero, String fechaPrestamo, String fechaEntrega, int valorPrestamo,
			String estado, Cliente clienteAsociado, Empleado empleadoAsociado, List<Objeto> listaObjetosPrestar) throws PrestamoExisteException {

		Seguimiento.getInstance().log("Usuario " + usuarioAutenticado.getLogin() + ", creó un préstamo");
		if( buscarPrestamo(numero) != null  ) {
			throw new PrestamoExisteException("ERROR: Ya existe un préstamo con el número "+numero);
		}
		Prestamo nuevoPrestamo = new Prestamo(numero, fechaPrestamo, fechaEntrega, null, valorPrestamo, estado,
				empleadoAsociado, clienteAsociado, listaObjetosPrestar);

		for (Objeto objeto : listaObjetosPrestar) {
			objeto.setEstado(Objeto.ESTADO_PRESTADO);
		}

		getListPrestamos().add(nuevoPrestamo);

		getListaObjetosAPrestar().clear();

		return nuevoPrestamo;
	}
	
	
	// buscar
	public Empleado buscarEmpleado(String cedulaEmpleado) {
		Seguimiento.getInstance()
				.log("Usuario " + usuarioAutenticado.getLogin() + ", busco el empleado con cédula " + cedulaEmpleado);
		for (int i = 0; i < listaEmpleados.size(); i++) {
			if (listaEmpleados.get(i).getCedula().equals(cedulaEmpleado)) {
				return listaEmpleados.get(i);
			}
		}
		return null;
	}

	public Cliente buscarCliente(String cedulaCliente) {
		Seguimiento.getInstance()
				.log("Usuario " + usuarioAutenticado.getLogin() + ", buscó el cliente con cédula " + cedulaCliente);
		for (int i = 0; i < listClientes.size(); i++) {
			if (listClientes.get(i).getCedula().equals(cedulaCliente)) {
				return listClientes.get(i);
			}
		}
		return null;
	}

	public Objeto buscarObjeto(String codigo) {
		Seguimiento.getInstance()
				.log("Usuario " + usuarioAutenticado.getLogin() + ", buscó el objeto con código " + codigo);
		for (int i = 0; i < listObjetos.size(); i++) {
			if (listObjetos.get(i).getCodigo().equals(codigo)) {
				return listObjetos.get(i);
			}
		}
		return null;
	}

	public Prestamo buscarPrestamo(int numero) {
		Seguimiento.getInstance()
				.log("Usuario " + usuarioAutenticado.getLogin() + ", buscó el Préstamo con código " + numero);
		for (int i = 0; i < listPrestamos.size(); i++) {
			if (listPrestamos.get(i).getNumero().equals(numero)) {
				return listPrestamos.get(i);
			}
		}
		return null;
	}
	
	
	// eliminar
	public Cliente eliminarCliente(String cedulaCliente) throws ClienteNoExisteException {

		Cliente clienteEliminar = buscarCliente(cedulaCliente);

		if (clienteEliminar != null) {
			Seguimiento.getInstance().log(
					"Usuario " + usuarioAutenticado.getLogin() + ", eliminó el cliente con cédula " + cedulaCliente);
			getListClientes().remove(clienteEliminar);
			return clienteEliminar;
		} 
		throw new ClienteNoExisteException("ERROR: No existe un cliente con cédula "+cedulaCliente);
	}

	public Empleado eliminarEmpleado(String cedulaEmpleado) throws EmpleadoNoExisteException {

		Empleado empleadoEliminar = buscarEmpleado(cedulaEmpleado);

		if (empleadoEliminar != null) {
			Seguimiento.getInstance().log(
					"Usuario " + usuarioAutenticado.getLogin() + ", eliminó el empleado con cédula " + cedulaEmpleado);
			getListaEmpleados().remove(empleadoEliminar);
			return empleadoEliminar;
		} 
		
		throw new EmpleadoNoExisteException("ERROR: No existe un empleado con cédula "+cedulaEmpleado);

	}

	public Objeto eliminarObjeto(String codigo) throws ObjetoNoExisteException {
		Objeto objetoEliminar = buscarObjeto(codigo);

		if (objetoEliminar != null) {
			Seguimiento.getInstance()
					.log("Usuario " + usuarioAutenticado.getLogin() + ", eliminó el objeto con código " + codigo);
			getListObjetos().remove(objetoEliminar);
			return objetoEliminar;
		} 
		
		throw new ObjetoNoExisteException("ERROR: No existe un objeto con código "+codigo);

	}
	
	public Prestamo eliminarPrestamo(int numero) throws PrestamoNoExisteException {
		Prestamo prestamoEliminar = buscarPrestamo(numero);

		if (prestamoEliminar != null) {
			Seguimiento.getInstance()
					.log("Usuario " + usuarioAutenticado.getLogin() + ", eliminó el préstamo con número " + numero);
			getListPrestamos().remove(prestamoEliminar);
			return prestamoEliminar;
		} 
		throw new PrestamoNoExisteException("ERROR: No existe un préstamo con número "+numero);
	}

	// Actualizar
	public boolean actualizarCliente(String cedulaCliente, String nombre, String apellido, String direccion) throws ClienteNoExisteException {
		Cliente cliente = buscarCliente(cedulaCliente);

		if (cliente == null) {
			throw new ClienteNoExisteException("ERROR: No existe un cliente con número de cédula"+cedulaCliente);
		}

		Seguimiento.getInstance().log("Usuario " + usuarioAutenticado.getLogin() + ", actualizó un cliente");
		cliente.setNombre(nombre);
		cliente.setApellido(apellido);
		cliente.setDireccion(direccion);
		return true;
	}

	public boolean actualizarEmpleado(String cedulaEmpleado, String nombre, String apellido, String direccion) throws EmpleadoNoExisteException {
		Empleado empleado = buscarEmpleado(cedulaEmpleado);

		if (empleado == null) {
			throw new EmpleadoNoExisteException("ERROR: No existe un empleado con número de cédula"+cedulaEmpleado);
		}
		Seguimiento.getInstance().log("Usuario " + usuarioAutenticado.getLogin() + ", actualizó un empleado");
		empleado.setNombre(nombre);
		empleado.setApellido(apellido);
		empleado.setDireccion(direccion);
		return true;
	}

	public boolean actualizarObjeto(String codigo, String nombre, String descripcion, String estado) throws ObjetoNoExisteException {

		Seguimiento.getInstance().log("Usuario " + usuarioAutenticado.getLogin() + ", actualizó un objeto");
		Objeto objeto = buscarObjeto(codigo);

		if (objeto == null) {
			throw new ObjetoNoExisteException("ERROR: No existe un objeto con código"+codigo);
		}

		objeto.setNombre(nombre);
		objeto.setDescripcion(descripcion);
		objeto.setCodigo(codigo);
		objeto.setEstado(estado);
		return true;
	}

	public boolean actualizarPrestamo(int numero, String fechaPrestamo, String fechaEntrega, int valorPrestamo,
			String estado, Cliente clienteAsociado, Empleado empleadoAsociado, List<Objeto> listaObjetosPrestar) throws PrestamoNoExisteException {

		Seguimiento.getInstance().log("Usuario " + usuarioAutenticado.getLogin() + ", actualizó un préstamo");
		Prestamo prestamo = buscarPrestamo(numero);

		if (prestamo == null) {
			throw new PrestamoNoExisteException("ERROR: No existe un préstamo con número "+numero);
		}

		prestamo.setNumero(numero);
		prestamo.setFechaPrestamo(fechaPrestamo);
		prestamo.setFechaEntrega(fechaEntrega);
		prestamo.setValorPrestamo(valorPrestamo);
		prestamo.setEstado(estado);
		prestamo.setClienteAsociado(clienteAsociado);
		prestamo.setEmpleadoAsociado(empleadoAsociado);
		prestamo.setListaObjetosAsociados(listaObjetosPrestar);
		return true;
	}
	

	// GETS AND SETS
	public List<Cliente> getListClientes() {
		return listClientes;
	}

	public void setListClientes(ArrayList<Cliente> listClientes) {
		this.listClientes = listClientes;
	}

	public List<Empleado> getListaEmpleados() {
		return listaEmpleados;
	}

	public void setListaEmpleados(ArrayList<Empleado> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}

	public List<Objeto> getListObjetos() {
		return listObjetos;
	}

	public void setListObjetos(ArrayList<Objeto> listObjetos) {
		this.listObjetos = listObjetos;
	}

	public List<Prestamo> getListPrestamos() {
		return listPrestamos;
	}

	public void setListPrestamos(ArrayList<Prestamo> listPrestamos) {
		this.listPrestamos = listPrestamos;
	}

	public List<Objeto> getListaObjetosAPrestar() {
		return listaObjetosAPrestar;
	}

	public void setListaObjetosAPrestar(List<Objeto> listaObjetosAPrestar) {
		this.listaObjetosAPrestar = listaObjetosAPrestar;
	}

	public List<Objeto> getListaObjetosDisponibles() {
		return listaObjetosDisponibles;
	}

	public void setListaObjetosDisponibles(List<Objeto> listaObjetosDisponibles) {
		this.listaObjetosDisponibles = listaObjetosDisponibles;
	}

	/**
	 * Metodo que permite obtener el valor del atributo listaUsuario
	 * 
	 * @return El valor del atributo listaUsuario
	 */
	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	/**
	 * Metodo que permite asignar un valor al atributo listaUsuario
	 * 
	 * @param listaUsuario Valor a ser asignado al atributo listaUsuario
	 */
	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public boolean autenticar(String login, String password) throws AutenticacionException {
		Usuario usuario = buscarUsuario(login);
		if (usuario != null) {
			usuarioAutenticado = usuario;
			Seguimiento.getInstance().log("Usuario " + usuarioAutenticado.getLogin() + ", se autenticó");
			if( usuario.getClave().equals(password) ) {
				return true;
			}
		}
		throw new AutenticacionException();
	}

	private Usuario buscarUsuario(String login) {
		for (Usuario usuario : listaUsuario) {
			if (usuario.getLogin().equals(login)) {
				return usuario;
			}
		}
		return null;
	}

	/**
	 * Metodo que permite obtener el valor del atributo usuarioAutenticado
	 * 
	 * @return El valor del atributo usuarioAutenticado
	 */
	public Usuario getUsuarioAutenticado() {
		return usuarioAutenticado;
	}

	/**
	 * Metodo que permite asignar un valor al atributo usuarioAutenticado
	 * 
	 * @param usuarioAutenticado Valor a ser asignado al atributo usuarioAutenticado
	 */
	public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
		this.usuarioAutenticado = usuarioAutenticado;
	}

	public void guardarUsuarios() {
		PersistenciaXML.guardar(new Usuarios(listaUsuario), Empresa.ARCHIVO_USUARIOS);
	}

	public void leerUsuarios() {
		Usuarios usuarios = (Usuarios) PersistenciaXML.leer(ARCHIVO_USUARIOS, Usuarios.class);
		listaUsuario = usuarios.getUsuarios();
	}

	public void guardarClientes() {
		PersistenciaXML.guardar(new Clientes(listClientes), Empresa.ARCHIVO_CLIENTES);
		PersistenciaComas.guardarClientes(listClientes);
	}

	public void leerClientes() {
		Clientes clientes = (Clientes) PersistenciaXML.leer(ARCHIVO_CLIENTES, Clientes.class);
		listClientes = clientes.getClientes();
	}

	public void guardarEmpleados() {
		PersistenciaXML.guardar(new Empleados(listaEmpleados), Empresa.ARCHIVO_EMPLEADOS);
		PersistenciaComas.guardarEmpleados(listaEmpleados);
	}

	public void leerEmpleados() {
		Empleados empleados = (Empleados) PersistenciaXML.leer(ARCHIVO_EMPLEADOS, Empleados.class);
		listaEmpleados = empleados.getEmpleados();
	}

	public void guardarObjetos() {
		PersistenciaXML.guardar(new Objetos(listObjetos), Empresa.ARCHIVO_OBJETOS);
		PersistenciaComas.guardarObjetos(listObjetos);
	}

	public void leerObjetos() {
		Objetos objetos = (Objetos) PersistenciaXML.leer(ARCHIVO_OBJETOS, Objetos.class);
		listObjetos = objetos.getObjetos();
	}

	public void guardarPrestamos() {
		CopiasUtil.crearCopia(ARCHIVO_PRESTAMOS);
		PersistenciaXML.guardar(new Prestamos(listPrestamos), Empresa.ARCHIVO_PRESTAMOS);
		PersistenciaComas.guardarPrestamo(listPrestamos);
	}

	public void leerPrestamos() {
		Prestamos prestamos = (Prestamos) PersistenciaXML.leer(ARCHIVO_PRESTAMOS, Prestamos.class);
		listPrestamos = prestamos.getPrestamos();
	}

	
}
