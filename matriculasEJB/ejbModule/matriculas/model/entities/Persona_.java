package matriculas.model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-09-20T19:01:19.191-0500")
@StaticMetamodel(Persona.class)
public class Persona_ {
	public static volatile SingularAttribute<Persona, Integer> id;
	public static volatile SingularAttribute<Persona, String> apellidos;
	public static volatile SingularAttribute<Persona, String> cedula;
	public static volatile SingularAttribute<Persona, String> dirCallePrincipal;
	public static volatile SingularAttribute<Persona, String> dirCalleSecundaria;
	public static volatile SingularAttribute<Persona, String> dirCiudad;
	public static volatile SingularAttribute<Persona, String> dirNumCasa;
	public static volatile SingularAttribute<Persona, String> nombres;
	public static volatile ListAttribute<Persona, Telefono> telefonos;
	public static volatile ListAttribute<Persona, Usuario> usuarios;
}
