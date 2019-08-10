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
//		if()

//		Customer customer = new Customer(companyName, name, phoneNumber, email);
		Database.getInstance().addCustomer(new Customer(companyName, name, phoneNumber, email));
		Stage thisStage = (Stage) ((Node)(event.getSource())).getScene().getWindow();
		thisStage.close();
		
	}

	/*public boolean validateFields() {
		
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
		
	}*/

	
}
