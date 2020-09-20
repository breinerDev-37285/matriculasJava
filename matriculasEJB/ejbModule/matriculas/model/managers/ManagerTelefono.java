package matriculas.model.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import matriculas.model.dto.telefonoDTO;
import matriculas.model.entities.PeriodoAcademico;
import matriculas.model.entities.Persona;

/**
 * Session Bean implementation class ManagerTelefono
 */
@Stateless
@LocalBean
public class ManagerTelefono {

   @PersistenceContext
   EntityManager em;
   
   
   public List<Persona> getAllPersonas(){
	   return em.createNamedQuery("Persona.findAll", Persona.class).getResultList();
   }
   
    public List<telefonoDTO>  getAllTelefono () {
    	Query query = em.createNativeQuery("select t.codigo, p.nombres, p.apellidos, p.cedula, t.numero\n" + 
    			"from telefono t\n" + 
    			"inner join persona p\n" + 
    			"on t.persona=p.id"); 
    	
    	List<Object[]> loTelefonos = query.getResultList();
    	
    	List<telefonoDTO> lTelefonos = new ArrayList<telefonoDTO>();
    	telefonoDTO telefono;
    	
    	int codigo;
    	String  nombres, apellidos, cedula, numero;

    	
    	for (Object[] ftelefono : loTelefonos) {
    				
    				codigo = Integer.parseInt( ftelefono[0].toString()  );
    				nombres = ftelefono[1].toString();
    				apellidos = ftelefono[2].toString();
    				cedula = ftelefono[3].toString();
    				numero = ftelefono[4].toString();
    		
			  		telefono = new telefonoDTO(codigo, nombres, apellidos, cedula, numero);
			  		lTelefonos.add( telefono );
		}
    	
    	return lTelefonos;
    }

}
