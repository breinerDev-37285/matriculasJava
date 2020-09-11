package matriculas.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the semestre database table.
 * 
 */
@Entity
@Table(name="semestre")
@NamedQuery(name="Semestre.findAll", query="SELECT s FROM Semestre s")
public class Semestre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	//bi-directional many-to-one association to Materia
	@OneToMany(mappedBy="semestreBean")
	private List<Materia> materias;

	//bi-directional many-to-one association to Nivel
	@ManyToOne
	@JoinColumn(name="nivel")
	private Nivel nivelBean;

	//bi-directional many-to-one association to PeriodoAcademico
	@ManyToOne
	@JoinColumn(name="periodo_academico")
	private PeriodoAcademico periodoAcademicoBean;

	public Semestre() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Materia> getMaterias() {
		return this.materias;
	}

	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}

	public Materia addMateria(Materia materia) {
		getMaterias().add(materia);
		materia.setSemestreBean(this);

		return materia;
	}

	public Materia removeMateria(Materia materia) {
		getMaterias().remove(materia);
		materia.setSemestreBean(null);

		return materia;
	}

	public Nivel getNivelBean() {
		return this.nivelBean;
	}

	public void setNivelBean(Nivel nivelBean) {
		this.nivelBean = nivelBean;
	}

	public PeriodoAcademico getPeriodoAcademicoBean() {
		return this.periodoAcademicoBean;
	}

	public void setPeriodoAcademicoBean(PeriodoAcademico periodoAcademicoBean) {
		this.periodoAcademicoBean = periodoAcademicoBean;
	}

}