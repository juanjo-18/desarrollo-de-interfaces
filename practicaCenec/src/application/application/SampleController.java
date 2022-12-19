package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SampleController {
	@FXML
	private TextField txtNombre;
	
	@FXML
	private Button btnMensaje;
	
	@FXML 
	private Label lblMensaje;
	
	@FXML
	public void mostrarMensaje(ActionEvent event) {
		lblMensaje.setText(txtNombre.getText());
	}
}
