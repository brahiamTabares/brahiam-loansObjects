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
	private String codigo;
	private String descripcion;
	private String Color;
	private String peso;
	private String estado;
	private String tipo;
	private String valorUnitario;
	private String valorTotal;
	private Prestamo prestamoAsociado;

	public Objeto() {
	}

	public Objeto(String nombre, String codigo, String descripcion, String color, String peso, String estado,
			String tipo,String valorUnitario,String valorTotal ) {
		this.nombre = nombre;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.Color = color;
		this.peso = peso;
		this.estado = estado;
		this.tipo = tipo;
		this.valorUnitario=valorUnitario;
		this.valorTotal=valorTotal;

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

	public String getColor() {
		return Color;
	}

	public void setColor(String color) {
		Color = color;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(String valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public String getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}

}
