package launcher;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.SkeletonController;

public class Launcher2 extends Application{
	
	@Override
	public void start(Stage stage){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Launcher.class.getResource("/view/SkeletonView.fxml"));
			SkeletonController controller = new SkeletonController();
			loader.setController(controller);
			
			BorderPane borderPane = loader.load();
			controller.setRootPane(borderPane);
			Scene scene = new Scene(borderPane);
			SkeletonController.switchView(3);
			
			stage.setTitle("Job Queue");
			stage.setScene(scene);
			stage.show();
			
		}catch(IOException e) {
			System.out.println("Could not find FXML file");
			return;
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
