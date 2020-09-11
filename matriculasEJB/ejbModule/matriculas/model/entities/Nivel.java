package matriculas.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the nivel database table.
 * 
 */
@Entity
@Table(name="nivel")
@NamedQuery(name="Nivel.findAll", query="SELECT n FROM Nivel n")
public class Nivel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=30)
	private String nombre;

	//bi-directional many-to-one association to Semestre
	@OneToMany(mappedBy="nivelBean")
	private List<Semestre> semestres;

	public Nivel() {
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

	public List<Semestre> getSemestres() {
		return this.semestres;
	}

	public void setSemestres(List<Semestre> semestres) {
		this.semestres = semestres;
	}

	public Semestre addSemestre(Semestre semestre) {
		getSemestres().add(semestre);
		semestre.setNivelBean(this);

		return semestre;
	}

	public Semestre removeSemestre(Semestre semestre) {
		getSemestres().remove(semestre);
		semestre.setNivelBean(null);

		return semestre;
	}

}