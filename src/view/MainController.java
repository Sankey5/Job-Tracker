package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import launcher.Launcher2;

public class MainController implements Initializable {
	
	private ViewType view;
	
	private static BorderPane rootPane;
	
	public MainController() {
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
	
	public static void switchView(ViewType viewType) {
		try {
			FXMLLoader loader = null;
			switch(viewType) {
				case Admin:
					loader = new FXMLLoader(Launcher2.class.getResource("/view/AdminMainView.fxml"));
					loader.setController(new AdminController());
					break;
				case Technician:
					loader = new FXMLLoader(Launcher2.class.getResource("/view/TechMainView.fxml"));
					loader.setController(new TechnicianController());
					break;
				case Login:
					loader = new FXMLLoader(Launcher2.class.getResource("/view/LoginView.fxml"));
					loader.setController(new LoginController());
					break;
					
			}
			Parent view = loader.load();
			rootPane.setCenter(view);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
    void doSwitchView1(ActionEvent event) {
		switchView(VIEW_1);
    }

    @FXML
    void doSwitchView2(ActionEvent event) {
		switchView(VIEW_2);
    }
    
    @FXML
    void doSwitchView3(ActionEvent event) {
		switchView(VIEW_3);
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}