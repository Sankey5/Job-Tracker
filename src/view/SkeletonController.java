package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import launcher.Launcher2;

public class SkeletonController implements Initializable {
	public static final int VIEW_1 = 1;
	public static final int VIEW_2 = 2;
	public static final int VIEW_3 = 3; 
	
	private static BorderPane rootPane;
	
	public SkeletonController() {
	}
	
	public void setRootPane(BorderPane rootPane) {
		SkeletonController.rootPane = rootPane;
	}


	@FXML
	public MenuItem logoutItem;
	void logoutAction(ActionEvent event) {
		switchView(VIEW_3);
	}
	
	
	public static void switchView(int viewType) {
		try {
			FXMLLoader loader = null;
			switch(viewType) {
				case VIEW_1:
					loader = new FXMLLoader(Launcher2.class.getResource("/view/AdminMainView.fxml"));
					loader.setController(new AdminController());
					break;
				case VIEW_2:
					loader = new FXMLLoader(Launcher2.class.getResource("/view/TechMainView.fxml"));
					loader.setController(new TechController());
					break;
				case VIEW_3:
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