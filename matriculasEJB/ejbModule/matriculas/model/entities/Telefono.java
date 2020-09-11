package matriculas.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the telefono database table.
 * 
 */
@Entity
@Table(name="telefono")
@NamedQuery(name="Telefono.findAll", query="SELECT t FROM Telefono t")
public class Telefono implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer codigo;

	@Column(nullable=false, length=10)
	private String numero;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="persona")
	private Persona personaBean;

	public Telefono() {
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Persona getPersonaBean() {
		return this.personaBean;
	}

	public void setPersonaBean(Persona personaBean) {
		this.personaBean = personaBean;
	}

}