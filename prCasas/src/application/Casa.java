package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Casa {
	private int id;
	private SimpleStringProperty calle;
	private SimpleStringProperty municipio;
	private SimpleStringProperty provincia;
	private SimpleIntegerProperty numero;
	
	public Casa (String calle, String municipio, 
			String provincia, int numero) {
		
		this.calle = new SimpleStringProperty(calle);
		this.municipio = new SimpleStringProperty(municipio);
		this.provincia = new SimpleStringProperty(provincia);
		this.numero = new SimpleIntegerProperty(numero);
	}
	
	public Casa(int id,String calle,int numero,String municipio, 
			String provincia ) {
		
		this.calle = new SimpleStringProperty(calle);
		this.municipio = new SimpleStringProperty(municipio);
		this.provincia = new SimpleStringProperty(provincia);
		this.numero = new SimpleIntegerProperty(numero);
	}
	
	public int getId() {
		return id;
	}
	public String getCalle() {
		return calle.get();
	}
	
	public void setCalle(String calle) {
		this.calle = new SimpleStringProperty(calle);
	}
	
	public String getMunicipio() {
		return municipio.get();
	}
	
	public void setMunicipio(String municipio) {
		this.municipio = new SimpleStringProperty(municipio);
	}
	
	public String getProvincia() {
		return provincia.get();
	}
	
	public void setProvincia(String provincia) {
		this.provincia = new SimpleStringProperty(provincia);
	}
	
	public int getNumero() {
		return numero.get();
	}
	
	public void setNumero(int numero) {
		this.numero = new SimpleIntegerProperty(numero);	
	}
	
}
