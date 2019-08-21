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
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import model.Customer;
import model.Database;
import model.Equipment;
import model.Job;
import model.Technician;

public class TechSelectController implements Initializable{
	@FXML
    private BorderPane borderPane;
	
	@FXML
	private ListView<Technician> techListView;
	
	
	public TechSelectController() {}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Database database = Database.getInstance();

		ObservableList<Technician> techniciansObservableList = FXCollections.observableArrayList(database.getTechnicians());
		
		techListView.setItems(techniciansObservableList);
		
	}
	
	@FXML
    void selectTechnician(MouseEvent event) {

		if(event.getClickCount() < 2 || techListView.getSelectionModel().isEmpty()) {
			return;
		}
		
		Database.getInstance().load();
		
		MainController.getInstance().setSelectedTech(techListView.getSelectionModel().getSelectedItem());
		MainController.switchView(ViewType.Technician);
    }
	
	@FXML
    void goBack(ActionEvent event) {
		MainController.switchView(ViewType.Login);
    }
}
