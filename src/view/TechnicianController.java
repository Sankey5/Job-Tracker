package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Database;
import model.Technician;
import javafx.scene.control.TextArea;

public class TechnicianController implements EventHandler<ActionEvent>, Initializable {

	@FXML
	private Button updateDateButton;
	@FXML
	private ListView<String> availableJobsListView, currentJobsListView, completedJobsListView;
	@FXML
	private DatePicker startDatePicker, endDatePicker;
	@FXML
	private TextArea detailsTextArea, extrasTextArea;
	private Technician tech;
	private Database database;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub	
		
		tech = MainController.getInstance().getSelectedTech();
		database = Database.getInstance();
		ObservableList<String> availablejobs = FXCollections.observableArrayList(Database.getInstance().getStringJobs());
		ObservableList<String> currentjobs = FXCollections.observableArrayList(tech.getStringJobs());
		ObservableList<String> completedjobs = FXCollections.observableArrayList(tech.getStringJobs());
		currentJobsListView.setItems(currentjobs);
		availableJobsListView.setItems(availablejobs);
		completedJobsListView.setItems(completedjobs);
		if(tech.getMyJobs().size() == 0) {
	
		} else {
			detailsTextArea.setText(tech.getMyJobs().get(0).toDescription());
		}
		extrasTextArea.setText(tech.toExtras());
		
	}
	
	
	public void logoutAction(ActionEvent event) {
		MainController.switchView(ViewType.Login);
	}
	
	@FXML
	public void handleMouseClick(MouseEvent click) {
		
			String current = currentJobsListView.getSelectionModel().getSelectedItem();
			System.out.println(current);
			for (int i = 0; i < tech.getMyJobs().size(); i++) {
				if(current.equals(tech.getMyJobs().get(i).toString())) {
					detailsTextArea.setText(tech.getMyJobs().get(i).toDescription());
				}
			}	
			String available = availableJobsListView.getSelectionModel().getSelectedItem();
			System.out.println(available);
			for (int i = 0; i < database.getJobs().size(); i++) {
				if(available.equals(database.getJobs().get(i).toString())) {
					detailsTextArea.setText(database.getJobs().get(i).toDescription());
				}
			}	
	}
	

	
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
	}
}
