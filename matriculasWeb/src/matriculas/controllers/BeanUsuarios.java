package matriculas.controllers;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import matriculas.model.dto.usuariosDTO;
import matriculas.model.entities.Persona;
import matriculas.model.entities.Rol;
import matriculas.model.entities.Usuario;
import matriculas.model.managers.ManagerLogin;
import matriculas.model.managers.ManagerUsuarios;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanUsuarios implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerLogin mLogin;
	@EJB
	private ManagerUsuarios mUsuario;

	private List<Rol> roles;
	private List<usuariosDTO> usuarios;
	private Usuario usuario;
	private Persona persona;
	

	@PostConstruct
	public void init() {
		try {
			roles = mLogin.obtenerTodosRoles();
			usuarios= mUsuario.obtenerTodoslosUsuarios();
			persona = new Persona();
			usuario = new Usuario();
		} catch (Exception e) {
			JSFUtil.crearMensajeInfo(e.getMessage());
			e.printStackTrace();
		}
	}

	public void registrarUsuario() {
		try {
			usuario = mUsuario.registrarUsuarios(usuario);
			mUsuario.registrarPersona(persona,usuario);
			
			usuarios= mUsuario.obtenerTodoslosUsuarios();
			persona = new Persona();
			usuario = new Usuario();
			
			JSFUtil.crearMensajeInfo("usuario registrado correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError( e.getMessage() );
			e.printStackTrace();
		}
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public List<usuariosDTO> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<usuariosDTO> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}
