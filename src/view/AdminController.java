
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
	import java.util.ResourceBundle;

	import javafx.collections.FXCollections;
	import javafx.collections.ObservableList;
	import javafx.event.ActionEvent;
	import javafx.event.EventHandler;
	import javafx.fxml.FXML;
	import javafx.fxml.FXMLLoader;
	import javafx.fxml.Initializable;
	import javafx.scene.Parent;
	import javafx.scene.Scene;
	import javafx.scene.control.ListView;
	import javafx.scene.control.MenuButton;
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
		private ListView<Job> jobsListView;
		@FXML
		private MenuButton actionMenuButton;
		@FXML
		private TextField memoTextField;
		
		private Database database;
		
		public AdminController() {
			database = Database.getInstance();
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
			
		}
		
		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			ObservableList<Customer> clients = FXCollections.observableArrayList(database.getCustomers());
			clientListView.itemsProperty().set(clients);
			inventoryListView.itemsProperty().set(FXCollections.observableArrayList(database.getEquipment()));
			jobsListView.itemsProperty().set(FXCollections.observableArrayList(database.getJobs()));
		}
		
	}
