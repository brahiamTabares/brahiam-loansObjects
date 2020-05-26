package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

public class InventarioPrestamo  implements Serializable{
	
	   
	 private  static final long serialVersionUID = 1L;
	
		private LocalDate fechaEntrega;
		private String codigo;
		private String nombre;
		private int diasMora;
		private int diasFaltantes;

	 public InventarioPrestamo (LocalDate fechaEntrega, String codigo, String nombre) {

			this.fechaEntrega = fechaEntrega;
			this.codigo = codigo;
			this.nombre = nombre;
			calcularDiasFaltantes();
			calcularDiasMora();
		}

		public LocalDate getFechaEntrega() {
			return fechaEntrega;
		}

		public void setFechaEntrega(LocalDate fechaEntrega) {
			this.fechaEntrega = fechaEntrega;
		}

		public String getCodigo() {
			return codigo;
		}

		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		private void calcularDiasMora() {
			Period p = Period.between(fechaEntrega, LocalDate.now());
			diasMora = p.getDays();
			if( diasMora < 0) {
				diasMora = 0;
			}
		}

		private void calcularDiasFaltantes() {
			Period p = Period.between(LocalDate.now(), fechaEntrega);
			diasFaltantes = p.getDays();
			if( diasFaltantes < 0) {
				diasFaltantes = 0;
			}
		}

		public int getDiasMora() {
			return diasMora;
		}

		public void setDiasMora(int diasMora) {
			this.diasMora = diasMora;
		}

		public int getDiasFaltantes() {
			return diasFaltantes;
		}

		public void setDiasFaltantes(int diasFaltantes) {
			this.diasFaltantes = diasFaltantes;
		}
	
	

}
