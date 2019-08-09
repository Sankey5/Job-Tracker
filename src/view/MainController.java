package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import launcher.Launcher;
import model.Database;
import model.Technician;

/**
 * This class is the main App skeleton controller that individual views will communicate with to access the database.
 * @author Kenny
 *
 */
public class MainController implements Initializable {
	
	private static MainController singleton;
	
	private Technician selectedTech;
	
	private ViewType view;
	
	private static BorderPane rootPane;
	
	private MainController() {
		
	}
	
	public static MainController getInstance() {
		if(singleton == null) {
			singleton = new MainController();
		}
		return singleton;
	}
	
	public Technician getSelectedTech() {
		return selectedTech;
	}

	public void setSelectedTech(Technician selectedTech) {
		this.selectedTech = selectedTech;
	}

	public void setRootPane(BorderPane rootPane) {
		MainController.rootPane = rootPane;
	}


	@FXML
	public Button logoutButton;
	
	@FXML
	void logoutAction(ActionEvent event) {
			switchView(ViewType.Login);
		}
/*public static Database getDatabase() {
		return database;
	}*/
	/*public static void loadTechnician(Technician tech) {
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(Launcher.class.getResource("/view/TechMainView.fxml"));
			Technician conew technicianController()
			loader.setController();
			
		}
	}*/
	public static void switchView(ViewType viewType) {
		try {
			FXMLLoader loader = null;
			switch(viewType) {
				case Admin:
					loader = new FXMLLoader(Launcher.class.getResource("/view/AdminView.fxml"));
					loader.setController(new AdminController());
					break;
				case Technician:
					loader = new FXMLLoader(Launcher.class.getResource("/view/TechMainView.fxml"));
					loader.setController(new TechnicianController());
					break;
				case Login:
					loader = new FXMLLoader(Launcher.class.getResource("/view/LoginView.fxml"));
					loader.setController(new LoginController());
					break;
				case TechSelect:
					loader = new FXMLLoader(Launcher.class.getResource("/view/TechSelectView.fxml"));
					loader.setController(new TechSelectController());
					break;
			}
			Parent view = loader.load();
			rootPane.setCenter(view);
			Launcher.stage.sizeToScene();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}