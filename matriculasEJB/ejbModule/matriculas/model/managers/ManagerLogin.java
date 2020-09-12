package matriculas.model.managers;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import matriculas.model.entities.Rol;

/**
 * Session Bean implementation class ManagerLogin
 */
@Stateless
@LocalBean
public class ManagerLogin {

	@PersistenceContext
    private EntityManager em;
    
    public String verificarAcceso(String email, String password) throws Exception {
    	
	    	String link = "";
	    	
			StoredProcedureQuery query = em.createStoredProcedureQuery("verificarUsuario") 
					.registerStoredProcedureParameter("correo",String.class, ParameterMode.IN) 
					.registerStoredProcedureParameter("password",String.class, ParameterMode.IN);
			query.setParameter("correo", email);
			query.setParameter("password",password);
			query.execute();
			
			List<Object[]> getUsuario = query.getResultList();
			
			String rol = getUsuario.get(0)[1].toString();
		
			switch (  rol  ) {
		    		case "1": link = "estudiante.xhtml";  break;
		    		case "2": link = "usuarios.xhtml"; break;
			}
	    		
	       return link;
    }
    
    public List<Rol> obtenerTodosRoles() throws Exception {
    		List<Rol> roles = em.createQuery("SELECT r FROM Rol r",Rol.class).getResultList();
    		return roles;
    }

}
