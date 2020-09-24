package matriculas.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the estado database table.
 * 
 */
@Entity
@Table(name = "estado")
@NamedQuery(name = "Estado.findAll", query = "SELECT e FROM Estado e")

public class Estado implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@Column(length = 30)
	private String nombre;

	// bi-directional many-to-one association to Registro
	@OneToMany(mappedBy = "estadoBean")
	private List<Registro> registros;

	public Estado() {
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

	public List<Registro> getRegistros() {
		return this.registros;
	}

	public void setRegistros(List<Registro> registros) {
		this.registros = registros;
	}

	public Registro addRegistro(Registro registro) {
		getRegistros().add(registro);
		registro.setEstadoBean(this);

		return registro;
	}

	public Registro removeRegistro(Registro registro) {
		getRegistros().remove(registro);
		registro.setEstadoBean(null);

		return registro;
	}

}