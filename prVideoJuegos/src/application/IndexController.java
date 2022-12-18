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
	private TextField txtNombre;

	@FXML
	private ChoiceBox cbConsola;

	@FXML
	private TextField txtCompania;

	@FXML
	private TextField txtPrecio;

	@FXML
	private TableView<VideoJuego> tableVideoJuegos;

	@FXML
	private TableColumn<VideoJuego, String> columnNombre;

	@FXML
	private TableColumn<VideoJuego, String> columnConsola;

	@FXML
	private TableColumn<VideoJuego, String> columnCompania;

	@FXML
	private TableColumn<VideoJuego, Integer> columnPrecio;

	@FXML
	private Button btnAnadir;

	@FXML
	private Button btnBorrar;

	private ObservableList<VideoJuego> listaVideoJuegos = FXCollections.observableArrayList();

	public ObservableList<String> listaConsolas = FXCollections.observableArrayList("Nintendo", "Play", "XBOX",
			"Game Boy");

	@FXML
	private void initialize() {

		cbConsola.setItems(listaConsolas);

		columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columnConsola.setCellValueFactory(new PropertyValueFactory<>("consola"));
		columnCompania.setCellValueFactory(new PropertyValueFactory<>("compania"));
		columnPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

		ObservableList listaVideoJuegosBD = getVideoJuegosBD();

		tableVideoJuegos.setItems(listaVideoJuegosBD);
	}

	private ObservableList<VideoJuego> getVideoJuegosBD() {
		// creamos la observable list donde almacenaremos los video juegos obenidos de la bd
		ObservableList<VideoJuego> listaVideoJuegosBD = FXCollections.observableArrayList();

		// nos conectamos a la bd
		DatabaseConnection dbConnection = new DatabaseConnection();
		Connection connection = dbConnection.getConnection();

		String query = "select * from videoJuegos";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				VideoJuego videoJuego = new VideoJuego(rs.getInt("id"), rs.getString("nombre"), rs.getString("consola"),
						rs.getString("compania"), rs.getInt("precio"));

				listaVideoJuegosBD.add(videoJuego);
			}
			// cerramos la conexion

			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaVideoJuegosBD;
	}

	public void anadirVideoJuego(ActionEvent event) {
		String mensaje = "";
		boolean vacio = true;
		if (txtPrecio.getText().isEmpty() || txtNombre.getText().isEmpty() || cbConsola.getSelectionModel().isEmpty()
				|| txtNombre.getText().isEmpty()) {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Informacion incompleta");
			alerta.setHeaderText("Falta informacion del video juego");
			alerta.setContentText("Por favor, introduce todos los campos");
			alerta.showAndWait();

		} else {
			if (esNumero(txtPrecio.getText())) {
				VideoJuego v = new VideoJuego(txtNombre.getText(), cbConsola.getValue().toString(), txtCompania.getText(),
						Integer.parseInt(txtPrecio.getText()));
				
				txtNombre.clear();
				cbConsola.getSelectionModel().clearSelection();
				txtCompania.clear();
				txtPrecio.clear();

				// nos conectamos a la bd
				DatabaseConnection dbConnection = new DatabaseConnection();
				Connection connection = dbConnection.getConnection();

				try {
					// aqui insertaremos en la bd
					String query = "insert into videoJuegos(nombre, consola,compania,precio) Values (?,?,?,?)";
					PreparedStatement ps = connection.prepareStatement(query);
					ps.setString(1, v.getNombre());
					ps.setString(2, v.getConsola());
					ps.setString(3, v.getCompania());
					ps.setInt(4, v.getPrecio());
					ps.executeUpdate();

					// cerramos la sesion
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// despues de insertar actualizamos la tabla
				ObservableList listaVideoJuegosBD = getVideoJuegosBD();

				tableVideoJuegos.setItems(listaVideoJuegosBD);
			} else {
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("Error al insertar");
				alerta.setHeaderText("No se ha introducido un numero en el precio");
				alerta.setContentText("Por favor, introduzca un numero en el precio");
				alerta.showAndWait();
			}
		}
	}

	@FXML
	public void borrarVideoJuego(ActionEvent event) {
		int indiceSeleccionado = tableVideoJuegos.getSelectionModel().getSelectedIndex();

		if (indiceSeleccionado <= -1) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Error al borrar");
			alerta.setHeaderText("No se ha seleccionado ningunn video juego a borrar");
			alerta.setContentText("Por favor, selecciona un video juego para borrarlo");
			alerta.showAndWait();
		} else {

			//	Nos conectamos a la BD
			DatabaseConnection dbConnection = new DatabaseConnection();
			Connection connection = dbConnection.getConnection();

			try {
				String query = "delete from videoJuegos where nombre = ? and consola=? and compania=? and precio=?";
				PreparedStatement ps = connection.prepareStatement(query);
				VideoJuego juego = tableVideoJuegos.getSelectionModel().getSelectedItem();
				
				ps.setString(1, juego.getNombre());
				ps.setString(2, juego.getConsola());
				ps.setString(3, juego.getCompania());
				ps.setInt(4, juego.getPrecio());
				ps.executeUpdate();

				tableVideoJuegos.getSelectionModel().clearSelection();

				//	Actualizamos la tabla
				ObservableList listaVideoJuegosBD = getVideoJuegosBD();
				tableVideoJuegos.setItems(listaVideoJuegosBD);

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