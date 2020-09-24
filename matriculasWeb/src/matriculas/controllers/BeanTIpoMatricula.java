package matriculas.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import matriculas.model.entities.NumMatricula;
import matriculas.model.managers.ManagerTipoMatriculas;

@Named
@RequestScoped
public class BeanTIpoMatricula implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerTipoMatriculas mtMatricula;
	private List<NumMatricula>   lNumMatricula;
	private NumMatricula   numMatricula;
	
	public BeanTIpoMatricula() {  }
	
	@PostConstruct
	public void init() {
		lNumMatricula = mtMatricula.getAllTipoMatricula();
		numMatricula = new NumMatricula();
	}
	
	public void actionListenerRegistrarTipoMatricula() {
		try {
			mtMatricula.registrarNuevoTipo( numMatricula );
			numMatricula = new NumMatricula();
			lNumMatricula = mtMatricula.getAllTipoMatricula();
			JSFUtil.crearMensajeInfo("Nuevo tipo de matricula registrado");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}
	
	public void actionListenerActualizarTipoMatricula( NumMatricula mat) {
		try {
			mtMatricula.actualizarTipo(mat);;
			numMatricula = new NumMatricula();
			lNumMatricula = mtMatricula.getAllTipoMatricula();
			JSFUtil.crearMensajeInfo("El tipo de matricula seleccionado ha sido acualizado");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}
	
	public ManagerTipoMatriculas getMtMatricula() {
		return mtMatricula;
	}

	public void setMtMatricula(ManagerTipoMatriculas mtMatricula) {
		this.mtMatricula = mtMatricula;
	}

	public List<NumMatricula> getlNumMatricula() {
		return lNumMatricula;
	}

	public void setlNumMatricula(List<NumMatricula> lNumMatricula) {
		this.lNumMatricula = lNumMatricula;
	}

	public NumMatricula getNumMatricula() {
		return numMatricula;
	}

	public void setNumMatricula(NumMatricula numMatricula) {
		this.numMatricula = numMatricula;
	}

}
