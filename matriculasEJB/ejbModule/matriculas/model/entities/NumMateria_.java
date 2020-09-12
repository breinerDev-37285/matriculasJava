package matriculas.model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-09-12T11:09:18.866-0500")
@StaticMetamodel(NumMateria.class)
public class NumMateria_ {
	public static volatile SingularAttribute<NumMateria, Integer> codigo;
	public static volatile SingularAttribute<NumMateria, String> nombre;
	public static volatile ListAttribute<NumMateria, Matricula> matriculas;
}
