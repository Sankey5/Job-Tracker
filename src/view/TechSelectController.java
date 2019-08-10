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
		
		if(database.getInstance().getTechnicians().isEmpty()) {
		Technician tempTech = new Technician();
		tempTech.setName("Jason");
		tempTech.setPhoneNumber("123456789");
		tempTech.setStats("Doing Pretty Well");
		tempTech.setStatus("New Hire");
		tempTech.setNotice("Generated from login");
		int j = 4;
		for(int i = 0; i < j; i++) {
			Customer guy = new Customer("Place"+i, "Person"+i, 123456789, "thisguy@email"+i);
			Equipment tool = new Equipment("Make"+i, "Model"+i, "Serial"+i);
			Job work = new Job(guy, tool, LocalDate.of(2020, Month.APRIL, i+1));
			tempTech.addEquipment(tool);
			tempTech.giveJob(work);
		}
		
		for(int i = j; i < j + 4; i++) {
			Customer guy = new Customer("Place"+i, "Person"+i, 123456789, "thisguy@email"+i);
			Equipment tool = new Equipment("Make"+(i-2), "Model"+(i-2), "Serial"+(i-2));
			Job work = new Job(guy, tool, LocalDate.of(2020, Month.FEBRUARY, i+1));
			database.addJob(work);
		}
		for(int i = 0; i < 3;i++) {
			Customer guy = new Customer("Place"+(i+10), "Person"+(i+10), 123456789, "thisguy@email"+(i+10));
			Equipment tool = new Equipment("Make"+i, "Model"+i, "Serial"+i);
			LocalDate date = LocalDate.of(2020, Month.AUGUST, i+1);
			Job work = new Job(guy, tool, date);
			tempTech.giveJob(work);
			tempTech.completedJob(work);
		}
		database.getTechnicians().add(tempTech);
		}
		ObservableList<Technician> something = FXCollections.observableArrayList(database.getTechnicians());
		
		techListView.setItems(something);
		
	}
	
	@FXML
    void selectTechnician(MouseEvent event) {

		if(event.getClickCount() < 2 || techListView.getSelectionModel().isEmpty()) {
			return;
		}
		
		MainController.getInstance().setSelectedTech(techListView.getSelectionModel().getSelectedItem());
		MainController.switchView(ViewType.Technician);
    }
	
	@FXML
    void goBack(ActionEvent event) {
		MainController.switchView(ViewType.Login);
    }
}
