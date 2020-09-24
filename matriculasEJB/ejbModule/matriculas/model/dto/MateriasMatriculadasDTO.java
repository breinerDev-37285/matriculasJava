package matriculas.model.dto;

public class MateriasMatriculadasDTO {

	private int id;
	private String nMateria;
	private String nSemestre;
	private int nCreditos;
	private String tipoMatricula;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getnMateria() {
		return nMateria;
	}

	public void setnMateria(String nMateria) {
		this.nMateria = nMateria;
	}

	public String getnSemestre() {
		return nSemestre;
	}

	public void setnSemestre(String nMestre) {
		this.nSemestre = nMestre;
	}

	public int getnCreditos() {
		return nCreditos;
	}

	public void setnCreditos(int nCreditos) {
		this.nCreditos = nCreditos;
	}

	public String getTipoMatricula() {
		return tipoMatricula;
	}

	public void setTipoMatricula(String tipoMatricula) {
		this.tipoMatricula = tipoMatricula;
	}

}
