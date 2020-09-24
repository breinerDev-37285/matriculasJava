package matriculas.model.managers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import matriculas.model.dto.MateriasMatriculadasDTO;
import matriculas.model.dto.MatriculaDTO;
import matriculas.model.entities.Estado;
import matriculas.model.entities.Materia;
import matriculas.model.entities.Matricula;
import matriculas.model.entities.MatriculaPK;
import matriculas.model.entities.NumMatricula;
import matriculas.model.entities.Registro;
import matriculas.model.entities.Semestre;
import matriculas.model.entities.Usuario;

@Stateless
@LocalBean
public class ManagerMatricula {
	@PersistenceContext
	private EntityManager em;

	public List<MatriculaDTO> findMatriculasBySemestre(String correo) {
		int idUsuario = findIDUsuarioByCorreo(correo).getId();

		List<Object[]> lMatriculas = em.createNativeQuery(
				"select m.nombre as nombre_materia,nm.nombre as tipo_matricula,e.nombre as nombre_estado,m.creditos\n"
						+ "from matricula ma inner join materia m on ma.materia=m.codigo\n"
						+ "inner join registro r on ma.registro=r.id inner join estado e on r.estado=e.id\n"
						+ "inner join num_matricula nm on ma.num_materia=nm.codigo where ma.estudiante=" + idUsuario)
				.getResultList();
		List<MatriculaDTO> listadoMatriculas = new ArrayList<MatriculaDTO>();
		MatriculaDTO matriculaDTO;
		String nMateria;
		String tipoMatricula;
		String estado;
		int creditos;
		for (int i = 0; i < lMatriculas.size(); i++) {
			nMateria = lMatriculas.get(i)[0].toString();
			tipoMatricula = lMatriculas.get(i)[1].toString();
			estado = lMatriculas.get(i)[2].toString();
			creditos = Integer.parseInt(lMatriculas.get(i)[3].toString());
			matriculaDTO = new MatriculaDTO(nMateria, tipoMatricula, estado, creditos);
			listadoMatriculas.add(matriculaDTO);
		}
		return listadoMatriculas;
	}

	public List<Semestre> findAllSemestres() {
		return em.createNamedQuery("Semestre.findAll", Semestre.class).getResultList();
	}

	public List<NumMatricula> findAllNumMatriculas() {
		return em.createNamedQuery("NumMatricula.findAll", NumMatricula.class).getResultList();
	}

	public List<Materia> findMateriasBySemestre(int idSemestre, List<Materia> lMaterias, int tamaño) {

		lMaterias = em.createQuery("select m from Materia m where m.semestreBean=" + idSemestre, Materia.class)
				.getResultList();

		return lMaterias;
	}

	public Usuario findIDUsuarioByCorreo(String correo) {
		return em.createQuery("select u from Usuario u where u.correo='" + correo + "'", Usuario.class)
				.getSingleResult();
	}

	public Semestre findSemestresById(int idSemestre) {
		return em.find(Semestre.class, idSemestre);
	}

	public Materia findMateriaById(int idMateria) {
		return em.find(Materia.class, idMateria);
	}

	public Usuario findUsuarioById(int idUsuario) {
		return em.find(Usuario.class, idUsuario);
	}

	public NumMatricula findNumMatriculaById(int idNumMatricula) {
		return em.find(NumMatricula.class, idNumMatricula);
	}

	public Estado findEstadoById(int idEstado) {
		return em.find(Estado.class, idEstado);
	}

	public List<MatriculaPK> agregarMateriaMatriculada(List<MatriculaPK> lMaterias, MatriculaPK matriculaPK)
			throws Exception {
		matriculaPK = validacionesMatriculaPK(matriculaPK);
		lMaterias.add(matriculaPK);
		return lMaterias;
	}

	public List<Materia> agregarMateria(List<Materia> lMaterias, int idMateria) {
		lMaterias.add(findMateriaById(idMateria));
		return lMaterias;
	}

	public List<Materia> eliminarMateria(List<Materia> lMaterias, int idMateria) {
		for (int i = 0; i < lMaterias.size(); i++) {
			if (lMaterias.get(i).getCodigo() == idMateria)
				lMaterias.remove(i);
		}
		return lMaterias;
	}

	public List<MatriculaPK> eliminarMateriaMatriculada(List<MatriculaPK> lMatriculaPK, int id) {
		for (int i = 0; i < lMatriculaPK.size(); i++) {
			if (lMatriculaPK.get(i).getMateria() == id)
				lMatriculaPK.remove(i);
		}

		return lMatriculaPK;
	}

	public void guardarMatricula(List<MatriculaPK> lMaterias) throws Exception {

		Estado estado = findEstadoById(1);
		Registro registro = new Registro();
		registro.setEstadoBean(estado);
		registro.setFecha(new Date());
		registro.setMatriculas(new ArrayList<Matricula>());
		Matricula matricula;
		for (int i = 0; i < lMaterias.size(); i++) {
			matricula = new Matricula();
			matricula.setId(lMaterias.get(i));
			matricula.setEstado(false);
			matricula.setRegistroBean(registro);
			registro.getMatriculas().add(matricula);
		}
		em.persist(registro);
	}

	public List<MateriasMatriculadasDTO> verMateriasMatriculadas(List<MatriculaPK> lMatriculaPK) {
		List<MateriasMatriculadasDTO> lMateriasMatriculadasDTO = new ArrayList<MateriasMatriculadasDTO>();
		for (int i = 0; i < lMatriculaPK.size(); i++) {

			Materia materia = new Materia();
			NumMatricula nMateria = new NumMatricula();
			MateriasMatriculadasDTO materiasMatriculadasDTO = new MateriasMatriculadasDTO();

			materia = findMateriaById(lMatriculaPK.get(i).getMateria());
			nMateria = findNumMatriculaById(lMatriculaPK.get(i).getNumMateria());

			materiasMatriculadasDTO.setId(materia.getCodigo());
			materiasMatriculadasDTO.setnSemestre(materia.getSemestreBean().getNombre());
			materiasMatriculadasDTO.setnMateria(materia.getNombre());
			materiasMatriculadasDTO.setnCreditos(materia.getCreditos());
			materiasMatriculadasDTO.setTipoMatricula(nMateria.getNombre());
			lMateriasMatriculadasDTO.add(materiasMatriculadasDTO);
		}
		return lMateriasMatriculadasDTO;
	}

	public MatriculaPK validacionesMatriculaPK(MatriculaPK matriculaPK) throws Exception {
		if (matriculaPK.getEstudiante() == null || matriculaPK.getEstudiante().equals(""))
			throw new Exception("El campo estudiante no tiende que estar vacio");
		if (matriculaPK.getMateria() == null || matriculaPK.getMateria().equals(""))
			throw new Exception("El campo materia no tiene que estar vacio");
		if (matriculaPK.getNumMateria() == null || matriculaPK.getNumMateria().equals(""))
			throw new Exception("El campo numero de matricula no tiene que estar vacio");
		return matriculaPK;
	}
}