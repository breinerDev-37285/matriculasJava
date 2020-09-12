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
import matriculas.model.entities.Rol;
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

	public Usuario findUserById(int usuario) {
		return em.find(Usuario.class, usuario);
	}

	public Rol findRolById(int rol) {
		return em.find(Rol.class, rol);
	}

	public void registrarUsuario(Usuario user, int rol, Persona persona) throws Exception {

		if (rol <= 0) {
			throw new Exception("Por favor seleccione un rol valido");
		}

		user = validarUsuario(user);
		persona = validarPersona(persona);

		Rol rolBean = findRolById(rol);
		user.setRolBean(rolBean);
		user.setEstado(true);

		if (persona.getUsuarios() == null) {
			persona.setUsuarios(new ArrayList<Usuario>());
		}

		persona.getUsuarios().add(user);
		user.setPersonaBean(persona);

		em.persist(persona);
	}

	public Usuario validarUsuario(Usuario user) throws Exception {

		if (user.getCorreo().equals("")) {
			throw new Exception("Por favor ingrese un correo");
		}

		if (user.getPassword().equals("")) {
			throw new Exception("Por favor ingrese una contraseña");
		}

		if (user.getPassword().length() < 8) {
			throw new Exception("La contraseña debe contener almenos 8 caracteres");
		}

		return user;
	}

	public Persona validarPersona(Persona persona) throws Exception {

		if (persona.getNombres().equals("")) {
			throw new Exception("Por favor ingrese los nombres completos");
		}

		if (persona.getApellidos().equals("")) {
			throw new Exception("Por favor ingrese los apellidos completos");
		}

		if (persona.getCedula().length() < 10 || !isNumeric(persona.getCedula())) {
			throw new Exception("La cedula debe contener 10 caracteres numéricos");
		}

		return persona;
	}

	public List<Object[]> buscarPersonaPorCedula(String cedula) {
		Query q = em.createNativeQuery("select * from persona where cedula='" + cedula + "'");
		List<Object[]> u = q.getResultList();
		return u;
	}

	public List<usuariosDTO> obtenerTodoslosUsuarios() throws Exception {

		String consulta = "select p.nombres, p.apellidos, u.correo,p.cedula, u.estado, p.dir_ciudad as ciudad, \n"
				+ "p.dir_calle_principal as calleP, p.dir_calle_secundaria as calleS, p.dir_num_casa as numCasa\n"
				+ "from usuario u\n" + "inner join persona p\n" + "on u.persona = p.id;";

		Query q = em.createNativeQuery(consulta);
		List<Object[]> fu = q.getResultList();
		List<usuariosDTO> usuarios = new ArrayList<usuariosDTO>();

		for (int i = 0; i < fu.size(); i++) {

			String nombres = fu.get(i)[0].toString();
			String apellidos = fu.get(i)[1].toString();
			String correo = fu.get(i)[2].toString();
			Object cedula = fu.get(i)[3];
			boolean estado = Boolean.parseBoolean(fu.get(i)[4].toString());
			Object ciudad = fu.get(i)[5];
			Object calleP = fu.get(i)[6];
			Object calleS = fu.get(i)[7];
			Object numCasa = fu.get(i)[8];

			usuariosDTO userdf = new usuariosDTO(nombres, apellidos, cedula, correo, estado, ciudad, calleP, calleS,
					numCasa);

			usuarios.add(userdf);
		}

		return usuarios;
	}
	

	private boolean isNumeric(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
}
