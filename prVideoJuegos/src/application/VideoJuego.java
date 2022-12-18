package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class VideoJuego {
	private int id;
	private SimpleStringProperty nombre;
	private SimpleStringProperty consola;
	private SimpleStringProperty compania;
	private SimpleIntegerProperty precio;
	
	public VideoJuego (String nombre, String consola, 
			String compania, int precio) {
		
		this.nombre = new SimpleStringProperty(nombre);
		this.consola = new SimpleStringProperty(consola);
		this.compania = new SimpleStringProperty(compania);
		this.precio = new SimpleIntegerProperty(precio);
	}
	
	public VideoJuego(int id,String nombre, String consola, 
			String compania, int precio) {
		
		this.nombre = new SimpleStringProperty(nombre);
		this.consola = new SimpleStringProperty(consola);
		this.compania = new SimpleStringProperty(compania);
		this.precio = new SimpleIntegerProperty(precio);
	}
	
	public int getId() {
		return id;
	}
	public String getNombre() {
		return nombre.get();
	}
	
	public void setNombre(String nombre) {
		this.nombre = new SimpleStringProperty(nombre);
	}
	
	public String getConsola() {
		return consola.get();
	}
	
	public void setConsola(String consola) {
		this.consola = new SimpleStringProperty(consola);
	}
	
	public String getCompania() {
		return compania.get();
	}
	
	public void setCompania(String compania) {
		this.compania = new SimpleStringProperty(compania);
	}
	
	public int getPrecio() {
		return precio.get();
	}
	
	public void setPaginas(int precio) {

		this.precio = new SimpleIntegerProperty(precio);
	
		
	}
	
}
