package matriculas.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the persona database table.
 * 
 */
@Entity
@Table(name="persona")
@NamedQuery(name="Persona.findAll", query="SELECT p FROM Persona p")
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false, length=30)
	private String apellidos;

	@Column(length=10)
	private String cedula;

	@Column(name="dir_calle_principal", length=30)
	private String dirCallePrincipal;

	@Column(name="dir_calle_secundaria", length=30)
	private String dirCalleSecundaria;

	@Column(name="dir_ciudad", length=30)
	private String dirCiudad;

	@Column(name="dir_num_casa", length=20)
	private String dirNumCasa;

	@Column(nullable=false, length=30)
	private String nombres;

	//bi-directional many-to-one association to Telefono
	@OneToMany(mappedBy="personaBean")
	private List<Telefono> telefonos;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="personaBean")
	private List<Usuario> usuarios;

	public Persona() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getDirCallePrincipal() {
		return this.dirCallePrincipal;
	}

	public void setDirCallePrincipal(String dirCallePrincipal) {
		this.dirCallePrincipal = dirCallePrincipal;
	}

	public String getDirCalleSecundaria() {
		return this.dirCalleSecundaria;
	}

	public void setDirCalleSecundaria(String dirCalleSecundaria) {
		this.dirCalleSecundaria = dirCalleSecundaria;
	}

	public String getDirCiudad() {
		return this.dirCiudad;
	}

	public void setDirCiudad(String dirCiudad) {
		this.dirCiudad = dirCiudad;
	}

	public String getDirNumCasa() {
		return this.dirNumCasa;
	}

	public void setDirNumCasa(String dirNumCasa) {
		this.dirNumCasa = dirNumCasa;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public List<Telefono> getTelefonos() {
		return this.telefonos;
	}

	public void setTelefonos(List<Telefono> telefonos) {
		this.telefonos = telefonos;
	}

	public Telefono addTelefono(Telefono telefono) {
		getTelefonos().add(telefono);
		telefono.setPersonaBean(this);

		return telefono;
	}

	public Telefono removeTelefono(Telefono telefono) {
		getTelefonos().remove(telefono);
		telefono.setPersonaBean(null);

		return telefono;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setPersonaBean(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setPersonaBean(null);

		return usuario;
	}

}