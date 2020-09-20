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

	@Column(nullable=false, length=2147483647)
	private String nombre;

	//bi-directional many-to-one association to Materia
	@OneToMany(mappedBy="semestreBean")
	private List<Materia> materias;

	//bi-directional many-to-many association to PeriodoAcademico
	@ManyToMany
	@JoinTable(
		name="semestre_periodo"
		, joinColumns={
			@JoinColumn(name="semestre", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="periodo", nullable=false)
			}
		)
	private List<PeriodoAcademico> periodoAcademicos;

	public Semestre() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public List<PeriodoAcademico> getPeriodoAcademicos() {
		return this.periodoAcademicos;
	}

	public void setPeriodoAcademicos(List<PeriodoAcademico> periodoAcademicos) {
		this.periodoAcademicos = periodoAcademicos;
	}

}