package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


public class LoginController implements Initializable {
	

	public LoginController() throws IOException {
	}
	
	@FXML
    private Button techButton;

    @FXML
    private Button adminButton;
    
    @FXML
    void switchAdminView(ActionEvent event) {
    	MainController.switchView(ViewType.Admin);
    }

    @FXML
    void techSelect(ActionEvent event) {
    	MainController.switchView(ViewType.TechSelect);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
