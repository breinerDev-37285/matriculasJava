package matriculas.model.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import matriculas.model.dto.telefonoDTO;
import matriculas.model.entities.PeriodoAcademico;
import matriculas.model.entities.Persona;
import matriculas.model.entities.Telefono;

/**
 * Session Bean implementation class ManagerTelefono
 */
@Stateless
@LocalBean
public class ManagerTelefono {

   @PersistenceContext
   EntityManager em;
   
   
   public List<Persona> getAllPersonas(){
	   return em.createNamedQuery("Persona.findAll", Persona.class).getResultList();
   }
   
    public List<telefonoDTO>  getAllTelefono () {
    	Query query = em.createNativeQuery("select t.codigo, p.nombres, p.apellidos, p.cedula, t.numero" + 
    			"from telefono t" + 
    			"inner join persona p" + 
    			"on t.persona=p.id"); 
    	
    	List<Object[]> loTelefonos = query.getResultList();
    	
    	List<telefonoDTO> lTelefonos = new ArrayList<telefonoDTO>();
    	telefonoDTO telefono;
    	
    	int codigo;
    	String  nombres, apellidos, cedula, numero;

    	
    	for (Object[] ftelefono : loTelefonos) {
    				
    				codigo = Integer.parseInt( ftelefono[0].toString()  );
    				nombres = ftelefono[1].toString();
    				apellidos = ftelefono[2].toString();
    				cedula = ftelefono[3].toString();
    				numero = ftelefono[4].toString();
    		
			  		telefono = new telefonoDTO(codigo, nombres, apellidos, cedula, numero);
			  		lTelefonos.add( telefono );
		}
    	
    	return lTelefonos;
    }
    
    private Persona findPersona(int idPersona) {
    		return em.find(Persona.class, idPersona);
    }
    
    
    public void registrarTelefono( Telefono telefono, int idpersona, int personas ) throws Exception {
    	

    	
    	if (  idpersona <= 0 || idpersona > personas) {
    		throw new Exception( "Por favor seleccione una persona valida" );
    	}
    		
    		Persona persona = findPersona(idpersona);
    		Telefono telf = validarTelefono(telefono);
    		telf.setPersonaBean(persona);
    		
    		em.persist(telf);
    }
    
    private Telefono validarTelefono ( Telefono telefono ) throws Exception{
    		if (  telefono.getNumero().equals("")  ) {
    			throw new Exception( "Por favor ingrese un numero" );
    		}
    		
    		if( telefono.getNumero().length() != 10 ||
    			 !isNumeric(telefono.getNumero())
    				) {
    			throw new Exception( "El número telefonico debe contener 10 digitos" );
    		}
    	
    		return telefono;
    }
    
    
    public void actualizarTelefono( telefonoDTO telefono ) throws Exception {
    		Telefono telf = findTelefono(telefono.getCodigo());
    		telf.setNumero(telefono.getNumero());
    		telf = validarTelefono(telf);
    		
    		em.merge(telf);
    }
    
    
    public void eliminarTelefono( telefonoDTO telefono ) throws Exception {
    		Telefono telf = findTelefono(telefono.getCodigo());
    		em.remove(telf);
    }
    
    private Telefono findTelefono( int idtelefono )  {
    		return em.find(Telefono.class, idtelefono);
    }
    
    
	private boolean isNumeric(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

}
