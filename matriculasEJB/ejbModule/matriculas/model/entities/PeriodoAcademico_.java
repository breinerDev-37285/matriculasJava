package matriculas.model.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-09-20T19:01:19.189-0500")
@StaticMetamodel(PeriodoAcademico.class)
public class PeriodoAcademico_ {
	public static volatile SingularAttribute<PeriodoAcademico, Integer> id;
	public static volatile SingularAttribute<PeriodoAcademico, Date> fechaFin;
	public static volatile SingularAttribute<PeriodoAcademico, Date> fechaInicio;
	public static volatile ListAttribute<PeriodoAcademico, Semestre> semestres;
}
