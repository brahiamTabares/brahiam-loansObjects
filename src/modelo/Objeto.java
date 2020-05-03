package modelo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * 
 */
@XmlRootElement(name = "objeto")
@XmlAccessorType(XmlAccessType.FIELD)

public class Objeto implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String ESTADO_PRESTADO = "Prestado";
	public static final String ESTADO_DISPONIBLE = "Disponible";
	public static final String ESTADO_RESERVADO = "Reservado";
	
	private String nombre;
	private String descripcion;
	private String codigo;// pendiente convertir a entero
	private String estado;
	private Prestamo prestamoAsociado;
	
	
	public Objeto() {
	}
	
	public Objeto(String nombre, String descripcion, String codigo, String estado) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.codigo = codigo;
		this.estado = estado;
	}


	public String getNombre() {
		return nombre;
	}
	
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}


	public Prestamo getPrestamoAsociado() {
		return prestamoAsociado;
	}


	public void setPrestamoAsociado(Prestamo prestamoAsociado) {
		this.prestamoAsociado = prestamoAsociado;
	}

}
