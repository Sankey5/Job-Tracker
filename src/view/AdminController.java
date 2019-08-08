package view;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import model.Database;

/**
 * The controller for the admin view.
 * <br>This class allows for an admin user to create new instances of equipment, customers, jobs, and technicians.
 * @author Kenny
 *
 */
public class AdminController implements EventHandler<ActionEvent>, Initializable {
	@FXML
	private ListView statisticsListView, memoListView, jobsListView;
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
		
	}
	
	
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
