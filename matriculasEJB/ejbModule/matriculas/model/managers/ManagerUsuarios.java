package matriculas.model.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import matriculas.model.dto.usuariosDTO;
import matriculas.model.entities.Persona;
import matriculas.model.entities.Usuario;

/**
 * Session Bean implementation class ManagerUsuarios
 */
@Stateless
@LocalBean
public class ManagerUsuarios {

	@PersistenceContext
	private EntityManager em;

	public ManagerUsuarios() {
	}

	public void registrarPersona(Persona persona, Usuario user ) throws Exception{
		
		if ( persona == null ) {
			persona = new Persona();
		}
		
		List<Usuario> listUser = new ArrayList<Usuario>();

		if(  persona.getNombres() .equals("") ) {
			throw new Exception("Por favor ingresa los nombres");
		}
		
		if(  persona.getApellidos(). equals("") ) {
			throw new Exception("Por favor ingresa los apellidos");
		}
	
		if(   persona.getCedula().equals("") ) {
			throw new Exception("Por favor ingresa la cedula");
		}
		
		if(   persona.getCedula().length()    != 10 ||  !isNumeric(persona.getCedula()) ) {
			throw new Exception("La cedula debe constar de 10 caracteres numericos");
		}
	
		if ( buscarPersonaPorCedula(persona.getCedula()).size() != 0 ) {
			throw new Exception("la cedula ingresada ya se encuentra registrada en el sistema");
		}	
		
		
		listUser.add(user);
	    persona.setUsuarios(listUser);
	    
	    em.persist(persona);
	}
	
	
	public Usuario registrarUsuarios( Usuario user ) throws Exception {
		
		if(  user.getCorreo().equals("") ) {
			throw new Exception("Por favor ingresa el correo");
		}
		
		if( user.getPassword().equals("") ) {
			throw new Exception("Por favor ingresa una contraseña");
		}
		
		if( user.getRol()  == 0) {
			throw new Exception("Por favor selecciona un rol");
		}
		
	    return user;
	}
	
	
//	private void agregarUsuario(int rol, int persona, String  correo, String password) {
//		Usuario user = new Usuario();
//		user.setCorreo(correo);
//		user.setper
//		em.persist(user);
//	}
//	
//	private void agregarPersona(String nombres, String apellidos, String cedula, String ciudad, String calleP, String calleS, String numCasa) {
//		
//	}
//	
	public List<Object[]> buscarPersonaPorCedula(String cedula) {
			Query q = em.createNativeQuery("select * from persona where cedula='"+cedula+"'");
		   List<Object[]> u = q.getResultList();
		   return u;
	}
	

	public List<usuariosDTO> obtenerTodoslosUsuarios() throws Exception {
		
		String consulta = "select p.nombres, p.apellidos, u.correo,p.cedula, u.estado, p.dir_ciudad as ciudad, \n"+
				"p.dir_calle_principal as calleP, p.dir_calle_secundaria as calleS, p.dir_num_casa as numCasa\n" + 
				"from usuario u\n" + 
				"inner join persona p\n" + 
				"on u.persona = p.id;";
		
		Query q = em.createNativeQuery(consulta);
		List<Object[]> fu = q.getResultList();
		List<usuariosDTO> usuarios = new ArrayList<usuariosDTO>();
		
		for (int i = 0; i < fu.size(); i++) {
			
			String nombres = fu.get(i)[0].toString();
			String apellidos = fu.get(i)[1].toString();
			String correo = fu.get(i)[2].toString();
			Object cedula =  fu.get(i)[3];
			boolean estado = Boolean.parseBoolean( fu.get(i)[4].toString() );
			Object ciudad =  fu.get(i)[5];
			Object calleP = fu.get(i)[6];
			Object calleS = fu.get(i)[7];
			Object numCasa = fu.get(i)[8];
			
			String est = "inactivo";
			
			if( estado ) est = "activo";
			if ( cedula == null ) cedula = "---------";
			if ( ciudad == null ) ciudad =  "---------";
			if ( calleP == null ) calleP =  "---------";
			if ( calleS == null ) calleS =  "---------";
			if ( numCasa == null ) numCasa =  "---------";
			
			usuariosDTO userdf = new usuariosDTO(nombres, apellidos, cedula, correo, est, ciudad, calleP, calleS, numCasa);
			
			usuarios.add(userdf);
		}
		
		return usuarios;
	}
	
	private  boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
}
