package launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.MainController;

public class Launcher2 extends Application{
	
	/*public static void main(String[] args) {
		launch(args);
	}*/
	
	@Override
	public void start(Stage stage) throws Exception {
		
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Launcher.class.getResource("/view/MainView.fxml"));
			MainController controller = new MainController();
			loader.setController(controller);
			
			BorderPane borderPane = (BorderPane) loader.load();
			controller.setRootPane(borderPane);
			Scene scene = new Scene(borderPane);
			MainController.switchView(3);
			
			stage.setTitle("Job Queue");
			stage.setScene(scene);
			stage.show();
		
	}
}
