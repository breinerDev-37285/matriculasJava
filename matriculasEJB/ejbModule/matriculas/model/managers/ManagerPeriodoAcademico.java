package matriculas.model.managers;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import matriculas.model.entities.PeriodoAcademico;

@Stateless
@LocalBean
public class ManagerPeriodoAcademico {

	@PersistenceContext
	private EntityManager em;

	public PeriodoAcademico findPeriodoAcademicoById(int idPeriodoAcademico) {
		return em.find(PeriodoAcademico.class, idPeriodoAcademico);
	}

	public List<PeriodoAcademico> findAllPeriodoAcademico() {
		return em.createQuery("SELECT p FROM PeriodoAcademico p", PeriodoAcademico.class).getResultList();
	}

	public void registrarPeriodoAcademico(PeriodoAcademico periodoAcademico) throws Exception {
		periodoAcademico = validacionesPeriodoAcademico(periodoAcademico);
		em.persist(periodoAcademico);
	}

	public void actualizarPeriodoAcademico(PeriodoAcademico periodoAcademico) throws Exception {
		periodoAcademico = validacionesPeriodoAcademico(periodoAcademico);
		PeriodoAcademico pa = findPeriodoAcademicoById(periodoAcademico.getId());
		pa.setFechaInicio(periodoAcademico.getFechaInicio());
		pa.setFechaFin(periodoAcademico.getFechaFin());
		em.merge(pa);
	}
	
	public void eliminarPeriodoAcademico(PeriodoAcademico periodoAcademico) throws Exception {
		PeriodoAcademico pa = findPeriodoAcademicoById(periodoAcademico.getId());
	
		em.remove(pa);
	}

	public PeriodoAcademico validacionesPeriodoAcademico(PeriodoAcademico periodoAcademico) throws Exception {
		if (periodoAcademico.getFechaInicio() == null || periodoAcademico.getFechaInicio().equals(""))
			throw new Exception("Ingrese la fecha de inicio del periodo academico");
		if (periodoAcademico.getFechaFin() == null || periodoAcademico.getFechaFin().equals(""))
			throw new Exception("Ingrese la fecha de Fin del periodo academico");
		if (periodoAcademico.getFechaInicio().after(periodoAcademico.getFechaFin()))
			throw new Exception(
					"La Fecha de Fin del periodo academico deber ser mayo que la Fecha de Inicio del periodo academico");
		return periodoAcademico;
	}
}
