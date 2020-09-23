package matriculas.model.managers;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import matriculas.model.entities.Materia;
import matriculas.model.entities.Semestre;

/**
 * Session Bean implementation class ManagerMaterias
 */
@Stateless
@LocalBean
public class ManagerMaterias {
	
	@PersistenceContext
	EntityManager em;
 
	
	public List<Materia> findAllmaterias() {
		return em.createQuery("select m from Materia m" , Materia.class).getResultList();
	}
	
	public List<Semestre> findAllSemestres(){
		return em.createQuery("select s from Semestre s", Semestre.class).getResultList();
	}
	
	public Semestre validarSemestre(Semestre semestre) throws Exception {
		
		if( semestre == null ) {
				throw new Exception("Por favor seleccione un semestre valido");
		}
		
		return semestre;
	}
	
	public void registrarNuevaMateria(Materia materia, int id_semestre) throws Exception {
		
		materia = validarMateria(materia, id_semestre);
		Semestre semestre = em.find(Semestre.class,id_semestre );
		semestre = validarSemestre(semestre);
		
		materia.setSemestreBean(semestre);
		
		em.persist(materia);
	}
	
	public void actualizarMateria(Materia materia) throws Exception {
		
		int idSemestre = Integer.parseInt(materia.getSemestreBean().getNombre());
		materia = validarMateria(materia, idSemestre);
		Semestre semestre = em.find(Semestre.class,idSemestre );
		
		materia.setSemestreBean(semestre);
		em.merge(materia);	
	}
	
	
	public Materia validarMateria( Materia materia, int semestre) throws Exception {
		
		if (  materia.getNombre() == null || materia.getNombre().equals("")  ) {
			throw new Exception( "Por favor incluya el nombre de la materia" );
		}
		
		if ( materia.getCreditos() == 0 || 
				materia.getCreditos() == null  || 
				!isNumeric(materia.getCreditos().toString()) ||
				materia.getCreditos() <=  0 || 
				materia.getCreditos() > 5
				) {
			throw new Exception( "Por favor ingrese los creditos validos" );
		}
		
		if ( semestre <= 0 ||  semestre > 10) {
			throw new Exception( "Por favor seleccione un semestre valido" );
		}
		
		return materia;
	}
	
	

	private boolean isNumeric(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
}
