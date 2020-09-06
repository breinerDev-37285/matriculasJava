package matriculas.model.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import matriculas.model.dto.usuariosDTO;
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

	public void registrarUsuario(String nombres, String apellidos, String cedula, String correo, String password,
			int rol, String ciudad, String callePrincipal, String calleSecundaria, String numCasa) throws Exception{
		
		if(  nombres.equals("") ) {
			throw new Exception("Por favor ingresa los nombres");
		}
		
		if(  apellidos.equals("") ) {
			throw new Exception("Por favor ingresa los apellidos");
		}
	
		if(  correo.equals("") ) {
			throw new Exception("Por favor ingresa el correo");
		}
		
		if(  password.equals("") ) {
			throw new Exception("Por favor ingresa la contraseña");
		}
		
		if(  rol == 0 ) {
			throw new Exception("Por favor seleccionar el rol de usuario");
		}
		
		System.out.println(  "rol ->  "+rol  );
		System.out.println(  "nombres  -> "+nombres  );
		
		
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
}
