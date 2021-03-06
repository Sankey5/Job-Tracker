package view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.Screen;
import javafx.stage.Stage;
import launcher.Launcher;
import model.Database;
import model.Equipment;
import model.Job;
import model.Priority;
import model.Technician;
import javafx.scene.control.TextArea;

public class TechnicianController implements EventHandler<ActionEvent>, Initializable {

	@FXML
	private Button newJob, filterButton;;
	@FXML
	private ListView<Job> availableJobsListView, expressJobsListView, regularJobsListView, slowJobsListView, completedJobsListView;
	@FXML
	private TextArea detailsTextArea, extrasTextArea;
	@FXML
	private TextField editField;
	private boolean fired;
	ObservableList<Job> expressObservableList, regularObservableList, slowObservableList, availablejobs, completedjobs;
	
	private Technician tech;
	private Database database;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateJobQueues();
		fired = false;
		if(tech.getMyJobs().size() == 0) {
	
		} else {
			detailsTextArea.setText(tech.getMyJobs().get(0).toDescription());
		}
		extrasTextArea.setText(tech.toExtras());
		
	}
	
	@FXML
	public void logoutAction(ActionEvent event) {
		database.save();
		MainController.switchView(ViewType.Login);
	}
	
	@FXML
	public void backAction(ActionEvent event) {
		database.save();
		MainController.switchView(ViewType.TechSelect);
	}
	
	@FXML
	public void handleExpressMouseClick(MouseEvent click) {
			
		if(expressJobsListView.getSelectionModel().isEmpty()){
			return;
		}
		Job current = expressJobsListView.getSelectionModel().getSelectedItem();
	
		for (int i = 0; i < tech.getMyJobs().size(); i++) {
			if(current.equals(tech.getMyJobs().get(i))) {
				detailsTextArea.setText(tech.getMyJobs().get(i).toDescription());
			} 
		}
		
	}
	
	@FXML
	public void handleRegularMouseClick(MouseEvent click) {
			
		if(regularJobsListView.getSelectionModel().isEmpty()){
			return;
		}
		Job current = regularJobsListView.getSelectionModel().getSelectedItem();
	
		for (int i = 0; i < tech.getMyJobs().size(); i++) {
			if(current.equals(tech.getMyJobs().get(i))) {
				detailsTextArea.setText(tech.getMyJobs().get(i).toDescription());
			} 
		}
		
	}
	
	@FXML
	public void handleSlowMouseClick(MouseEvent click) {
			
		if(slowJobsListView.getSelectionModel().isEmpty()){
			return;
		}
		Job current = slowJobsListView.getSelectionModel().getSelectedItem();
	
		for (int i = 0; i < tech.getMyJobs().size(); i++) {
			if(current.equals(tech.getMyJobs().get(i))) {
				detailsTextArea.setText(tech.getMyJobs().get(i).toDescription());
			} 
		}
		
	}
	
	@FXML
	public void handleAvailableMouseClick(MouseEvent click) {
		
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
	public void handleCompletedMouseClick(MouseEvent click) {
		
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
	
	private void updateJobQueues() {
		tech = MainController.getInstance().getSelectedTech();
		database = Database.getInstance();
		
		availablejobs = FXCollections.observableArrayList(database.getJobs());
		completedjobs = FXCollections.observableArrayList(tech.getCompletedJobs());
		
		ArrayList<Job> expressJobsUnsorted = new ArrayList<Job>();
		ArrayList<Job> regularJobsUnsorted = new ArrayList<Job>();
		ArrayList<Job> slowJobsUnsorted = new ArrayList<Job>();
		
		for(Job temp: tech.getMyJobs()) {
			if(temp.getPriority() == Priority.High)
				expressJobsUnsorted.add(temp);
			if(temp.getPriority() == Priority.Medium)
				regularJobsUnsorted.add(temp);
			if(temp.getPriority() == Priority.Low)
				slowJobsUnsorted.add(temp);
		}
		
		expressObservableList = FXCollections.observableArrayList(expressJobsUnsorted);
		regularObservableList = FXCollections.observableArrayList(regularJobsUnsorted);
		slowObservableList = FXCollections.observableArrayList(slowJobsUnsorted);
		
		expressJobsListView.setItems(expressObservableList);
		regularJobsListView.setItems(regularObservableList);
		slowJobsListView.setItems(slowObservableList);
		availableJobsListView.setItems(availablejobs);
		completedJobsListView.setItems(completedjobs);
		
		filterButton.setText("All Jobs");
		fired = false;
	}
		
	public void availableMenuAdd(ActionEvent event) {
		
		if(availableJobsListView.getSelectionModel().isEmpty()){
			return;
		}
		
		Job available = availableJobsListView.getSelectionModel().getSelectedItem();
		if(tech.giveJob(available)) {
			database.getJobs().remove(available);
			updateJobQueues();
			availableJobsListView.setItems(FXCollections.observableArrayList(database.getJobs()));
		} else {
			detailsTextArea.appendText("\n\n\n"+available.getEquipment().toString().replace("\n","")+" required for this job.");
		}
		extrasTextArea.setText(tech.toExtras());
	}
	
	public void expressMenuComplete(ActionEvent event) {
		
		if(expressJobsListView.getSelectionModel().isEmpty()){
			return;
		} 
		
		Job current = expressJobsListView.getSelectionModel().getSelectedItem();
		tech.completedJob(current);
		updateJobQueues();
		completedJobsListView.setItems(FXCollections.observableArrayList(tech.getCompletedJobs()));
		extrasTextArea.setText(tech.toExtras());
	}
	
	public void expressMenuRemove(ActionEvent event) {
		if(expressJobsListView.getSelectionModel().isEmpty()){
			return;
		} 
		
		Job current = expressJobsListView.getSelectionModel().getSelectedItem();
		database.addJob(current);
		tech.removeJob(current);
		updateJobQueues();
		availableJobsListView.setItems(FXCollections.observableArrayList(database.getJobs()));
		extrasTextArea.setText(tech.toExtras());
	}
	
	public void expressMenuDetails(ActionEvent event) {
		if(expressJobsListView.getSelectionModel().isEmpty()){
			return;
		}
		
		Job current = expressJobsListView.getSelectionModel().getSelectedItem();
		if(editField.getText().isEmpty()) {
			return;
		}
		current.setMemo(editField.getText());
		detailsTextArea.setText(current.toDescription());
		editField.clear();
		extrasTextArea.setText(tech.toExtras());
	}
	
	public void regularMenuComplete(ActionEvent event) {
		if(regularJobsListView.getSelectionModel().isEmpty()){
			return;
		} 
		
		Job current = regularJobsListView.getSelectionModel().getSelectedItem();
		tech.completedJob(current);
		updateJobQueues();
		completedJobsListView.setItems(FXCollections.observableArrayList(tech.getCompletedJobs()));
		extrasTextArea.setText(tech.toExtras());
	}
	
	public void regularMenuRemove(ActionEvent event) {
		if(regularJobsListView.getSelectionModel().isEmpty()){
			return;
		} 
		
		Job current = regularJobsListView.getSelectionModel().getSelectedItem();
		database.addJob(current);
		tech.removeJob(current);
		updateJobQueues();
		availableJobsListView.setItems(FXCollections.observableArrayList(database.getJobs()));
		extrasTextArea.setText(tech.toExtras());
	}
	
	public void regularMenuDetails(ActionEvent event) {
		if(regularJobsListView.getSelectionModel().isEmpty()){
			return;
		}
		
		Job current = regularJobsListView.getSelectionModel().getSelectedItem();
		if(editField.getText().isEmpty()) {
			return;
		}
		current.setMemo(editField.getText());
		detailsTextArea.setText(current.toDescription());
		editField.clear();
		extrasTextArea.setText(tech.toExtras());
	}
	
	public void slowMenuComplete(ActionEvent event) {
		if(slowJobsListView.getSelectionModel().isEmpty()){
			return;
		} 
		
		Job current = slowJobsListView.getSelectionModel().getSelectedItem();
		tech.completedJob(current);
		updateJobQueues();
		completedJobsListView.setItems(FXCollections.observableArrayList(tech.getCompletedJobs()));
		extrasTextArea.setText(tech.toExtras());
	}
	
	public void slowMenuRemove(ActionEvent event) {
		if(slowJobsListView.getSelectionModel().isEmpty()){
			return;
		} 
		
		Job current = slowJobsListView.getSelectionModel().getSelectedItem();
		database.addJob(current);
		tech.removeJob(current);
		updateJobQueues();
		availableJobsListView.setItems(FXCollections.observableArrayList(database.getJobs()));
		extrasTextArea.setText(tech.toExtras());
	}
	
	public void slowMenuDetails(ActionEvent event) {
		if(slowJobsListView.getSelectionModel().isEmpty()){
			return;
		}
		
		Job current = slowJobsListView.getSelectionModel().getSelectedItem();
		if(editField.getText().isEmpty()) {
			return;
		}
		current.setMemo(editField.getText());
		detailsTextArea.setText(current.toDescription());
		editField.clear();
		extrasTextArea.setText(tech.toExtras());
	}
	
	public void completedMenuReturn(ActionEvent event) {
		if(completedJobsListView.getSelectionModel().isEmpty()){
			return;
		} 
		
		Job current = completedJobsListView.getSelectionModel().getSelectedItem();
		if(! tech.giveJob(current)) {
			detailsTextArea.appendText(tech.getName()+" is not trained to use "+ current.getEquipment());
			return;
		}
		tech.getCompletedJobs().remove(current);
		updateJobQueues();
		completedJobsListView.setItems(FXCollections.observableArrayList(tech.getCompletedJobs()));
		extrasTextArea.setText(tech.toExtras());
	}
	
	@FXML
    void handleNewJob(ActionEvent event) {
		try {
			Stage popupWindow = new Stage();
			FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("/view/PopUpAddJob.fxml"));
			JobPopUpController controller = new JobPopUpController();
			loader.setController(controller);
			Parent root = loader.load();
			popupWindow.setScene(new Scene(root));
			popupWindow.show();
			popupWindow.setOnHidden(
				e-> availableJobsListView.setItems(FXCollections.observableArrayList(database.getJobs())));
		}
		catch (IOException e){
			e.printStackTrace();
		}
    }
	
	public void filterAction(ActionEvent event) {
		ArrayList<Job> matched = new ArrayList<Job>();
		if(fired == false) {
			availableJobsListView.setItems(null);
			filterButton.setText("Acceptable Jobs");
			ArrayList<Job> jobList = database.getJobs();
			for(int i = 0; i < jobList.size(); i++) {
				Equipment equipment = jobList.get(i).getEquipment();
				for(int k = 0; k < tech.getEquipmentList().size(); k++) {
					if(equipment.compareTo(tech.getEquipmentList().get(k))) {
						matched.add(jobList.get(i));
					}
				}
				
			}
			
			ObservableList<Job> matchList= FXCollections.observableArrayList(matched);
			availableJobsListView.setItems(matchList);
			fired = true;
			
		} else if(fired == true) {//if (availableJobsListView.getItems().size() < database.getJobs().size()) {
			availableJobsListView.setItems(FXCollections.observableArrayList(database.getJobs()));
			filterButton.setText("All Jobs");
			fired = false;
		}
		
	}
	
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
	}
}
