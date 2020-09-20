package matriculas.model.dto;

public class telefonoDTO {
	
		int codigo;
		String nombres, apellidos, cedula, numero;
		
		public telefonoDTO(int codigo, String nombres, String apellidos, String cedula, String numero) {
			
			this.codigo = codigo;
			this.nombres = nombres;
			this.apellidos = apellidos;
			this.cedula = cedula;
			this.numero = numero;
		}
		
		
		public int getCodigo() {
			return codigo;
		}
		public void setCodigo(int codigo) {
			this.codigo = codigo;
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
		public String getCedula() {
			return cedula;
		}
		public void setCedula(String cedula) {
			this.cedula = cedula;
		}
		public String getNumero() {
			return numero;
		}
		public void setNumero(String numero) {
			this.numero = numero;
		}
		
		
}
