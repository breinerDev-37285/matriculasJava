package matriculas.controllers;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import matriculas.model.dto.usuariosDTO;
import matriculas.model.entities.Rol;
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
	private int rol;
	private String nombres;
	private String apellidos;
	private String cedula;
	private String correo;
	private String password;
	private String ciudad;
	private String callePrincipal;
	private String calleSecundaria;
	private String numeroCasa;

	@PostConstruct
	public void init() {
		try {
			roles = mLogin.obtenerTodosRoles();
			usuarios= mUsuario.obtenerTodoslosUsuarios();
		} catch (Exception e) {
			JSFUtil.crearMensajeInfo(e.getMessage());
			e.printStackTrace();
		}
	}

	public void registrarUsuario() {
		try {
			mUsuario.registrarUsuario(nombres, apellidos, cedula, correo, password, rol, ciudad, callePrincipal,
					calleSecundaria, numeroCasa);
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

	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCallePrincipal() {
		return callePrincipal;
	}

	public void setCallePrincipal(String callePrincipal) {
		this.callePrincipal = callePrincipal;
	}

	public String getCalleSecundaria() {
		return calleSecundaria;
	}

	public void setCalleSecundaria(String calleSecundaria) {
		this.calleSecundaria = calleSecundaria;
	}

	public String getNumeroCasa() {
		return numeroCasa;
	}

	public void setNumeroCasa(String numeroCasa) {
		this.numeroCasa = numeroCasa;
	}

	public List<usuariosDTO> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<usuariosDTO> usuarios) {
		this.usuarios = usuarios;
	}
	
	

}
