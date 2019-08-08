package launcher;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.LoginController;
import view.MainController;
import view.ViewType;

public class Launcher extends Application{
	
	public static Stage stage ;
	@Override
	public void start(Stage primaryStage) throws Exception{
		stage = primaryStage;
		MainController controller = new MainController();
		FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("/view/defaultView.fxml"));
		//loader.setController(controller);
		Parent root = loader.load();
		controller.setRootPane((BorderPane) root);
		controller.switchView(ViewType.Login);
		Scene scene = new Scene(root);
		stage.setTitle("Welcome!");
		stage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
