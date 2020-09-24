package matriculas.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import matriculas.model.entities.Rol;
import matriculas.model.managers.ManagerRol;

@Named
@SessionScoped
public class BeanRol implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@EJB
	ManagerRol mRol;
	
	private List<Rol> lRoles;
	private Rol rol;
	
	@PostConstruct
	public void init() {
		lRoles = mRol.findAllRoles();
		rol = new Rol();
	}
	
	public void actionListenerRegistrarRol() {
		try {
			mRol.registrarNuevoRol(rol);
			rol = new Rol();
			lRoles = mRol.findAllRoles();
			JSFUtil.crearMensajeInfo("Se ha registrado el nuevo rol con éxito");
		} catch (Exception e) {
				JSFUtil.crearMensajeError( e.getMessage() );
		}
	}
	
	public void actionListenerActualizarRol( Rol role ) {
		try {
			mRol.actualizarRol(role);
			lRoles = mRol.findAllRoles();
			JSFUtil.crearMensajeInfo("Se ha actualizado el nuevo rol con éxito");
		} catch (Exception e) {
			JSFUtil.crearMensajeError( e.getMessage() );
		}
	}
	
	public void actionListenerEliminarRol( Rol  role ) {
		try {
			mRol.eliminarRol(role);
			lRoles = mRol.findAllRoles();
			JSFUtil.crearMensajeInfo("Se ha eliminado el nuevo rol con éxito");
		} catch (Exception e) {
			JSFUtil.crearMensajeError( e.getMessage() );
		}
	}

	public List<Rol> getlRoles() {
		return lRoles;
	}

	public void setlRoles(List<Rol> lRoles) {
		this.lRoles = lRoles;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

}
