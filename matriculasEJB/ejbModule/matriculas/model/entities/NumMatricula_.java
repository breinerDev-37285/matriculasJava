package matriculas.model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-09-20T19:01:19.188-0500")
@StaticMetamodel(NumMatricula.class)
public class NumMatricula_ {
	public static volatile SingularAttribute<NumMatricula, Integer> codigo;
	public static volatile SingularAttribute<NumMatricula, String> nombre;
	public static volatile ListAttribute<NumMatricula, Matricula> matriculas;
}
