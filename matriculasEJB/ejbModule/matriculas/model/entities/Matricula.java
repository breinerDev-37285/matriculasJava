package matriculas.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the matricula database table.
 * 
 */
@Entity
@Table(name="matricula")
@NamedQuery(name="Matricula.findAll", query="SELECT m FROM Matricula m")
public class Matricula implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MatriculaPK id;

	private Boolean estado;

	private Integer registro;

	//bi-directional many-to-one association to Materia
	@ManyToOne
	@JoinColumn(name="materia", nullable=false, insertable=false, updatable=false)
	private Materia materiaBean;

	//bi-directional many-to-one association to NumMatricula
	@ManyToOne
	@JoinColumn(name="num_materia", nullable=false, insertable=false, updatable=false)
	private NumMatricula numMatricula;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="estudiante", nullable=false, insertable=false, updatable=false)
	private Usuario usuario;

	public Matricula() {
	}

	public MatriculaPK getId() {
		return this.id;
	}

	public void setId(MatriculaPK id) {
		this.id = id;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Integer getRegistro() {
		return this.registro;
	}

	public void setRegistro(Integer registro) {
		this.registro = registro;
	}

	public Materia getMateriaBean() {
		return this.materiaBean;
	}

	public void setMateriaBean(Materia materiaBean) {
		this.materiaBean = materiaBean;
	}

	public NumMatricula getNumMatricula() {
		return this.numMatricula;
	}

	public void setNumMatricula(NumMatricula numMatricula) {
		this.numMatricula = numMatricula;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}