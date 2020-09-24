package matriculas.model.managers;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import matriculas.model.entities.NumMatricula;

/**
 * Session Bean implementation class ManagerTipoMatriculas
 */
@Stateless
@LocalBean
public class ManagerTipoMatriculas {

    	@PersistenceContext
    	EntityManager em;
    	
    	public  List<NumMatricula  > getAllTipoMatricula() {
    			return em.createNamedQuery( "NumMatricula.findAll"  ,NumMatricula.class ).getResultList();
    	}

    	public void registrarNuevoTipo( NumMatricula mat ) throws Exception {
    		mat = validarTipo(mat);
    		
    		System.out.println( mat.getNombre() );
    		em.persist(mat);
    	}
    	
    	public NumMatricula validarTipo(NumMatricula mat) throws Exception {
    		
    		if (  mat.getNombre().equals("")  ) {
    				throw new Exception("Por favor ingrese un nombre");
    		}
    		
    		return mat;
    	}
    	
    	
    	public void actualizarTipo(NumMatricula mat) throws Exception {
    			mat = validarTipo(mat);
    			em.merge(mat);
    	}
    	

}
