package matriculas.model.managers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import matriculas.model.entities.Rol;

public class ManagerRol {

	@PersistenceContext
	private EntityManager em;
	
	public ManagerRol() {
		
	}
	
	public Rol findRolById(int rol) {
		return em.find(Rol.class, rol);
	}
	
	public void registrarRol(Rol roles,int rol) throws Exception{
		roles = validarRol(roles);
		
		
	}
	
	public Rol validarRol(Rol rol)throws Exception {
		if (rol.getNombre().equals("")) {
			throw new Exception("Por favor ingrese un rol");
		}
		return rol;
	}
	
	
	
}
