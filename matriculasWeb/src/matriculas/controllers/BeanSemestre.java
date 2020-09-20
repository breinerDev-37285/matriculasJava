package matriculas.controllers;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import matriculas.model.dto.semestreDTO;
import matriculas.model.entities.PeriodoAcademico;
import matriculas.model.entities.Semestre;
import matriculas.model.managers.ManagerSemestre;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanSemestre implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ManagerSemestre mSemestre;
	private List<semestreDTO> lSemestres;
	private List<PeriodoAcademico> lPeriodos;
	private Semestre semestre;
	private int periodo;
	
	@PostConstruct
	private void init() {
		lSemestres = mSemestre.getAllSemestres();
		lPeriodos = mSemestre.findAllPeriodo();
		semestre = new Semestre();
	}
	
	public void actionListenerActualizarSemestre(semestreDTO sem) {
		try {
			mSemestre.actualizarSemestre(sem);
			lSemestres = mSemestre.getAllSemestres();
			JSFUtil.crearMensajeInfo("el semestre ha sido actualizado con exito");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}
	
	public void actionListenerRegistrarSemestre() {
			try {
				mSemestre.RegistrarSemestre(semestre, periodo);
				lSemestres = mSemestre.getAllSemestres();
				semestre = new Semestre();
				JSFUtil.crearMensajeInfo("nevo semestre registrado con exito");
			} catch (Exception e) {
				JSFUtil.crearMensajeError(e.getMessage());
			}
	}
	
	public ManagerSemestre getmSemestre() {
		return mSemestre;
	}
	public void setmSemestre(ManagerSemestre mSemestre) {
		this.mSemestre = mSemestre;
	}
	public List<semestreDTO> getlSemestres() {
		return lSemestres;
	}
	public void setlSemestres(List<semestreDTO> lSemestres) {
		this.lSemestres = lSemestres;
	}

	public List<PeriodoAcademico> getlPeriodos() {
		return lPeriodos;
	}

	public void setlPeriodos(List<PeriodoAcademico> lPeriodos) {
		this.lPeriodos = lPeriodos;
	}

	public Semestre getSemestre() {
		return semestre;
	}

	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}

	public int getPeriodo() {
		return periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	
}
