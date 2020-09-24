package matriculas.model.managers;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import matriculas.model.entities.PeriodoAcademico;
import matriculas.model.entities.Rol;

/**
 * Session Bean implementation class ManagerRol
 */
@Stateless
@LocalBean
public class ManagerRol {

		   @PersistenceContext
		   EntityManager em;
		   
		   public List<Rol> findAllRoles() {
			   return em.createQuery("SELECT r FROM Rol r", Rol.class).getResultList();
		   }
		   
		   public void registrarNuevoRol( Rol rol ) throws Exception {
			   	rol = validarRol(rol);
			   	Rol role = new Rol(); 
			   	role = rol;
			   	
			   	em.persist(role);			   	
		   }
		   
		   private Rol validarRol( Rol rol) throws Exception {
			   
			   if (  rol.getNombre().equals("")  ) {
				   	throw new Exception("Por favor ingrese un nombre que defina el tipo de rol");
			   }
			   
			   return rol;
		   }
		   
		   private Rol findRolById(int idRol) throws Exception {
			   	 return em.find(Rol.class, idRol);
		   }
		   
		   public void actualizarRol( Rol rol ) throws Exception{
			 	rol = validarRol(rol);
			    Rol role = findRolById(rol.getId());
			   role.setNombre(rol.getNombre());
			    em.merge(role);
		   }
		   
		   public void eliminarRol( Rol rol ) throws Exception {
			    Rol role = findRolById(rol.getId());
			    em.remove(role);
		   }

}
