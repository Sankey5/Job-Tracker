package view;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.*;

public class JobPopUpController implements Initializable {

    @FXML
    private DatePicker deadlinePicker;
    
    @FXML
    private Label warningLabel;

    @FXML
    private TextField equipmentSerialText, equipmentMakeText, jobNumberText, equipmentModelText
    	,customerEmailText, customerNameText, customerPhoneNumberText, customerCompanyText ;
    
    @FXML
    private MenuButton customerMenuButton, equipmentMenuButton;
    
    private boolean creatingCustomer, creatingEquipment;
    
    public JobPopUpController() {
    	
    }

    @FXML
    void handleNewCustomer(ActionEvent event) {
    	customerEmailText.setVisible(true);
    	customerPhoneNumberText.setVisible(true);
    	customerCompanyText.setVisible(true);
    	customerNameText.setVisible(true);
    	creatingCustomer = true;
    }
    @FXML
    void handleNewEquipment(ActionEvent event) {
    	equipmentSerialText.setVisible(true);
    	equipmentMakeText.setVisible(true);
    	equipmentModelText.setVisible(true);
    	creatingEquipment = true;
    }

    @FXML
    void handleSelectCustomer(ActionEvent event) {
    	customerEmailText.setVisible(false);
    	customerPhoneNumberText.setVisible(false);
    	customerCompanyText.setVisible(false);
    	customerNameText.setVisible(false);
    	creatingCustomer = false;
    	System.out.print("Easdf");
    }
    @FXML
    void handleSelectEquipment(ActionEvent event) {
    	equipmentSerialText.setVisible(false);
    	equipmentMakeText.setVisible(false);
    	equipmentModelText.setVisible(false);
    	creatingEquipment = false;
    }

    @FXML
    void handleCreateJob(ActionEvent event) {
    	if(creatingCustomer) {
    		if (! validateCustomerFields()) {
    			return;
    		}
    	}
    	if(creatingEquipment) {
    		if (! validateEquipmentFields()) {
    			return;
    		}
    	}
    	if(! validateDate()) {
    		return;
    	}
    	Equipment equipment = new Equipment(equipmentMakeText.getText()
    			,equipmentModelText.getText(), equipmentSerialText.getText());
    	Customer customer = new Customer(customerCompanyText.getText(), customerNameText.getText()
    			, Integer.parseInt(customerPhoneNumberText.getText().replace("-", ""))
    			, customerEmailText.getText());
    	Job job = new Job(customer, equipment, deadlinePicker.getValue());
    	Database.getInstance().addJob(job);
    	Stage thisStage = (Stage) ((Node)(event.getSource())).getScene().getWindow();
		thisStage.close();
    }
    
    public void showWarning(String warning) {
    	warningLabel.setText(warning);
    }
    
    public boolean validateDate() {
    	if(deadlinePicker.getValue() == null) {
    		showWarning("Please select a deadline for this job");
    		return false;
    	}
    	return true;
    }
    
	public boolean validateCustomerFields() {
		if(! Database.validatePhoneNumer(customerPhoneNumberText.getText())) {
			showWarning("Invalid phone number: "+customerPhoneNumberText.getText()+"\nFormat: ###-###-####");
			customerPhoneNumberText.clear();
			return false;
		}
		if(! Database.validateWord(customerCompanyText.getText())) {
			showWarning("Invalid company name: "+customerCompanyText.getText()+"Please enter only alpha numeric characters");
			customerCompanyText.clear();
			return false;
		}
		if(! Database.validateWord(customerNameText.getText())) {
			showWarning("Invalid name:"+customerNameText.getText()+"Please enter only alphanumeric characters");
			customerNameText.clear();
			return false;
		}
		if(! Database.validEmail(customerEmailText.getText())) {
			showWarning("Invalid email:"+customerEmailText.getText()+"\nFormat: Example@hotmail.com");
			customerEmailText.clear();
			return false;
		}
		return true;
	}
		
	public boolean validateEquipmentFields() {
		if(! Database.validateWord(equipmentMakeText.getText())) {
			showWarning("Please enter only alphanumeric characters for equipment make!");
			return false;
		}
		if(! Database.validateWord(equipmentModelText.getText())) {
			showWarning("Please enter only alphanumeric characters for equipment model!");
			return false;
		}
		if(! Database.validateNumber(equipmentSerialText.getText())) {
			showWarning("Please enter only numbers for equipment serial number!");
			return false;
		}
		return true;
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for(Customer customer : Database.getInstance().getCustomers()) {
			customerMenuButton.getItems().add(new CheckMenuItem(customer.getCustomerName()));
		}
		for(Equipment equipment : Database.getInstance().getEquipment()) {
			equipmentMenuButton.getItems().add(new CheckMenuItem(equipment.getEquipmentMake()+" - "+equipment.getEquipmentModel()));
		}
		//customerMenuButton.setOn
	}
    
}
