package matriculas.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the periodo_academico database table.
 * 
 */
@Entity
@Table(name="periodo_academico")
@NamedQuery(name="PeriodoAcademico.findAll", query="SELECT p FROM PeriodoAcademico p")
public class PeriodoAcademico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_fin", nullable=false)
	private Date fechaFin;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inicio", nullable=false)
	private Date fechaInicio;

	//bi-directional many-to-one association to Semestre
	@OneToMany(mappedBy="periodoAcademicoBean")
	private List<Semestre> semestres;

	public PeriodoAcademico() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public List<Semestre> getSemestres() {
		return this.semestres;
	}

	public void setSemestres(List<Semestre> semestres) {
		this.semestres = semestres;
	}

	public Semestre addSemestre(Semestre semestre) {
		getSemestres().add(semestre);
		semestre.setPeriodoAcademicoBean(this);

		return semestre;
	}

	public Semestre removeSemestre(Semestre semestre) {
		getSemestres().remove(semestre);
		semestre.setPeriodoAcademicoBean(null);

		return semestre;
	}

}