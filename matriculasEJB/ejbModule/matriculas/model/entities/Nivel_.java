package matriculas.model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-09-20T18:45:32.968-0500")
@StaticMetamodel(Nivel.class)
public class Nivel_ {
	public static volatile SingularAttribute<Nivel, Integer> id;
	public static volatile SingularAttribute<Nivel, String> nombre;
	public static volatile ListAttribute<Nivel, Semestre> semestres;
}
