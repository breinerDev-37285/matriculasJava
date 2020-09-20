package matriculas.model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-09-20T18:45:32.977-0500")
@StaticMetamodel(Usuario.class)
public class Usuario_ {
	public static volatile SingularAttribute<Usuario, Integer> id;
	public static volatile SingularAttribute<Usuario, String> correo;
	public static volatile SingularAttribute<Usuario, Boolean> estado;
	public static volatile SingularAttribute<Usuario, String> password;
	public static volatile ListAttribute<Usuario, Matricula> matriculas;
	public static volatile SingularAttribute<Usuario, Persona> personaBean;
	public static volatile SingularAttribute<Usuario, Rol> rolBean;
}
