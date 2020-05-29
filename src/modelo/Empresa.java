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
	public Cliente crearCliente(String tipoDocumento,String numeroDocumento,String nombre,String telefonoResidencia,
			String telefonoCelular,String direccion,String ciudadR,String departamento,String pais, String profesion, String email) throws ClienteExisteException {
		Seguimiento.getInstance().log("Usuario " + usuarioAutenticado.getLogin() + ", creó un cliente ");
		if( buscarCliente(numeroDocumento) != null  ) {
			throw new ClienteExisteException("ERROR: Ya existe un cliente con cédula "+numeroDocumento);
		}
		Cliente nuevoCliente = new Cliente(tipoDocumento, numeroDocumento, nombre, telefonoResidencia, telefonoCelular, direccion, ciudadR, departamento, pais,profesion, email);
		listClientes.add(nuevoCliente);
		return nuevoCliente;
	}

	public Empleado crearEmpleado(String tipoDocumento, String numeroDocumento, String nombre, String telefonoResidencia,
			String telefonoCelular, String direccion, String ciudadR, String departamento, String pais, String tipoEmpleado, String email) throws EmpleadoExisteException {
		Seguimiento.getInstance().log("Usuario " + usuarioAutenticado.getLogin() + ", creó un empleado ");
		if( buscarEmpleado(numeroDocumento) != null  ) {
			throw new EmpleadoExisteException("ERROR: Ya existe un empleado con c�dula "+numeroDocumento);
		}
		Empleado nuevoEmpleado = new Empleado(tipoDocumento, numeroDocumento, nombre, telefonoResidencia, telefonoCelular, direccion, ciudadR,
				departamento, pais,tipoEmpleado, email);
		listaEmpleados.add(nuevoEmpleado);
		return nuevoEmpleado;

	}

	public Objeto crearObjeto(String nombre, String codigo, String descripcion, String color, String peso, String estado,
			String tipo, String valorUnitario,String valorTotal) throws ObjetoExisteException {
		Seguimiento.getInstance().log("Usuario " + usuarioAutenticado.getLogin() + ", cre� un objeto ");
		if( buscarObjeto(codigo) != null  ) {
			throw new ObjetoExisteException("ERROR: Ya existe un objeto con c�digo "+codigo);
		}
		Objeto nuevoObjeto = new Objeto(nombre,codigo,descripcion,color,peso,estado,tipo,valorUnitario,valorTotal);
		listObjetos.add(nuevoObjeto);
		return nuevoObjeto;

	}
	
	public Prestamo crearPrestamo(int codigo, String estado,int valorPrestamo,String fechaPrestamo, String fechaEntrega, 
			 Cliente clienteAsociado, Empleado empleadoAsociado, List<Objeto> listaObjetosPrestar) throws PrestamoExisteException {

		Seguimiento.getInstance().log("Usuario " + usuarioAutenticado.getLogin() + ", cre� un pr�stamo");
		if( buscarPrestamo(codigo) != null  ) {
			throw new PrestamoExisteException("ERROR: Ya existe un préstamo con el número "+codigo);
		}
		Prestamo nuevoPrestamo = new Prestamo(codigo, estado, valorPrestamo, fechaPrestamo, fechaEntrega, null,
				 clienteAsociado,empleadoAsociado, listaObjetosPrestar);
		for (Objeto objeto : listaObjetosPrestar) {
			objeto.setEstado(Objeto.ESTADO_PRESTADO);
		}

		getListPrestamos().add(nuevoPrestamo);

		getListaObjetosAPrestar().clear();

		return nuevoPrestamo;
	}
	
	
	// buscar
	public Empleado buscarEmpleado(String numeroDocumento) {
		Seguimiento.getInstance()
				.log("Usuario " + usuarioAutenticado.getLogin() + ", busco el empleado con cédula " +numeroDocumento);
		for (int i = 0; i < listaEmpleados.size(); i++) {
			if (listaEmpleados.get(i).getNumeroDocumento().equals(numeroDocumento)) {
				return listaEmpleados.get(i);
			}
		}
		return null;
	}

	public Cliente buscarCliente(String numeroDocumento) {
		Seguimiento.getInstance()
				.log("Usuario " + usuarioAutenticado.getLogin() + ", buscó el cliente con cédula " + numeroDocumento);
		for (int i = 0; i < listClientes.size(); i++) {
			if (listClientes.get(i).getNumeroDocumento().equals(numeroDocumento)) {
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

	public Prestamo buscarPrestamo(int codigo) {
		Seguimiento.getInstance()
				.log("Usuario " + usuarioAutenticado.getLogin() + ", buscó el Préstamo con código " + codigo);
		for (int i = 0; i < listPrestamos.size(); i++) {
			if (listPrestamos.get(i).getCodigo().equals(codigo)) {
				return listPrestamos.get(i);
			}
		}
		return null;
	}
	
	
	// eliminar
	public Cliente eliminarCliente(String numeroDocumento) throws ClienteNoExisteException {

		Cliente clienteEliminar = buscarCliente(numeroDocumento);

		if (clienteEliminar != null) {
			Seguimiento.getInstance().log(
					"Usuario " + usuarioAutenticado.getLogin() + ", eliminó el cliente con cédula " + numeroDocumento);
			getListClientes().remove(clienteEliminar);
			return clienteEliminar;
		} 
		throw new ClienteNoExisteException("ERROR: No existe un cliente con cédula "+numeroDocumento);
	}

	public Empleado eliminarEmpleado(String numeroDocumento) throws EmpleadoNoExisteException {

		Empleado empleadoEliminar = buscarEmpleado(numeroDocumento);

		if (empleadoEliminar != null) {
			Seguimiento.getInstance().log(
					"Usuario " + usuarioAutenticado.getLogin() + ", eliminó el empleado con cédula " + numeroDocumento);
			getListaEmpleados().remove(empleadoEliminar);
			return empleadoEliminar;
		} 
		
		throw new EmpleadoNoExisteException("ERROR: No existe un empleado con cédula "+numeroDocumento);

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
	
	public Prestamo eliminarPrestamo(int codigo) throws PrestamoNoExisteException {
		Prestamo prestamoEliminar = buscarPrestamo(codigo);

		if (prestamoEliminar != null) {
			Seguimiento.getInstance()
					.log("Usuario " + usuarioAutenticado.getLogin() + ", eliminó el préstamo con número " + codigo);
			getListPrestamos().remove(prestamoEliminar);
			return prestamoEliminar;
		} 
		throw new PrestamoNoExisteException("ERROR: No existe un préstamo con número "+codigo);
	}

	// Actualizar
	public boolean actualizarCliente(String tipoDocumento, String numeroDocumento, String nombre, String telefonoResidencia,
			String telefonoCelular, String direccion, String ciudadR, String departamento, String pais, String email) throws ClienteNoExisteException {
		Cliente cliente = buscarCliente(numeroDocumento);

		if (cliente == null) {
			throw new ClienteNoExisteException("ERROR: No existe un cliente con número de cédula"+numeroDocumento);
		}

		Seguimiento.getInstance().log("Usuario " + usuarioAutenticado.getLogin() + ", actualizó un cliente");
		
		cliente.setTipoDocumento(tipoDocumento);
		cliente.setNumeroDocumento(numeroDocumento);
		cliente.setNombre(nombre);
		cliente.setTelefonoResidencia(telefonoResidencia);
		cliente.setTelefonoCelular(telefonoCelular);
		cliente.setDireccion(direccion);
		cliente.setCiudadR(ciudadR);
		cliente.setDepartamento(departamento);
		cliente.setPais(pais);
		cliente.setEmail(email);
		return true;
	}

	public boolean actualizarEmpleado(String tipoDocumento, String numeroDocumento, String nombre, String telefonoResidencia,
			String telefonoCelular, String direccion, String ciudadR, String departamento, String pais, String email) throws EmpleadoNoExisteException {
		
		Empleado empleado = buscarEmpleado(numeroDocumento);

		if (empleado == null) {
			throw new EmpleadoNoExisteException("ERROR: No existe un empleado con número de cédula"+numeroDocumento);
		}
		Seguimiento.getInstance().log("Usuario " + usuarioAutenticado.getLogin() + ", actualizó un empleado");
		empleado.setTipoDocumento(tipoDocumento);
		empleado.setNumeroDocumento(numeroDocumento);
		empleado.setNombre(nombre);
		empleado.setTelefonoResidencia(telefonoResidencia);
		empleado.setTelefonoCelular(telefonoCelular);
		empleado.setDireccion(direccion);
		empleado.setCiudadR(ciudadR);
		empleado.setDepartamento(departamento);
		empleado.setPais(pais);
		empleado.setEmail(email);
		
		return true;
	}

	public boolean actualizarObjeto(String nombre, String codigo, String descripcion, String color, String peso, String estado,
			String tipo, String valorUnitario,String valorTotal) throws ObjetoNoExisteException {

		Seguimiento.getInstance().log("Usuario " + usuarioAutenticado.getLogin() + ", Se actualizo un objeto");
		Objeto objeto = buscarObjeto(codigo);

		if (objeto == null) {
			throw new ObjetoNoExisteException("ERROR: No existe un objeto con c�digo"+codigo);
		}

		objeto.setNombre(nombre);
		objeto.setCodigo(codigo);
		objeto.setDescripcion(descripcion);
		objeto.setColor(color);
		objeto.setEstado(estado);
		objeto.setTipo(tipo);
		objeto.setValorUnitario(valorUnitario);
		objeto.setValorTotal(valorTotal);
		
		return true;
	}

	public boolean actualizarPrestamo(int codigo,String estado, int valorPrestamo,String fechaPrestamo, String fechaEntrega,
			  Empleado empleadoAsociado, Cliente clienteAsociado, List<Objeto> listaObjetosPrestar) throws PrestamoNoExisteException {

		Seguimiento.getInstance().log("Usuario " + usuarioAutenticado.getLogin() + ", actualizó un préstamo");
		Prestamo prestamo = buscarPrestamo(codigo);

		if (prestamo == null) {
			throw new PrestamoNoExisteException("ERROR: No existe un préstamo con número "+codigo);
		}

		prestamo.setCodigo(codigo);
		prestamo.setEstado(estado);
		prestamo.setValorPrestamo(valorPrestamo);
		prestamo.setFechaPrestamo(fechaPrestamo);
		prestamo.setFechaEntrega(fechaEntrega);
		prestamo.setEmpleadoAsociado(empleadoAsociado);
		prestamo.setClienteAsociado(clienteAsociado);
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
