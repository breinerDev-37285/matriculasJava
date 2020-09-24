package matriculas.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the registro database table.
 * 
 */
@Entity

@Table(name = "registro")
@NamedQuery(name = "Registro.findAll", query = "SELECT r FROM Registro r")

public class Registro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)

	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	// bi-directional many-to-one association to Matricula
	@OneToMany(mappedBy = "registroBean", cascade = CascadeType.ALL)
	private List<Matricula> matriculas;

	// bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name = "estado")

	private Estado estadoBean;

	public Registro() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<Matricula> getMatriculas() {
		return this.matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public Matricula addMatricula(Matricula matricula) {
		getMatriculas().add(matricula);
		matricula.setRegistroBean(this);

		return matricula;
	}

	public Matricula removeMatricula(Matricula matricula) {
		getMatriculas().remove(matricula);
		matricula.setRegistroBean(null);

		return matricula;
	}

	public Estado getEstadoBean() {
		return this.estadoBean;
	}

	public void setEstadoBean(Estado estadoBean) {
		this.estadoBean = estadoBean;
	}

}