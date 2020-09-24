package matriculas.model.managers;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import matriculas.model.dto.semestreDTO;
import matriculas.model.entities.PeriodoAcademico;
import matriculas.model.entities.Semestre;

/**
 * Session Bean implementation class ManagerSemestre
 */
@Stateless
@LocalBean
public class ManagerSemestre {

    @PersistenceContext
    	EntityManager em;
    
    
    
    public void RegistrarSemestre(  Semestre semestre, int idPeriodo  )  throws Exception{
    	
    	List<PeriodoAcademico> lPeriodos = new ArrayList<PeriodoAcademico>();
    	PeriodoAcademico periodo = findPeriodo(idPeriodo);
    	
    	if  ( periodo == null  ) {
    		throw new Exception( "Por favor seleccione un periodo academico valido" );
    	}
    	
    	lPeriodos.add(periodo);
    	
    	semestre.setPeriodoAcademicos(lPeriodos);
    	semestre = validarSemestre(semestre);
    	
    	
    	em.persist(semestre);
    	
    }
    
    public Semestre validarSemestre( Semestre semestre  ) throws Exception {
    	
    	if ( semestre.getNombre() == null || semestre.getNombre().equals("")  ) {
    		throw new Exception( "Por favor ingrese un nombre" );
    	}
    	
    	
    	return semestre;
    }
    
    
    public PeriodoAcademico findPeriodo(int idPeriodo) {
    	return em.find(PeriodoAcademico.class, idPeriodo);
    }
    

    
    public void actualizarSemestre(semestreDTO sem) throws Exception {
    	Semestre semestre = findSemestreById(sem.getId());
    	semestre.setNombre(sem.getNombre());
    	
    	em.merge(semestre);
    }
    
    public Semestre findSemestreById(int idSemestre){
    	return em.find(Semestre.class, idSemestre);
    }
    
    public List<semestreDTO>  getAllSemestres () {
    	Query query = em.createNativeQuery("select s.id, s.nombre, pd.fecha_inicio, pd.fecha_fin\n" + 
    			"from semestre s\n" + 
    			"inner join semestre_periodo sm\n" + 
    			"on s.id=sm.semestre\n" + 
    			"inner join periodo_academico pd\n" + 
    			"on sm.periodo=pd.id\n" + 
    			"order by(pd.fecha_inicio) asc");
   
    	List<Object[]> listSemestres = query.getResultList();
    	
    	List<semestreDTO> listSemestre = new ArrayList<semestreDTO>();
    	semestreDTO semestre;
    	
    	int id = 0;
    	String nombre = null;
    	LocalDate fecha_inicio = null;
    	LocalDate fecha_fin = null;
    	
    	
    	for (Object[] fsemestre : listSemestres) {
    		
    			id = Integer.parseInt( fsemestre[0].toString() ) ; 
			  	nombre = fsemestre[1].toString();
			  	fecha_inicio = ParseFecha( fsemestre[2].toString() );
			 	fecha_fin = ParseFecha( fsemestre[3].toString() );
			 	

			 	semestre = new semestreDTO(id,nombre, fecha_inicio, fecha_fin);
			 	listSemestre.add(semestre);
		}
    	
    	return listSemestre;    	
    }
    
    public List<PeriodoAcademico> findAllPeriodo() {
    	return em.createNamedQuery("PeriodoAcademico.findAll",PeriodoAcademico.class).getResultList();
    }
    
    
    public  LocalDate ParseFecha(String fecha)  {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	LocalDate f_fecha = LocalDate.parse(fecha);
    	f_fecha.format(formatter);
    	  	
        return f_fecha;
    }

}
