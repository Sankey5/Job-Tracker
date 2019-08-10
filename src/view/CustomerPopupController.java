/*******************************************************************************
 * Copyright (c) 2019 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
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
	 * Add customer button pressed. This function verify that all input fields contain valid data,
	 * then create a new customer from those fields. If a field is not valid, a warning message
	 * will be displayed to the user in the Label warningLabel.
	 */
	public void handle(ActionEvent event) {
		clearWarning();
		if(! validateFields()) {
			return;
		}
		int phoneNumber = Integer.parseInt(phoneTextField.getText().replaceAll("-", ""));
		String companyName = companyTextField.getText();
		String name = nameTextField.getText();
		String email = emailTextField.getText();
		Customer customer = new Customer(companyName, name, phoneNumber, email);
		Database.getInstance().addCustomer(new Customer(companyName, name, phoneNumber, email));
		Stage thisStage = (Stage) ((Node)(event.getSource())).getScene().getWindow();
		thisStage.close();
	}

	/**
	 * Validate each field. Set an appropriate error message if an incorrect
	 * value is detected in the input field.
	 * @return - True if all inputs are valid
	 * <br> - False if an error was detected. (shows warning)
	 */
	public boolean validateFields() {
		
		if(! Database.validatePhoneNumer(phoneTextField.getText())) {
			showWarning("Invalid phone number: "+phoneTextField.getText()+"\nFormat: ###-###-####");
			phoneTextField.clear();
			return false;
		}
		if(! Database.validateWord(companyTextField.getText())) {
			showWarning("Invalid company name: "+companyTextField.getText()+"\nPlease enter only alphanumeric characters");
			companyTextField.clear();
			return false;
		}
		if(! Database.validateWord(nameTextField.getText())) {
			showWarning("Invalid name:"+nameTextField.getText()+"\nPlease enter only alphanumeric characters");
			nameTextField.clear();
			return false;
		}
		if(! Database.validEmail(emailTextField.getText())) {
			showWarning("Invalid email:"+emailTextField.getText()+"\nFormat: BobSmith@hotmail.com");
			emailTextField.clear();
			return false;
		}
		return true;
		
	}

	
}
