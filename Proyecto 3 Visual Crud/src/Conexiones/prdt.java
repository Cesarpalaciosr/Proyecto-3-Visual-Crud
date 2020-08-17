package Conexiones;

public class prdt {

	private int id;
	private long codigo;
	private String nombre;
	private float precio;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public prdt() {
		codigo = 0;
		nombre = null;
		precio = 0;
	}
	
	public float getPrecio() {
		return precio;
	}

	public void setPrecio(long precio) {
		this.precio = precio;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String toString() {
	   return nombre;
   }
}
