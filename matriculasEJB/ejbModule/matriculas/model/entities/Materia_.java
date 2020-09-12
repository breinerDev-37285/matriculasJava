package matriculas.model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-09-12T11:09:18.754-0500")
@StaticMetamodel(Materia.class)
public class Materia_ {
	public static volatile SingularAttribute<Materia, Integer> codigo;
	public static volatile SingularAttribute<Materia, Integer> creditos;
	public static volatile SingularAttribute<Materia, String> nombre;
	public static volatile SingularAttribute<Materia, Semestre> semestreBean;
	public static volatile ListAttribute<Materia, Matricula> matriculas;
}
