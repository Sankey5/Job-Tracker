package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Database;
import model.Equipment;

public class EquipmentPopupController implements Initializable, EventHandler<ActionEvent>{

	@FXML
	private TextField makeTextField, modelTextField, serialTextField;
	@FXML
	private Label warningLabel;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	@Override
	/**
	 * Add Equipment button pressed
	 */
	public void handle(ActionEvent event) {
		clearWarning();
		String make = makeTextField.getText();
		String model = modelTextField.getText();
		String serial = serialTextField.getText();
		
		if(! validateFields()) {
			return;
		}
		Equipment equipment = new Equipment(make, model, serial);
		Database.getInstance().getEquipment().add(equipment);
		Stage thisStage = (Stage) ((Node)(event.getSource())).getScene().getWindow();
		thisStage.close();
	}
	
	
	public boolean validateFields() {
		return true;
	}
	public void showWarning(String warning) {
		warningLabel.setText(warning);
	}
	
	/**
	 * Clears the warning label
	 */
	public void clearWarning() {
		warningLabel.setText("");
	}


}