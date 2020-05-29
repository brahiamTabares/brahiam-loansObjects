package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "prestamo")
@XmlAccessorType(XmlAccessType.FIELD)

public class Prestamo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer codigo;
	private String estado;
	private Integer valorPrestamo;
	private String fechaPrestamo;
	private String fechaEntrega;
	private String numeroObjetoPrestado;
	private Cliente clienteAsociado;
	private Empleado empleadoAsociado;	
	private List<Objeto> listaObjetosAsociados = new ArrayList<>();

	public Prestamo() {

	}

	public Prestamo(int codigo, String estado, int valorPrestamo, String fechaPrestamo, String fechaEntrega,
			String numeroObjetoPrestado,  Cliente clienteAsociado,Empleado empleadoAsociado,
			List<Objeto> listaObjetosAsociados) {
		this.codigo = codigo;
		this.estado = estado;
		this.valorPrestamo = valorPrestamo;
		this.fechaPrestamo = fechaPrestamo;
		this.fechaEntrega = fechaEntrega;
		this.numeroObjetoPrestado = numeroObjetoPrestado;
		this.empleadoAsociado = empleadoAsociado;
		this.clienteAsociado = clienteAsociado;
		this.listaObjetosAsociados = new ArrayList<Objeto>();
		this.listaObjetosAsociados.addAll(listaObjetosAsociados);
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(String fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public String getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public String getNumeroObjetoPrestado() {
		return numeroObjetoPrestado;
	}

	public void setNumeroObjetoPrestado(String numeroObjetoPrestado) {
		this.numeroObjetoPrestado = numeroObjetoPrestado;
	}

	public Integer getValorPrestamo() {
		return valorPrestamo;
	}

	public void setValorPrestamo(Integer valorPrestamo) {
		this.valorPrestamo = valorPrestamo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<Objeto> getListaObjetosAsociados() {
		return listaObjetosAsociados;
	}

	public void setListaObjetosAsociados(List<Objeto> listaObjetosAsociados) {
		this.listaObjetosAsociados = listaObjetosAsociados;
	}

	public Empleado getEmpleadoAsociado() {
		return empleadoAsociado;
	}

	public void setEmpleadoAsociado(Empleado empleadoAsociado) {
		this.empleadoAsociado = empleadoAsociado;
	}

	public Cliente getClienteAsociado() {
		return clienteAsociado;
	}

	public void setClienteAsociado(Cliente clienteAsociado) {
		this.clienteAsociado = clienteAsociado;
	}



}
