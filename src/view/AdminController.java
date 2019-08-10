
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
//github.com/UTSA-Software-Engineering/JobTracker.git

	import java.io.IOException;
	import java.net.URL;
	import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
	import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
	import javafx.collections.ObservableList;
	import javafx.event.ActionEvent;
	import javafx.event.EventHandler;
	import javafx.fxml.FXML;
	import javafx.fxml.FXMLLoader;
	import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
	import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
	import javafx.stage.Stage;
	import launcher.Launcher;
	import model.*;

	/**
	 * The controller for the admin view.
	 * <br>This class allows for an admin user to create new instances of equipment, customers, jobs, and technicians.
	 * @author Kenny
	 *
	 */
	public class AdminController implements EventHandler<ActionEvent>, Initializable {
		@FXML
		private ListView<Customer> clientListView;
		@FXML
		private ListView<Equipment> inventoryListView;
		@FXML
		private ListView<Job> assignedJobsListView, unassignedJobsListView;
		@FXML 
		private ListView<Technician> technicianListView;
		@FXML
		private MenuButton actionMenuButton, memoMenuButton;
		@FXML
		private Label sentLabel;
		@FXML
		private TextArea memoTextArea;
		@FXML
		private CheckMenuItem sendAllCheckMenuItem;
		
		private Database database;
		private HashMap<CheckMenuItem, Technician> memoMap;
		
		public AdminController() {
			database = Database.getInstance();
			this.memoMap = new HashMap<>();
		}
		
		/**
		 * Displays a pop up dialogue box where the user can add equipment to the database of existing equipment.
		 */
		public void handleAddEquipment() {
			try {
				Stage popupWindow = new Stage();
				FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("/view/PopUpAddEquipment.fxml"));
				EquipmentPopupController controller = new EquipmentPopupController();
				loader.setController(controller);
				Parent root = loader.load();
				popupWindow.setScene(new Scene(root));
				popupWindow.show();
				popupWindow.setOnHidden(
						e-> inventoryListView.itemsProperty().set(
						FXCollections.observableArrayList(database.getEquipment())));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		/**
		 * Displays a popup window to the user to add a new customer
		 */
		public void handleAddCustomer() {
			try {
				Stage popupWindow = new Stage();
				FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("/view/PopUpAddCustomer.fxml"));
				CustomerPopupController controller = new CustomerPopupController();
				loader.setController(controller);
				Parent root = loader.load();
				popupWindow.setScene(new Scene(root));
				popupWindow.show();
				popupWindow.setOnHidden(
					e-> clientListView.itemsProperty().set(
					FXCollections.observableArrayList(database.getCustomers())));
			}
			catch (IOException e){
				e.printStackTrace();
			}
		}
		
		
		public void handleAddJob() {
			try {
				Stage popupWindow = new Stage();
				FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("/view/PopUpAddJob.fxml"));
				JobPopUpController controller = new JobPopUpController();
				loader.setController(controller);
				Parent root = loader.load();
				popupWindow.setScene(new Scene(root));
				popupWindow.show();
				/*popupWindow.setOnHidden(
					e-> clientListView.itemsProperty().set(
					FXCollections.observableArrayList(database.getCustomers())));*/
			}
			catch (IOException e){
				e.printStackTrace();
			}
		}
		public void handleHomePressed(ActionEvent event) {
			MainController.switchView(ViewType.Login);
		}
		public void handleAddTechnician() {
			try {
				Stage popupWindow = new Stage();
				FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("/view/PopUpAddTechnician.fxml"));
				TechnicianPopupController controller = new TechnicianPopupController();
				loader.setController(controller);
				Parent root = loader.load();
				popupWindow.setScene(new Scene(root));
				popupWindow.show();
				/*popupWindow.setOnHidden(
					e-> clientListView.itemsProperty().set(
					FXCollections.observableArrayList(database.getCustomers())));*/
			}
			catch (IOException e){
				e.printStackTrace();
			}
		}
		
		@Override
		public void handle(ActionEvent event) {
		}

		public void updateView() {
			ArrayList<Customer> customers = database.getCustomers();
			ObservableList<Customer> clients = FXCollections.observableArrayList(customers);
			clientListView.itemsProperty().set(clients);
			inventoryListView.itemsProperty().set(FXCollections.observableArrayList(database.getEquipment()));
			assignedJobsListView.itemsProperty().set(FXCollections.observableArrayList(database.getAssignedJobs()));
			unassignedJobsListView.itemsProperty().set(FXCollections.observableArrayList(database.getAvailableJobs()));
			technicianListView.itemsProperty().set(FXCollections.observableArrayList(database.getTechnicians()));
			
			for(Technician technician : database.getTechnicians()) {
				CheckMenuItem item = new CheckMenuItem(technician.getName());
				memoMenuButton.getItems().add(item);
				memoMap.put(item, technician);
			}
		}
		public void sendMemo() {
			List<MenuItem> items = (List<MenuItem>) memoMenuButton.getItems();
			String message = memoTextArea.getText();
			for(MenuItem item : items) {
				if(sendAllCheckMenuItem.isSelected() || ((CheckMenuItem)item).isSelected()){
					memoMap.get(item).setNotice(message);
				}
			}
			memoTextArea.clear();
			sentLabel.setText("Message sent!");
		}
		 
		public void handleTechnicianContextMenu() {
			Technician tech = (Technician) (technicianListView.getSelectionModel().getSelectedItem());
			ContextMenu techMenu = new ContextMenu();
			Menu equipmentMenu = new Menu("Add Equipent");
			for(Equipment equipment : database.getEquipment()) {
				MenuItem item = new MenuItem(equipment.toString());
				item.setOnAction(e->tech.addEquipment(equipment));
				equipmentMenu.getItems().add(item);
			}
			techMenu.getItems().add(equipmentMenu);
			MenuItem statusItem = new MenuItem("Set Status");
			statusItem.setOnAction(e->tech.setStatus(promptStatus()));
			techMenu.getItems().add(statusItem);
			technicianListView.setContextMenu(techMenu);
		}
		
		public String promptStatus() {
			TextInputDialog prompt = new TextInputDialog();
			prompt.showAndWait();
			return prompt.getEditor().getText();
		}
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			updateView();
			technicianListView.setOnContextMenuRequested(e->handleTechnicianContextMenu());
		}	
		
	}
