package matriculas.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import matriculas.model.entities.PeriodoAcademico;
import matriculas.model.managers.ManagerPeriodoAcademico;

@Named
@SessionScoped
public class BeanPeriodoAcademico implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerPeriodoAcademico mPeriodoAcademico;
	private List<PeriodoAcademico> listaPeriodoAcademico;
	private PeriodoAcademico periodoAcademico;

	@PostConstruct
	public void init() {
		periodoAcademico = new PeriodoAcademico();
		listaPeriodoAcademico = mPeriodoAcademico.findAllPeriodoAcademico();
	}

	public void registrarPeriodoAcademico() {
		try {
			mPeriodoAcademico.registrarPeriodoAcademico(periodoAcademico);
			listaPeriodoAcademico = mPeriodoAcademico.findAllPeriodoAcademico();
			JSFUtil.crearMensajeInfo("Se registro un nuevo periodo academico");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public void actualizarPeriodoAcademico(PeriodoAcademico periodoAcademico) {
		try {
			
			
			mPeriodoAcademico.actualizarPeriodoAcademico(periodoAcademico);
			listaPeriodoAcademico = mPeriodoAcademico.findAllPeriodoAcademico();
			JSFUtil.crearMensajeInfo("Se actualizo el periodo academico");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}
	
	public void eliminarPeriodoAcademico(PeriodoAcademico periodoAcademico) {
		
		try {
			
			mPeriodoAcademico.eliminarPeriodoAcademico(periodoAcademico);
			listaPeriodoAcademico = mPeriodoAcademico.findAllPeriodoAcademico();
			JSFUtil.crearMensajeInfo("Se actualizo el periodo academico");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
		
	}

	public List<PeriodoAcademico> getListaPeriodoAcademico() {
		return listaPeriodoAcademico;
	}

	public void setListaPeriodoAcademico(List<PeriodoAcademico> listaPeriodoAcademico) {
		this.listaPeriodoAcademico = listaPeriodoAcademico;
	}

	public PeriodoAcademico getPeriodoAcademico() {
		return periodoAcademico;
	}

	public void setPeriodoAcademico(PeriodoAcademico periodoAcademico) {
		this.periodoAcademico = periodoAcademico;
	}

}
