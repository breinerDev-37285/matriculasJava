package matriculas.model.dto;

public class MatriculaDTO {
	private String nombreMateria;
	private String nombreNumMateria;
	private String estado;
	private int creditos;

	public MatriculaDTO(String nombreMateria, String nombreNumMateria, String estado, int creditos) {
		this.nombreMateria = nombreMateria;
		this.nombreNumMateria = nombreNumMateria;
		this.estado = estado;
		this.creditos = creditos;
	}

	public String getNombreMateria() {
		return nombreMateria;
	}

	public void setNombreMateria(String nombreMateria) {
		this.nombreMateria = nombreMateria;
	}

	public String getNombreNumMateria() {
		return nombreNumMateria;
	}

	public void setNombreNumMateria(String nombreNumMateria) {
		this.nombreNumMateria = nombreNumMateria;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getCreditos() {
		return creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}

}
