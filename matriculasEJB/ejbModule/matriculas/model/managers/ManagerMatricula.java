package matriculas.model.managers;

import java.sql.SQLException;
import java.util.ArrayList;
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
import matriculas.model.entities.NumMateria;
import matriculas.model.entities.Registro;
import matriculas.model.entities.Semestre;
import matriculas.model.entities.Usuario;

@Stateless
@LocalBean
public class ManagerMatricula {
	@PersistenceContext
	private EntityManager em;

	public List<MatriculaDTO> findMatriculasBySemestre(int idUsuario, int idSemestre) {
		List<Object[]> lMatriculas = em.createNativeQuery(
				"select e.nombre as nombre_estado,nm.nombre as tipo_matricula, m.nombre as nombre_materia"
						+ "from matricula ma inner join materia m on ma.materia=m.codigo"
						+ "inner join registro r on ma.registro=r.id inner join estado e"
						+ "on r.estado=e.id inner join num_matricula nm\n"
						+ "on ma.num_materia=nm.codigo where ma.estudiante=" + idUsuario + " and m.semestre="
						+ idSemestre)
				.getResultList();
		List<MatriculaDTO> listadoMatriculas = new ArrayList<MatriculaDTO>();
		MatriculaDTO matriculaDTO;
		String nMateria;
		String tipoMatricula;
		String estado;
		for (int i = 0; i < lMatriculas.size(); i++) {
			nMateria = lMatriculas.get(i)[0].toString();
			tipoMatricula = lMatriculas.get(i)[1].toString();
			estado = lMatriculas.get(i)[2].toString();
			matriculaDTO = new MatriculaDTO(nMateria, tipoMatricula, estado);
			listadoMatriculas.add(matriculaDTO);
		}
		return listadoMatriculas;
	}

	public List<Semestre> findAllSemestres() {
		return em.createNamedQuery("Semestre.findAll", Semestre.class).getResultList();
	}

	public List<NumMateria> findAllNumMaterias() {
		return em.createNamedQuery("NumMateria.findAll", NumMateria.class).getResultList();
	}

	public List<Materia> findMateriasBySemestre(int idSemestre) {
		return em.createQuery("select m from Materia m where m.semestreBean=" + idSemestre, Materia.class)
				.getResultList();
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

	public NumMateria findNumMateriaById(int idNumMateria) {
		return em.find(NumMateria.class, idNumMateria);
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
		registro.setMatriculas(new ArrayList<Matricula>());
		Matricula matricula;
		for (int i = 0; i < lMaterias.size(); i++) {
			matricula = new Matricula();
			matricula.setId(lMaterias.get(i));
			matricula.setEstado(false);
			registro.getMatriculas().add(matricula);
		}
		em.persist(registro);
	}

	public List<MateriasMatriculadasDTO> verMateriasMatriculadas(List<MatriculaPK> lMatriculaPK) {
		List<MateriasMatriculadasDTO> lMateriasMatriculadasDTO = new ArrayList<MateriasMatriculadasDTO>();
		for (int i = 0; i < lMatriculaPK.size(); i++) {

			Materia materia = new Materia();
			NumMateria nMateria = new NumMateria();
			MateriasMatriculadasDTO materiasMatriculadasDTO = new MateriasMatriculadasDTO();

			materia = findMateriaById(lMatriculaPK.get(i).getMateria());
			nMateria = findNumMateriaById(lMatriculaPK.get(i).getNumMateria());

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
