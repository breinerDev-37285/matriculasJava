package matriculas.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the materia database table.
 * 
 */
@Entity
@Table(name="materia")
@NamedQuery(name="Materia.findAll", query="SELECT m FROM Materia m")
public class Materia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer codigo;

	private Integer creditos;

	@Column(length=30)
	private String nombre;

	//bi-directional many-to-one association to Semestre
	@ManyToOne
	@JoinColumn(name="semestre")
	private Semestre semestreBean;

	//bi-directional many-to-one association to Matricula
	@OneToMany(mappedBy="materiaBean")
	private List<Matricula> matriculas;

	public Materia() {
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCreditos() {
		return this.creditos;
	}

	public void setCreditos(Integer creditos) {
		this.creditos = creditos;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Semestre getSemestreBean() {
		return this.semestreBean;
	}

	public void setSemestreBean(Semestre semestreBean) {
		this.semestreBean = semestreBean;
	}

	public List<Matricula> getMatriculas() {
		return this.matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public Matricula addMatricula(Matricula matricula) {
		getMatriculas().add(matricula);
		matricula.setMateriaBean(this);

		return matricula;
	}

	public Matricula removeMatricula(Matricula matricula) {
		getMatriculas().remove(matricula);
		matricula.setMateriaBean(null);

		return matricula;
	}

}