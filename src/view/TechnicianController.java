
package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import launcher.Launcher;
import model.Database;
import model.Job;
import model.Technician;
import javafx.scene.control.TextArea;

public class TechnicianController implements EventHandler<ActionEvent>, Initializable {

	@FXML
	private Button updateDateButton;
	@FXML
	private ListView<Job> availableJobsListView, currentJobsListView, completedJobsListView;
	@FXML
	private DatePicker startDatePicker, endDatePicker;
	@FXML
	private TextArea detailsTextArea, extrasTextArea;
	
	private Technician tech;
	private Database database;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub	
		Launcher.stage.setFullScreen(true);
		tech = MainController.getInstance().getSelectedTech();
		database = Database.getInstance();
		ObservableList<Job> availablejobs = FXCollections.observableArrayList(Database.getInstance().getJobs());
		ObservableList<Job> currentjobs = FXCollections.observableArrayList(tech.getMyJobs());
		ObservableList<Job> completedjobs = FXCollections.observableArrayList(tech.getCompletedJobs());
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
		Launcher.stage.setFullScreen(false);
		MainController.switchView(ViewType.Login);
	}
	
	@FXML
	public void handleMouseClick(MouseEvent click) {
			
		if(currentJobsListView.getSelectionModel().isEmpty()){
			return;
		} else {
			Job current = currentJobsListView.getSelectionModel().getSelectedItem();
		
			for (int i = 0; i < tech.getMyJobs().size(); i++) {
				if(current.equals(tech.getMyJobs().get(i))) {
					detailsTextArea.setText(tech.getMyJobs().get(i).toDescription());
				} 
			}
		}
	}
	@FXML
	public void handleMouseClick2(MouseEvent click) {
		
		if(availableJobsListView.getSelectionModel().isEmpty()){
			return;
		} else {
			Job available = availableJobsListView.getSelectionModel().getSelectedItem();
			for (int i = 0; i < database.getJobs().size(); i++) {
				if(available.equals(database.getJobs().get(i))) {
					detailsTextArea.setText(database.getJobs().get(i).toDescription());
				}
			}	
		}
	}
	@FXML
	public void handleMouseClick3(MouseEvent click) {
		
		if(completedJobsListView.getSelectionModel().isEmpty()){
			return;
		} else {
			Job completed = completedJobsListView.getSelectionModel().getSelectedItem();
			for (int i = 0; i < tech.getCompletedJobs().size(); i++) {
				if(completed.equals(tech.getCompletedJobs().get(i))) {
					detailsTextArea.setText(tech.getCompletedJobs().get(i).toDescription());
				}
			}	
		}
	}
		
	public void availableMenuAdd(ActionEvent event) {
		
		if(availableJobsListView.getSelectionModel().isEmpty()){
			return;
		} else {
		Job available = availableJobsListView.getSelectionModel().getSelectedItem();
		if(tech.giveJob(available)) {
			database.getInstance().getJobs().remove(available);
			currentJobsListView.setItems(FXCollections.observableArrayList(tech.getMyJobs()));
			availableJobsListView.setItems(FXCollections.observableArrayList(Database.getInstance().getJobs()));
		} else {
			detailsTextArea.appendText("\n\n\nUnable to add job!!");
		}
		extrasTextArea.setText(tech.toExtras());
		}
	}
	
	public void currentMenuComplete(ActionEvent event) {
		if(currentJobsListView.getSelectionModel().isEmpty()){
			return;
		} else {
		Job current = currentJobsListView.getSelectionModel().getSelectedItem();
		tech.completedJob(current);
		currentJobsListView.setItems(FXCollections.observableArrayList(tech.getMyJobs()));
		completedJobsListView.setItems(FXCollections.observableArrayList(tech.getCompletedJobs()));
		extrasTextArea.setText(tech.toExtras());
		}
	}
	
	public void currentMenuRemove(ActionEvent event) {
		if(currentJobsListView.getSelectionModel().isEmpty()){
			return;
		} else {
		Job current = currentJobsListView.getSelectionModel().getSelectedItem();
		database.getInstance().addJob(current);
		tech.removeJob(current);
		currentJobsListView.setItems(FXCollections.observableArrayList(tech.getMyJobs()));
		availableJobsListView.setItems(FXCollections.observableArrayList(Database.getInstance().getJobs()));
		extrasTextArea.setText(tech.toExtras());
		}
	}
	
	public void completedMenuReturn(ActionEvent event) {
		if(completedJobsListView.getSelectionModel().isEmpty()){
			return;
		} else {
		Job current = completedJobsListView.getSelectionModel().getSelectedItem();
		tech.giveJob(current);
		tech.getCompletedJobs().remove(current);
		currentJobsListView.setItems(FXCollections.observableArrayList(tech.getMyJobs()));
		completedJobsListView.setItems(FXCollections.observableArrayList(tech.getCompletedJobs()));
		extrasTextArea.setText(tech.toExtras());
		}
	}
	
	
	
	
	
	
	
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
	}
}
