package view;

import java.awt.Label;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.Customer;
import model.Database;

public class CustomerPopupController implements Initializable, EventHandler<ActionEvent> {
	
	@FXML
	private TextField companyTextField, nameTextField
	, phoneTextField, emailTextField;
	@FXML
	private Label warningLabel;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Display a warning message to the user
	 * @param warning - String message to display
	 */
	public void showWarning(String warning) {
		warningLabel.setText(warning);
	}
	
	/**
	 * Clears the warning label
	 */
	public void clearWarning() {
		warningLabel.setText("");
	}


	@Override
	/**
	 * Add customer button pressed.
	 */
	public void handle(ActionEvent event) {
		clearWarning();
		int phoneNumber = Integer.parseInt(phoneTextField.getText());
		String companyName = companyTextField.getText();
		String name = nameTextField.getText();
		String email = emailTextField.getText();
		
		/**
		 * verify that all user provided field are valid
		 */
		
		if(! isValidPhone(phoneNumber)) {
			showWarning("Invalid phone number: "+phoneNumber+"\nFormat: ##########");
			phoneTextField.clear();
			return;
		}
		if(! isValidCompanyName()) {
			showWarning("Invalid company name: "+companyName+"\nFormat: *Give format here*");
			companyTextField.clear();
			return;
		}
		if(! isValidName()) {
			showWarning("Invalid name:"+name+"Format: *give format here*");
			nameTextField.clear();
			return;
		}
		if(! isValidEmail()) {
			showWarning("Invalid email:"+email+"Format: *give valid email format here");
			emailTextField.clear();
			return;
		}
		
		Customer customer = new Customer(companyName, name, phoneNumber, email);
		Database.getInstance().getCustomers().add(customer);
		
	}

	private boolean isValidEmail() {
		// TODO Auto-generated method stub
		return true;
	}

	private boolean isValidName() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * Verify that the company name provided is valid
	 * @return
	 */
	private boolean isValidCompanyName() {
		return true;
	}

	/**
	 * Checks for a valid phone number
	 * TODO: implement RegEx check
	 * @param phoneNumber
	 * @return
	 */
	public boolean isValidPhone(int phoneNumber) {
		return true;
	}
	
}
