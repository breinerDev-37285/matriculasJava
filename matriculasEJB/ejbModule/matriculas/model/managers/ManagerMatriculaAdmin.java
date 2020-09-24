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

import matriculas.model.dto.matriculasAdminDTO;
import matriculas.model.entities.Estado;
import matriculas.model.entities.Matricula;
import matriculas.model.entities.MatriculaPK;
import matriculas.model.entities.Registro;

/**
 * Session Bean implementation class ManagerMatriculaAdmin
 */
@Stateless
@LocalBean
public class ManagerMatriculaAdmin {

	@PersistenceContext
	EntityManager em;

	public List<Estado> findAllEstados() {
		return em.createNamedQuery("Estado.findAll", Estado.class).getResultList();
	}

	public Estado findEstadoById(int estado) {
		return em.find(Estado.class, estado);
	}

	public List<Registro> findMatriculaById(String matricula) throws Exception {

		if (matricula.equals("")) {
			throw new Exception("Ingrese un codigo de matricula");
		}

		if (!isNumeric(matricula)) {
			throw new Exception("El codigo de matricula es erroneo");
		}

		int mat = Integer.parseInt(matricula);

		Registro reg = em.find(Registro.class, mat);
		if (reg == null) {
			throw new Exception("No se encontro una matricula con el id ingresado");
		}

		List<Registro> lRegistros = new ArrayList<Registro>();
		lRegistros.add(reg);
		return lRegistros;

	}

	public List<Registro> findAllMatriculas() {
		return em.createQuery(" select r from Registro r order by r.id  ", Registro.class).getResultList();
	}

	public List<Registro> findAllMatriculasByEstado(int estado) throws Exception {

		Estado est = findEstadoById(estado);

		if (est == null) {
			throw new Exception("Seleccione un estado valido");
		}

		return em.createQuery("select r from Registro r where r.estadoBean=" + est.getId(), Registro.class)
				.getResultList();
	}

	private Matricula findMatriculaPK(matriculasAdminDTO mater) throws Exception {

		MatriculaPK matriculapk = new MatriculaPK();
		matriculapk.setEstudiante(mater.getIdEstudiante());
		matriculapk.setMateria(mater.getIdMateria());
		matriculapk.setNumMateria(mater.getIdNumMatricula());

		Matricula matricula = findMatricula(matriculapk);

		if (matricula == null) {
			throw new Exception("No se encontro la matricula");
		}

		return matricula;
	}

	public void rechazarMaterias(matriculasAdminDTO mater) throws Exception {
		Matricula matricula = new Matricula();
		matricula = findMatriculaPK(mater);

		matricula.setEstado(false);
		em.merge(matricula);
	}

	public void aceptarMaterias(matriculasAdminDTO mater) throws Exception {
		Matricula matricula = new Matricula();
		matricula = findMatriculaPK(mater);

		matricula.setEstado(true);
		em.merge(matricula);
	}

	public void rechazarMatricula(int idregistro) throws Exception {
		Registro registro = findRegistro(idregistro);
		Estado estadoBean = findEstado(3);
		registro.setEstadoBean(estadoBean);

		changeEstadosMatriculas(registro, false);

		em.persist(registro);
	}

	private void changeEstadosMatriculas(Registro registro, boolean estado) {

		String consulta = "UPDATE Matricula m " + "SET m.estado = :updated_status "
				+ "WHERE m.registroBean = :current_status";

		em.createQuery(consulta).setParameter("updated_status", estado).setParameter("current_status", registro)
				.executeUpdate();

	}

	public void aceptarMatricula(int idregistro) throws Exception {
		Registro registro = findRegistro(idregistro);
		Estado estadoBean = findEstado(2);
		registro.setEstadoBean(estadoBean);
		changeEstadosMatriculas(registro, true);
		em.persist(registro);
	}

	private Registro findRegistro(int registro) {
		return em.find(Registro.class, registro);
	}

	private Estado findEstado(int estado) {
		return em.find(Estado.class, estado);
	}

	public List<matriculasAdminDTO> listMateriasByMatricula(int registro) throws Exception {

		String consulta = "select u.id as idusuario,m.codigo as idmatricula, nm.codigo as idnummateria, p.cedula, m.nombre, nm.nombre as matricula, ma.estado\n"
				+ "from matricula ma\n" + "inner join registro r\n" + "on ma.registro = r.id\n"
				+ "inner join usuario u\n" + "on ma.estudiante = u.id\n" + "inner join persona p\n"
				+ "on u.persona = p.id\n" + "inner join materia m\n" + "on ma.materia = m.codigo\n"
				+ "inner join num_matricula nm\n" + "on ma.num_materia = nm.codigo\n" + "where r.id=" + registro
				+ "order by m.codigo";

		Query query = em.createNativeQuery(consulta);

		List<Object[]> lObject = query.getResultList();
		List<matriculasAdminDTO> lmatriculas = new ArrayList<matriculasAdminDTO>();
		matriculasAdminDTO matriculas;

		int idEstud, idMateria, idNumMatricula;
		String cedula, materia, num_matricula, estado = "rechazado";

		for (Object[] objects : lObject) {

			idEstud = Integer.parseInt(objects[0].toString());
			idMateria = Integer.parseInt(objects[1].toString());
			idNumMatricula = Integer.parseInt(objects[2].toString());

			cedula = objects[3].toString();
			materia = objects[4].toString();
			num_matricula = objects[5].toString();

			if (Boolean.parseBoolean(objects[6].toString().toString())) {
				estado = "aprobado";
			}

			matriculas = new matriculasAdminDTO(idEstud, idMateria, idNumMatricula, cedula, materia, num_matricula,
					estado);
			lmatriculas.add(matriculas);
		}

		return lmatriculas;
	}

	public Matricula findMatricula(MatriculaPK matricula) {
		return em.find(Matricula.class, matricula);
	}

	public LocalDate ParseFecha(String fecha) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate f_fecha = LocalDate.parse(fecha);
		f_fecha.format(formatter);

		return f_fecha;
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