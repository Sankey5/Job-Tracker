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
    	customerEmailText.setDisable(false);
    	customerEmailText.clear();
    	customerPhoneNumberText.setDisable(false);
    	customerPhoneNumberText.clear();
    	customerCompanyText.setDisable(false);
    	customerCompanyText.clear();
    	customerNameText.setDisable(false);
    	customerNameText.clear();
    	creatingCustomer = true;
    }
    @FXML
    void handleNewEquipment(ActionEvent event) {
    	System.out.print("asdfdf");
    	equipmentSerialText.setDisable(false);
    	equipmentSerialText.clear();
    	equipmentMakeText.setDisable(false);
    	equipmentMakeText.clear();
    	equipmentModelText.setDisable(false);
    	equipmentModelText.clear();
    	creatingEquipment = true;
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
    public void populateCustomerFields(Customer customer) {
    	customerCompanyText.setText(customer.getCustomerCompanyName());
    	customerCompanyText.setDisable(true);
    	customerNameText.setText(customer.getCustomerName());
    	customerNameText.setDisable(true);
    	customerEmailText.setText(customer.getCustomerEmail());
    	customerEmailText.setDisable(true);
    	customerPhoneNumberText.setText(String.valueOf(customer.getCustomerPhoneNumber()));
    	customerPhoneNumberText.setDisable(true);
    	creatingCustomer = false;
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

	private void populateEquipmentFields(Equipment equipment) {
		equipmentMakeText.setText(equipment.getEquipmentMake());
		equipmentMakeText.setDisable(true);
		equipmentModelText.setText(equipment.getEquipmentModel());
		equipmentModelText.setDisable(true);
		equipmentSerialText.setText(equipment.getEquipmentSerial());
		equipmentSerialText.setDisable(true);
		creatingEquipment = false;
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for(Customer customer : Database.getInstance().getCustomers()) {
			MenuItem item = new MenuItem(customer.getCustomerName());
			item.setOnAction(e-> populateCustomerFields(customer));
			customerMenuButton.getItems().add(item);
		}
		
		for(Equipment equipment : Database.getInstance().getEquipment()) {
			MenuItem item = new MenuItem(equipment.getEquipmentMake()+" - "+equipment.getEquipmentModel());
			item.setOnAction(e-> populateEquipmentFields(equipment));
			equipmentMenuButton.getItems().add(item);
		}
		
		
	}
    
}
