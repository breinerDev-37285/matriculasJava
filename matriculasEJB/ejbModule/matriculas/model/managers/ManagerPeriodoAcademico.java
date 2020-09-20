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
		periodoAcademico = validarPeriodoAcademico(periodoAcademico);
		em.persist(periodoAcademico);
	}

	public PeriodoAcademico validarPeriodoAcademico(PeriodoAcademico periodoAcademico) throws Exception {
		if (periodoAcademico.getFechaInicio().equals("")) {
			throw new Exception("Por favor ingrese la fecha de inicio");
		}
		if (periodoAcademico.getFechaFin().equals("")) {
			throw new Exception("Por favor ingrese la fecha de finalizacion");
		}
		return periodoAcademico;
	}
}
