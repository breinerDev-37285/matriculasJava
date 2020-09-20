package matriculas.controllers;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
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
	private boolean verificado;
	private List<Object> estado;
	
	public BeanLogin(){ }
	
	@PostConstruct
	public void init() { 
		
	}
	

	public String actionValidarUsuario() {
		String ruta = "";
		
		try {
			estado = mLogin.verificarAcceso(correo, password);
			
			ruta = estado.get(0).toString();
			
			if (!ruta.equals(""))
				verificado = true;
			else
				verificado = false;
		} catch (Exception e) {
			 JSFUtil.crearMensajeError(e.getMessage());
		}
		
		return ruta;
	}
	
	public void actionListenerVerificarLogin() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		String requestPath = ec.getRequestPathInfo();
		try {
		
			if (verificado == false) {
				// el usuario no ingrese por login
				ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml");
			} else {
				// el usuario iso login pero se verifica el control de acceso
				
				int rol = ( int )  estado.get(1) ;
				String pagina = requestPath;
				List<String> paginas = new ArrayList<String>();
				
				paginas.add("inicio.xhtml");
				paginas.add("usuarios.xhtml");
				paginas.add("matriculas.xhtml");
				paginas.add("periodoAcademico.xhtml");
				paginas.add("materias.xhtml");
				
				for (String pg : paginas) {
					if ( pagina.contains(pg)  && rol !=2  ) {
						ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml");
					}
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	
	public boolean isVerificado() {
		return verificado;
	}

	public void setVerificado(boolean verificado) {
		this.verificado = verificado;
	}

	public List<Object> getEstado() {
		return estado;
	}

	public void setEstado(List<Object> estado) {
		this.estado = estado;
	}
	
	
	
	
}
