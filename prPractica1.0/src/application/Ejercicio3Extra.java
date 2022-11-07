package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Ejercicio3Extra extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//panel principal
			//panel principal
			BorderPane panel=new BorderPane();
			Label lbl_numero=new Label("hola");
			panel.setBottom(lbl_numero);
			
			
			//panel secundario
			GridPane grid =new GridPane();
			panel.setCenter(grid);
			
			//primera fila
			Button b7=new Button("7");
			b7.setOnAction(new EventHandler<ActionEvent>(){
				public void handle(ActionEvent event) {
					String numero=lbl_numero.getText()+"7";
					lbl_numero.setText(numero);
				}
			});
			Button b8=new Button("8");
			Button b9=new Button("9");
			
			//segunda fila
			Button b4=new Button("4");
			Button b5=new Button("5");
			Button b6=new Button("6");
			Button bLlamar=new Button("Llamar");
			
			//tercera fila
			Button b1=new Button("1");
			Button b2=new Button("2");
			Button b3=new Button("3");
			Button bColgar=new Button("Colgar");
			
			//cuarta fila
			Button b0=new Button("0");
			
			grid.add(b7, 0, 0);
			grid.add(b8, 1, 0);
			grid.add(b9, 2, 0);
			
			grid.add(b4, 0, 1);
			grid.add(b5, 1, 1);
			grid.add(b6, 2, 1);
			grid.add(bLlamar, 3, 1);
			
			grid.add(b3, 0, 2);
			grid.add(b2, 1, 2);
			grid.add(b1, 2, 2);
			grid.add(bColgar, 3, 2);
			grid.add(b0, 1, 3);
			
			
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