package application;
	

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			/*Button btn =new Button("Click aqui");
			btn.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					System.out.println("hola mundo");
				}
			});
			Label lb=new Label("Hola pepe");
			
			StackPane panel=new StackPane();
			panel.setAlignment(lb,Pos.TOP_CENTER);
			panel.setAlignment(btn, Pos.CENTER);
			panel.getChildren().addAll(lb,btn);
			*/
			Button btn1=new Button("Boton 1");
			Button btn2=new Button("Boton 2");
			Button btn3=new Button("Boton 3");
			
			BorderPane panel=new BorderPane();
			panel.setCenter(btn1);
			
			//panel.add(btn1,0,0);
			//HBox panel=new HBox(2);
			//panel.setPadding(new Insets(15));
			//panel.getChildren().addAll(btn1,btn2,btn3);
			primaryStage.getIcons().add(new Image("/application/icon.png"));
			Scene scene=new Scene(panel,400,300);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
