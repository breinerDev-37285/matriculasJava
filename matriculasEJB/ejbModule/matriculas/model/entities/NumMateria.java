package matriculas.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the num_materia database table.
 * 
 */
@Entity
@Table(name="num_materia")
@NamedQuery(name="NumMateria.findAll", query="SELECT n FROM NumMateria n")
public class NumMateria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer codigo;

	@Column(length=30)
	private String nombre;

	//bi-directional many-to-one association to Matricula
	@OneToMany(mappedBy="numMateriaBean")
	private List<Matricula> matriculas;

	public NumMateria() {
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Matricula> getMatriculas() {
		return this.matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public Matricula addMatricula(Matricula matricula) {
		getMatriculas().add(matricula);
		matricula.setNumMateriaBean(this);

		return matricula;
	}

	public Matricula removeMatricula(Matricula matricula) {
		getMatriculas().remove(matricula);
		matricula.setNumMateriaBean(null);

		return matricula;
	}

}