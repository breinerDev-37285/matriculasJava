package matriculas.model.managers;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import matriculas.model.entities.Rol;
import matriculas.model.entities.Usuario;

/**
 * Session Bean implementation class ManagerLogin
 */
@Stateless
@LocalBean
public class ManagerLogin {

	@PersistenceContext
    private EntityManager em;
    
    public  List<Object> verificarAcceso(String email, String password) throws Exception {
    	
	    	String link = "";
	 
	    	List<Usuario> usuarios = em.createNamedQuery("Usuario.findAll",Usuario.class).getResultList();
	    	Usuario usuario = null;
	    	
	    	for (Usuario user : usuarios) {
	    			if (  user.getCorreo().equals(email)  && user.getPassword().equals(getMd5(password))   )  {
	    				usuario = user;
	    			}
	    	}
	    	
	    	if(  usuario == null ) {
	    			throw new Exception("Credenciales invalidas!");
	    	}
	    	
			if  ( !usuario.getEstado()  ) {
				throw new Exception( "El usuario se encuentra inactivo" );
			}
			
		
			switch (  usuario.getRolBean().getId()  ) {
					case 1: link = "matriculas.xhtml";  break;
					case 2: link = "usuarios.xhtml"; break;
			}	
			
			 List<Object> estado = new ArrayList<Object>();
			 
			 estado.add(link);
			 estado.add(usuario.getRolBean().getId());
			
			
			return estado;
    }
    
    public List<Rol> obtenerTodosRoles() throws Exception {
    		List<Rol> roles = em.createQuery("SELECT r FROM Rol r",Rol.class).getResultList();
    		return roles;
    }
    
    public  String getMd5(String input)  throws Exception { 
        try { 
  
            MessageDigest md = MessageDigest.getInstance("MD5"); 
            byte[] messageDigest = md.digest(input.getBytes()); 
            BigInteger no = new BigInteger(1, messageDigest); 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  
  
        catch (Exception e) { 
           throw new Exception(e); 
        } 
    } 
    
  

}
