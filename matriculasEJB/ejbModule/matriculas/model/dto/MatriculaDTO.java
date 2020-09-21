package matriculas.model.dto;

public class MatriculaDTO {
	private String nombreMateria;
	private String nombreNumMateria;
	private String estado;

	public MatriculaDTO(String nombreMateria, String nombreNumMateria, String estado) {
		this.nombreMateria = nombreMateria;
		this.nombreNumMateria = nombreNumMateria;
		this.estado = estado;
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

}
