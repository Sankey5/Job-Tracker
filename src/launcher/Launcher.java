package launcher;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application{
	
	@Override
	public void start(Stage stage){
		try {
			FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("/view/login.fxml"));
			
			Parent root = loader.load();
			
			Scene scene = new Scene(root);
			
			stage.setTitle("Job Queue");
			stage.setScene(scene);
			stage.show();
		}catch(IOException e) {
			System.out.println("Could not find FXML file");
			return;
		}
		
	}
	
/*	public static void main(String[] args) {
		launch(args);
	}*/

}
