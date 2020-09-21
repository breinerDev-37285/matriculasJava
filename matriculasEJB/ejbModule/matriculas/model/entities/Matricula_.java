package matriculas.model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-09-20T19:01:19.186-0500")
@StaticMetamodel(Matricula.class)
public class Matricula_ {
	public static volatile SingularAttribute<Matricula, MatriculaPK> id;
	public static volatile SingularAttribute<Matricula, Boolean> estado;
	public static volatile SingularAttribute<Matricula, Materia> materiaBean;
	public static volatile SingularAttribute<Matricula, Usuario> usuario;
	public static volatile SingularAttribute<Matricula, Integer> registro;
	public static volatile SingularAttribute<Matricula, NumMatricula> numMatricula;
}
