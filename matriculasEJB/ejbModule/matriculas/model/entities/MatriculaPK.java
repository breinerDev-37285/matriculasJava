package matriculas.model.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the matricula database table.
 * 
 */
@Embeddable
public class MatriculaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private Integer estudiante;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private Integer materia;

	@Column(name="num_materia", insertable=false, updatable=false, unique=true, nullable=false)
	private Integer numMateria;

	public MatriculaPK() {
	}
	public Integer getEstudiante() {
		return this.estudiante;
	}
	public void setEstudiante(Integer estudiante) {
		this.estudiante = estudiante;
	}
	public Integer getMateria() {
		return this.materia;
	}
	public void setMateria(Integer materia) {
		this.materia = materia;
	}
	public Integer getNumMateria() {
		return this.numMateria;
	}
	public void setNumMateria(Integer numMateria) {
		this.numMateria = numMateria;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MatriculaPK)) {
			return false;
		}
		MatriculaPK castOther = (MatriculaPK)other;
		return 
			this.estudiante.equals(castOther.estudiante)
			&& this.materia.equals(castOther.materia)
			&& this.numMateria.equals(castOther.numMateria);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.estudiante.hashCode();
		hash = hash * prime + this.materia.hashCode();
		hash = hash * prime + this.numMateria.hashCode();
		
		return hash;
	}
}