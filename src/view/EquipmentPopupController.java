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
import model.Database;
import model.Equipment;

public class EquipmentPopupController implements Initializable, EventHandler<ActionEvent>{

	@FXML
	private TextField makeTextField, modelTextField, serialTextField;
	@FXML
	private Label warningLabel;
	
	private final String warnMake = "Please enter only alphanumeric characters for equipment make!";
	private final String warnModel = "Please enter only alphanumeric characters for equipment model!";
	private final String warnSerial = "Please enter only numbers for equipment serial number!";
	
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
	
	/**
	 * This will validate that all fields are valid. If not, it will fail and
	 * display a warning message to the user
	 * @return - True if all fields contain valid inputs
	 * <br> - False if any field contains an illegal character
	 */
	public boolean validateFields() {
		if(! Database.validateWord(makeTextField.getText())) {
			showWarning(warnMake);
			return false;
		}
		if(! Database.validateWord(modelTextField.getText())) {
			showWarning(warnModel);
			return false;
		}
		if(! Database.validateNumber(makeTextField.getText())) {
			showWarning(warnSerial);
			return false;
		}
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
