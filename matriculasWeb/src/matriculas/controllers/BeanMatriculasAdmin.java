package matriculas.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.MoveEvent;

import matriculas.model.dto.matriculasAdminDTO;
import matriculas.model.entities.Estado;
import matriculas.model.entities.Registro;
import matriculas.model.managers.ManagerMatriculaAdmin;

@Named
@SessionScoped
public class BeanMatriculasAdmin implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerMatriculaAdmin mMatriculasAdmin;
	private List<Estado> lEstados;
	private int estado;
	private String matricula;
	private int filtro;
	private Estado est;
	private List<Registro> matriculas;
	private List<matriculasAdminDTO> lMaterias;
	private int registroestado;
	
	
	public BeanMatriculasAdmin() { }
	
	@PostConstruct
	public void init() {
			registroestado = 0;
			lEstados = mMatriculasAdmin.findAllEstados();
			matriculas = mMatriculasAdmin.findAllMatriculas()	;
			lMaterias = new ArrayList<matriculasAdminDTO>();
	}
	
	public void actionListenerFindFiltro() {
		try {
			

			
			switch (  filtro  ) {
						case 1:
							matriculas = mMatriculasAdmin.findAllMatriculasByEstado(estado);
							
							System.out.print(  matriculas.size()  );
							
							
						break;
						
						case 2:
							matriculas = mMatriculasAdmin.findMatriculaById(matricula);
						break;
			
						default:
							JSFUtil.crearMensajeWarning(" Por favor seleccione una opcion valida");
						break;
				}
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void actionListenerAceptarMatricula( int idregistro) {
			try {
					mMatriculasAdmin.aceptarMatricula(idregistro);
					JSFUtil.crearMensajeInfo("La matricula ha sido aceptada");
					matriculas = mMatriculasAdmin.findAllMatriculas()	;
			} catch (Exception e) {
					JSFUtil.crearMensajeError(e.getMessage());
					e.printStackTrace();
			}
	}
	
	public void actionListenerRechazarMatricula( int idregistro ) {
			try {
					mMatriculasAdmin.rechazarMatricula(idregistro);
					JSFUtil.crearMensajeInfo("La matricula ha sido rechazada");
					matriculas = mMatriculasAdmin.findAllMatriculas()	;
			} catch (Exception e) {
					JSFUtil.crearMensajeError(e.getMessage());
					e.printStackTrace();
			}
	}
	
	
	public void actionListenerVerMaterias(int registro ) {
		try {
			registroestado = registro;
			lMaterias =  mMatriculasAdmin.listMateriasByMatricula(registroestado);
			
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public void actionListenerRechazarMateria( matriculasAdminDTO mater ) {
		try {
				mMatriculasAdmin.rechazarMaterias(mater);
				JSFUtil.crearMensajeWarning("La materia ha sido rechazada");
				
				lMaterias =  mMatriculasAdmin.listMateriasByMatricula(registroestado);
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void actionListeneraceptarMateria( matriculasAdminDTO mater ) {
		try {
			mMatriculasAdmin.aceptarMaterias(mater);
			JSFUtil.crearMensajeInfo("La materia ha sido aprovada");
			lMaterias =  mMatriculasAdmin.listMateriasByMatricula(registroestado);
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	

	public List<Estado> getlEstados() {
		return lEstados;
	}

	public void setlEstados(List<Estado> lEstados) {
		this.lEstados = lEstados;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getFiltro() {
		return filtro;
	}

	public void setFiltro(int filtro) {
		this.filtro = filtro;
	}

	public Estado getEst() {
		return est;
	}

	public void setEst(Estado est) {
		this.est = est;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public List<Registro> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Registro> matriculas) {
		this.matriculas = matriculas;
	}

	public List<matriculasAdminDTO> getlMaterias() {
		return lMaterias;
	}

	public void setlMaterias(List<matriculasAdminDTO> lMaterias) {
		this.lMaterias = lMaterias;
	}

	public int getRegistroestado() {
		return registroestado;
	}

	public void setRegistroestado(int materiaestado) {
		this.registroestado = materiaestado;
	}

	
	
}