package matriculas.model.dto;

public class usuariosDTO {
	private  String nombres;
	private String apellidos; 
	private Object cedula;
	private String correo;
	private String estado;
	private Object ciudad;
	private Object calleP;
	private Object calleS;
	private Object numCasa;
	
	
	public usuariosDTO(String nombres, String apellidos, Object cedula, String correo, String estado, Object ciudad,
			Object calleP, Object calleS, Object numCasa) {
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.cedula = cedula;
		this.correo = correo;
		this.estado = estado;
		this.ciudad = ciudad;
		this.calleP = calleP;
		this.calleS = calleS;
		this.numCasa = numCasa;
	}


	public String getNombres() {
		return nombres;
	}


	public void setNombres(String nombres) {
		this.nombres = nombres;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public Object getCedula() {
		return cedula;
	}


	public void setCedula(Object cedula) {
		this.cedula = cedula;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public Object getCiudad() {
		return ciudad;
	}


	public void setCiudad(Object ciudad) {
		this.ciudad = ciudad;
	}


	public Object getCalleP() {
		return calleP;
	}


	public void setCalleP(Object calleP) {
		this.calleP = calleP;
	}


	public Object getCalleS() {
		return calleS;
	}


	public void setCalleS(Object calleS) {
		this.calleS = calleS;
	}


	public Object getNumCasa() {
		return numCasa;
	}


	public void setNumCasa(Object numCasa) {
		this.numCasa = numCasa;
	}


	@Override
	public String toString() {
		return "usuariosDTO [nombres=" + nombres + ", apellidos=" + apellidos + ", cedula=" + cedula + ", correo="
				+ correo + ", estado=" + estado + ", ciudad=" + ciudad + ", calleP=" + calleP + ", calleS=" + calleS
				+ ", numCasa=" + numCasa + "]";
	}

	
}
