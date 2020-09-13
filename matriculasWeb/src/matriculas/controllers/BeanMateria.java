package matriculas.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import matriculas.model.entities.Materia;
import matriculas.model.entities.Semestre;
import matriculas.model.managers.ManagerMaterias;

@Named
@SessionScoped
public class BeanMateria implements Serializable {

	private static final long serialVersionUID = 1L;

	public BeanMateria() {  }
	
	@EJB
	private ManagerMaterias mMateria;
	private List<Materia> materias;
	private List<Semestre> semestres;
	private Materia materia;
	private int semestre;
	
	@PostConstruct
	public void init () {
		materias = mMateria.findAllmaterias();
		semestres = mMateria.findAllSemestres();
		materia = new Materia();
	}
	
	public void actionListenerRegistrarMateria( )  {
		try {
			mMateria.registrarNuevaMateria(materia, semestre);
			materia = new Materia();
			materias = mMateria.findAllmaterias();
			JSFUtil.crearMensajeInfo("Materia agregada correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getLocalizedMessage());
		}
	}
	
	public void actionListenerActualizarMateria( Materia materia )  {
		try {
			mMateria.actualizarMateria(materia);
			materia = new Materia();
			materias = mMateria.findAllmaterias();
			JSFUtil.crearMensajeInfo("Materia agregada correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getLocalizedMessage());
		}
	}

	public List<Materia> getMaterias() {
		return materias;
	}

	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public List<Semestre> getSemestres() {
		return semestres;
	}

	public void setSemestres(List<Semestre> semestres) {
		this.semestres = semestres;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}
	
	
	

}
