package matriculas.model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-09-20T18:45:32.974-0500")
@StaticMetamodel(Rol.class)
public class Rol_ {
	public static volatile SingularAttribute<Rol, Integer> id;
	public static volatile SingularAttribute<Rol, String> nombre;
	public static volatile ListAttribute<Rol, Usuario> usuarios;
}
