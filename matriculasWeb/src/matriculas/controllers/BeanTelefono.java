package matriculas.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import matriculas.model.dto.telefonoDTO;
import matriculas.model.entities.Persona;
import matriculas.model.entities.Telefono;
import matriculas.model.managers.ManagerTelefono;

@Named
@SessionScoped
public class BeanTelefono implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private ManagerTelefono mTelefono;
	private List<telefonoDTO> lTelefonos;
	private List<Persona> lPersonas;
	private Telefono telefono;
	private int persona;
	
	
	public BeanTelefono() {  }

	@PostConstruct
	public void init() {
		lTelefonos = mTelefono.getAllTelefono();		
		lPersonas = mTelefono.getAllPersonas();
	}
	
	
	public void actionListenerRegistrarPersona() {
		
		
	}
	
	public void actionListenerActualizarTelefono(  telefonoDTO tel  ) {
		
	}
	
	public void actionListenereliminarTelefono(   telefonoDTO tel  ) {
		
	}

	public List<telefonoDTO> getlTelefonos() {
		return lTelefonos;
	}

	public void setlTelefonos(List<telefonoDTO> lTelefonos) {
		this.lTelefonos = lTelefonos;
	}

	public Telefono getTelefono() {
		return telefono;
	}

	public void setTelefono(Telefono telefono) {
		this.telefono = telefono;
	}

	public List<Persona> getlPersonas() {
		return lPersonas;
	}

	public void setlPersonas(List<Persona> lPersonas) {
		this.lPersonas = lPersonas;
	}

	public int getPersona() {
		return persona;
	}

	public void setPersona(int persona) {
		this.persona = persona;
	}
	
}
