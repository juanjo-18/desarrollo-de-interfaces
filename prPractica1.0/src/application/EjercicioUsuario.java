package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class EjercicioUsuario extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			VBox panel= new VBox(10);
			panel.setPadding(new Insets(5));
			Label nombre=new Label("Nombre");
			TextField txt_nombre=new TextField();
			Label lbl_contraseña=new Label("Contraseña");
			PasswordField psw_contraseña = new PasswordField();
			Button btn =new Button("Entrar");
			Label lb_bienvenida=new Label();
			
			panel.getChildren().addAll(nombre,txt_nombre,lbl_contraseña,psw_contraseña,btn,lb_bienvenida);
			btn.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					lb_bienvenida.setText("bienvenido "+txt_nombre.getText());
				}
			});
			Scene scene = new Scene(panel, 400, 300);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}