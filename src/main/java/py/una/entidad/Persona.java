package py.una.entidad;
import java.util.ArrayList;
import java.util.List;

public class Persona {

	int cedula;
	String nombre;
	String apellido;
	String chapa;
	String marca;
	
	public Persona(){
		
	}

	public Persona(int pcedula, String pnombre, String papellido, String chapa, String marca){
		this.cedula = pcedula;
		this.nombre = pnombre;
		this.apellido = papellido;
		this.chapa=chapa;
		this.marca=marca;
		
	}
	
	public int getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getChapa() {
		return chapa;
	}

	public void setChapa(String chapa) {
		this.chapa = chapa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
	


}
