package view;


import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Database;
import model.Equipment;
import model.Technician;

public class TechnicianPopupController implements EventHandler<ActionEvent>, Initializable {

	private Database database;
	
	@FXML
	private MenuButton equipmentMenuButton;
	@FXML
	private TextField nameTextField;
	@FXML
	private Label warningLabel;
	private HashMap<CheckMenuItem, Equipment> itemMap;
	
	public TechnicianPopupController() {
		database = Database.getInstance();
		this.itemMap = new HashMap<CheckMenuItem, Equipment>();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for(Equipment equipment: database.getEquipment()) {
			CheckMenuItem item = new CheckMenuItem(equipment.getEquipmentMake()+"-"+equipment.getEquipmentModel());
			equipmentMenuButton.getItems().add(item);
			itemMap.put(item, equipment);
		}
	}

	@Override
	public void handle(ActionEvent event) {
		if(! Database.validateWord(nameTextField.getText())) {
			showWarning("Please enter only alphanumeric characters");
		}
		ArrayList<Equipment> equipmentList = new ArrayList<Equipment>();
		for(MenuItem menuItem : equipmentMenuButton.getItems()) {
			if (((CheckMenuItem)menuItem).isSelected()) {
				equipmentList.add(itemMap.get((CheckMenuItem)menuItem));
			}
		}
		Technician technician = new Technician(nameTextField.getText());
		technician.setEquipmentList(equipmentList);
		database.addTechnician(technician);
		Stage thisStage = (Stage) ((Node)(event.getSource())).getScene().getWindow();
		thisStage.close();
	}
	public void showWarning(String warning) {
		warningLabel.setText(warning);
	}

}
