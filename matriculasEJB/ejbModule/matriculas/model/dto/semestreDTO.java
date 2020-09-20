package matriculas.model.dto;

import java.time.LocalDate;

public class semestreDTO {
	
		private int id;
		private  String nombre;
		private LocalDate fecha_inicio, fecha_fin;
		
		public semestreDTO(int id, String nombre, LocalDate fecha_inicio, LocalDate fecha_fin) {
			super();
			this.id = id;
			this.nombre = nombre;
			this.fecha_inicio = fecha_inicio;
			this.fecha_fin = fecha_fin;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public LocalDate getFecha_inicio() {
			return fecha_inicio;
		}

		public void setFecha_inicio(LocalDate fecha_inicio) {
			this.fecha_inicio = fecha_inicio;
		}

		public LocalDate getFecha_fin() {
			return fecha_fin;
		}

		public void setFecha_fin(LocalDate fecha_fin) {
			this.fecha_fin = fecha_fin;
		}
		
}
