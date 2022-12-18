package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class IndexController {

	@FXML
	private TextField txtCalle;

	@FXML
	private ChoiceBox cbProvincia;

	@FXML
	private TextField txtMunicipio;

	@FXML
	private TextField txtNumero;

	@FXML
	private TableView<Casa> tableCasas;

	@FXML
	private TableColumn<Casa, String> columnCalle;

	@FXML
	private TableColumn<Casa, String> columnMunicipio;

	@FXML
	private TableColumn<Casa, String> columnProvincia;

	@FXML
	private TableColumn<Casa, Integer> columnNumero;

	@FXML
	private Button btnAnadir;

	@FXML
	private Button btnBorrar;

	private ObservableList<Casa> listaCasas = FXCollections.observableArrayList();

	public ObservableList<String> listaProvincias = FXCollections.observableArrayList("Málaga", "Granada", "Sevilla",
			"Cordoba","Jaén","Cádiz","Huelva","Almería");

	@FXML
	private void initialize() {

		cbProvincia.setItems(listaProvincias);

		columnCalle.setCellValueFactory(new PropertyValueFactory<>("calle"));
		columnProvincia.setCellValueFactory(new PropertyValueFactory<>("provincia"));
		columnMunicipio.setCellValueFactory(new PropertyValueFactory<>("municipio"));
		columnNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));

		ObservableList listaCasasBD = getCasasBD();
		
		tableCasas.setItems(listaCasasBD);
	
	}

	private ObservableList<Casa> getCasasBD() {
		// creamos la observable list donde almacenaremos las casas obenidos de la bd
		ObservableList<Casa> listaCasasBD = FXCollections.observableArrayList();

		// nos conectamos a la bd
		DatabaseConnection dbConnection = new DatabaseConnection();
		Connection connection = dbConnection.getConnection();

		String query = "select * from casas";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Casa casa = new Casa(rs.getInt("id"), rs.getString("calle"), rs.getInt("numero"), 
						rs.getString("municipio"),rs.getString("provincia"));

				
				listaCasasBD.add(casa);
			}
			// cerramos la conexion

			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaCasasBD;
	}

	public void anadirCasa(ActionEvent event) {
		if (txtNumero.getText().isEmpty() || txtCalle.getText().isEmpty() || cbProvincia.getSelectionModel().isEmpty()
				|| txtMunicipio.getText().isEmpty()) {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Informacion incompleta");
			alerta.setHeaderText("Falta informacion de la casa");
			alerta.setContentText("Por favor, introduce todos los campos");
			alerta.showAndWait();

		} else {
			if (esNumero(txtNumero.getText())) {
				Casa c = new Casa(txtCalle.getText(), cbProvincia.getValue().toString(), txtMunicipio.getText(),
						Integer.parseInt(txtNumero.getText()));
				
				txtCalle.clear();
				cbProvincia.getSelectionModel().clearSelection();
				txtMunicipio.clear();
				txtNumero.clear();

				// nos conectamos a la bd
				DatabaseConnection dbConnection = new DatabaseConnection();
				Connection connection = dbConnection.getConnection();

				try {
					// aqui insertaremos en la bd
					String query = "insert into casas(calle, numero,municipio,provincia) Values (?,?,?,?)";
					PreparedStatement ps = connection.prepareStatement(query);
					ps.setString(1, c.getCalle());
					ps.setInt(2, c.getNumero());
					ps.setString(3, c.getMunicipio());
					ps.setString(4, c.getProvincia());
					ps.executeUpdate();

					// cerramos la sesion
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// despues de insertar actualizamos la tabla
				ObservableList listaCasasBD = getCasasBD();

				tableCasas.setItems(listaCasasBD);
			} else {
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("Error al insertar");
				alerta.setHeaderText("No se ha introducido un numero en el numero ");
				alerta.setContentText("Por favor, introduzca un numero en el numero");
				alerta.showAndWait();
			}
		}
	}

	@FXML
	public void borrarCasa(ActionEvent event) {
		int indiceSeleccionado = tableCasas.getSelectionModel().getSelectedIndex();

		if (indiceSeleccionado <= -1) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Error al borrar");
			alerta.setHeaderText("No se ha seleccionado ninguna casa al borrar");
			alerta.setContentText("Por favor, selecciona una casa para borrarlo");
			alerta.showAndWait();
		} else {


			//	Nos conectamos a la BD
			DatabaseConnection dbConnection = new DatabaseConnection();
			Connection connection = dbConnection.getConnection();

			try {
				String query = "delete from casas where calle = ? and numero=? and municipio=? and provincia=?";
				PreparedStatement ps = connection.prepareStatement(query);
				Casa casa = tableCasas.getSelectionModel().getSelectedItem();
				ps.setString(1, casa.getCalle());
				ps.setInt(2, casa.getNumero());
				ps.setString(3, casa.getMunicipio());
				ps.setString(4, casa.getProvincia());
				
				ps.executeUpdate();

				tableCasas.getSelectionModel().clearSelection();

				//	Actualizamos la tabla
				ObservableList listaCasasBD = getCasasBD();
				tableCasas.setItems(listaCasasBD);

				//	Cerramos la sesi�n
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	

	public boolean esNumero(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}