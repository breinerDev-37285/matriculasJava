package matriculas.model.managers;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import matriculas.model.entities.PeriodoAcademico;
import matriculas.model.entities.Semestre;

/**
 * Session Bean implementation class ManagerSemestre
 */
@Stateless
@LocalBean
public class ManagerSemestre {
	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public ManagerSemestre() {
    }
    
    public Semestre findSemestreyById(int  )
    
    public void registrarSemestre(Semestre semestre, int nivel,PeriodoAcademico periodo )throws Exception{
    	
    	    	
    }
    
    
    

//    public List<Semestre> findAllSemestres(){
//    	return em.createNamedQuery("Semestre.findAll", Semestre.class).getResultList();
//    	
//    }
    
}
