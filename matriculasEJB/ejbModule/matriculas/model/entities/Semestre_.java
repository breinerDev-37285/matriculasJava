package matriculas.model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-09-20T19:01:19.192-0500")
@StaticMetamodel(Semestre.class)
public class Semestre_ {
	public static volatile SingularAttribute<Semestre, Integer> id;
	public static volatile ListAttribute<Semestre, Materia> materias;
	public static volatile SingularAttribute<Semestre, String> nombre;
	public static volatile ListAttribute<Semestre, PeriodoAcademico> periodoAcademicos;
}
