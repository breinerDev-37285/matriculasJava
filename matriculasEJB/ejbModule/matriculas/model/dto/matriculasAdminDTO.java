package matriculas.model.dto;

public class matriculasAdminDTO {

		int idEstudiante, idMateria, idNumMatricula;
		String cedulaEst, materia, matricula, estado;
		
		public matriculasAdminDTO(int idEstudiante, int idMateria, int idNumMatricula, String cedulaEst, String materia,
				String matricula, String estado) {

			this.idEstudiante = idEstudiante;
			this.idMateria = idMateria;
			this.idNumMatricula = idNumMatricula;
			this.cedulaEst = cedulaEst;
			this.materia = materia;
			this.matricula = matricula;
			this.estado = estado;
		}

		public int getIdEstudiante() {
			return idEstudiante;
		}

		public void setIdEstudiante(int idEstudiante) {
			this.idEstudiante = idEstudiante;
		}

		public int getIdMateria() {
			return idMateria;
		}

		public void setIdMateria(int idMateria) {
			this.idMateria = idMateria;
		}

		public int getIdNumMatricula() {
			return idNumMatricula;
		}

		public void setIdNumMatricula(int idNumMatricula) {
			this.idNumMatricula = idNumMatricula;
		}

		public String getCedulaEst() {
			return cedulaEst;
		}

		public void setCedulaEst(String cedulaEst) {
			this.cedulaEst = cedulaEst;
		}

		public String getMateria() {
			return materia;
		}

		public void setMateria(String materia) {
			this.materia = materia;
		}

		public String getMatricula() {
			return matricula;
		}

		public void setMatricula(String matricula) {
			this.matricula = matricula;
		}

		public String getEstado() {
			return estado;
		}

		public void setEstado(String estado) {
			this.estado = estado;
		}
		
}
