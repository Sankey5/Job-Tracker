package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;


public class LoginController implements Initializable {
	

	public LoginController() throws IOException {
	}
	
	@FXML
	public TextField usernameTextField;
	
	
	@FXML
    void enterAction(ActionEvent event) throws IOException {
		
		System.out.print(usernameTextField.getText());
		if (usernameTextField.getText().equals("Admin")) {
			MainController.switchView(MainController.VIEW_1);
		}
		
		if (usernameTextField.getText().equals("Tech")) {
			MainController.switchView(MainController.VIEW_2);
		}
		
		
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
