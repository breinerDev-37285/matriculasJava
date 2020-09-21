package matriculas.controllers;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import matriculas.model.dto.MateriasMatriculadasDTO;
import matriculas.model.dto.MatriculaDTO;
import matriculas.model.entities.Materia;
import matriculas.model.entities.Matricula;
import matriculas.model.entities.MatriculaPK;
import matriculas.model.entities.NumMateria;
import matriculas.model.entities.Semestre;
import matriculas.model.managers.ManagerMatricula;

@Named
@SessionScoped
public class BeanMatricula implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerMatricula mMatricula;
	private List<Semestre> lSemestres;
	private List<Materia> lMaterias;
	private List<MatriculaDTO> lMatriculas;
	private List<NumMateria> lNumMateria;
	private List<MatriculaPK> lMatriculaPKs;
	private List<MateriasMatriculadasDTO> lMateriasMatriculadas;
	private MatriculaPK matriculaPK;
	private int idUsuario;
	private int idSemestre;

	@PostConstruct
	public void init() {
		idUsuario = 2;
		lSemestres = mMatricula.findAllSemestres();
		matriculaPK = new MatriculaPK();
		lMatriculaPKs = new ArrayList<MatriculaPK>();
	}

	public void materiasBySemestre() {
		lMaterias = mMatricula.findMateriasBySemestre(idSemestre);
		lNumMateria = mMatricula.findAllNumMaterias();
	}

	public void matriculasBySemestre() {
		lMatriculas = mMatricula.findMatriculasBySemestre(idUsuario, idSemestre);
	}

	public void agregarMaterias() {
		try {
			matriculaPK.setEstudiante(idUsuario);
			lMatriculaPKs = mMatricula.agregarMateriaMatriculada(lMatriculaPKs, matriculaPK);
			lMateriasMatriculadas = mMatricula.verMateriasMatriculadas(lMatriculaPKs);
			lMaterias = mMatricula.eliminarMateria(lMaterias, matriculaPK.getMateria());
			matriculaPK = new MatriculaPK();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public void eliminarMateriaMatriculada(int id) {
		lMatriculaPKs = mMatricula.eliminarMateriaMatriculada(lMatriculaPKs, id);
		lMateriasMatriculadas = mMatricula.verMateriasMatriculadas(lMatriculaPKs);
		lMaterias = mMatricula.agregarMateria(lMaterias, id);
	}

	public void guardarMatricula() {
		try {
			mMatricula.guardarMatricula(lMatriculaPKs);
			matriculaPK = new MatriculaPK();
			materiasBySemestre();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public List<Semestre> getlSemestres() {
		return lSemestres;
	}

	public void setlSemestres(List<Semestre> lSemestres) {
		this.lSemestres = lSemestres;
	}

	public List<Materia> getlMaterias() {
		return lMaterias;
	}

	public void setlMaterias(List<Materia> lMaterias) {
		this.lMaterias = lMaterias;
	}

	public List<MatriculaDTO> getlMatriculas() {
		return lMatriculas;
	}

	public void setlMatriculas(List<MatriculaDTO> lMatriculas) {
		this.lMatriculas = lMatriculas;
	}

	public List<NumMateria> getlNumMateria() {
		return lNumMateria;
	}

	public void setlNumMateria(List<NumMateria> lNumMateria) {
		this.lNumMateria = lNumMateria;
	}

	public List<MatriculaPK> getlMatriculaPKs() {
		return lMatriculaPKs;
	}

	public void setlMatriculaPKs(List<MatriculaPK> lMatriculaPKs) {
		this.lMatriculaPKs = lMatriculaPKs;
	}

	public MatriculaPK getMatriculaPK() {
		return matriculaPK;
	}

	public void setMatriculaPK(MatriculaPK matriculaPK) {
		this.matriculaPK = matriculaPK;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdSemestre() {
		return idSemestre;
	}

	public void setIdSemestre(int idSemestre) {
		this.idSemestre = idSemestre;
	}

	public List<MateriasMatriculadasDTO> getlMateriasMatriculadas() {
		return lMateriasMatriculadas;
	}

	public void setlMateriasMatriculadas(List<MateriasMatriculadasDTO> lMateriasMatriculadas) {
		this.lMateriasMatriculadas = lMateriasMatriculadas;
	}
}
