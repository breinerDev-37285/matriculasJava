package matriculas.controllers;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import matriculas.model.managers.ManagerLogin;

@Named
@SessionScoped
public class BeanLogin implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private ManagerLogin mLogin;
	private String correo;
	private String password;
	
	public BeanLogin(){ }
	
	@PostConstruct
	public void init() { 
		
	}
	
	public String  actionListenerVerificarAcceso() {
		String ruta = "";
		try {
				ruta =  mLogin.verificarAcceso(  correo,password  );
		} catch (Exception e) {
				JSFUtil.crearMensajeError( "credenciales invalidas" );
				e.printStackTrace();
		}
		System.out.println( ruta  );
		return ruta;
	}
	
	public String actionCerrarSession() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "login?faces-redirect=true";
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
	
}
