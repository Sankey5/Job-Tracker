
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
import model.Job;
import model.Priority;
import model.Technician;
import javafx.scene.control.TextArea;

public class TechnicianController implements EventHandler<ActionEvent>, Initializable {

	@FXML
	private Button newJob;
	@FXML
	private ListView<Job> availableJobsListView, expressJobsListView, regularJobsListView, slowJobsListView, completedJobsListView;
	@FXML
	private TextArea detailsTextArea;
	@FXML
	private TextField editField;
	
	ObservableList<Job> expressObservableList, regularObservableList, slowObservableList;
	
	private Technician tech;
	private Database database;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tech = MainController.getInstance().getSelectedTech();
		database = Database.getInstance();
		
		ObservableList<Job> availablejobs = FXCollections.observableArrayList(database.getJobs());
		ObservableList<Job> completedjobs = FXCollections.observableArrayList(tech.getCompletedJobs());
		
		ArrayList<Job> expressJobsUnsorted = new ArrayList<Job>();
		ArrayList<Job> regularJobsUnsorted = new ArrayList<Job>();
		ArrayList<Job> slowJobsUnsorted = new ArrayList<Job>();
		
		Collections.sort(expressJobsUnsorted);
		Collections.sort(regularJobsUnsorted);
		Collections.sort(slowJobsUnsorted);
		
		expressObservableList = FXCollections.observableArrayList(expressJobsUnsorted);
		regularObservableList = FXCollections.observableArrayList(regularJobsUnsorted);
		slowObservableList = FXCollections.observableArrayList(slowJobsUnsorted);
		
		expressJobsListView.setItems(expressObservableList);
		regularJobsListView.setItems(regularObservableList);
		slowJobsListView.setItems(slowObservableList);
		availableJobsListView.setItems(availablejobs);
		completedJobsListView.setItems(completedjobs);
		
		if(tech.getMyJobs().size() == 0) {
	
		} else {
			detailsTextArea.setText(tech.getMyJobs().get(0).toDescription());
		}
		
	}
	
	@FXML
	public void logoutAction(ActionEvent event) {
		MainController.switchView(ViewType.Login);
	}
	
	@FXML
	public void backAction(ActionEvent event) {
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
	
	private void moveJob(Job jobToMove) {
		switch(jobToMove.getPriority()) {
		case High:
			expressObservableList.add(jobToMove);
			Collections.sort(expressObservableList);
			expressJobsListView.setItems(expressObservableList);
			break;
		case Medium:
			regularObservableList.add(jobToMove);
			Collections.sort(regularObservableList);
			regularJobsListView.setItems(regularObservableList);
			break;
		case Low:
			slowObservableList.add(jobToMove);
			Collections.sort(slowObservableList);
			slowJobsListView.setItems(slowObservableList);
			break;
		}
	}
		
	public void availableMenuAdd(ActionEvent event) {
		
		if(availableJobsListView.getSelectionModel().isEmpty()){
			return;
		} else {
		Job available = availableJobsListView.getSelectionModel().getSelectedItem();
		if(tech.giveJob(available)) {
			database.getJobs().remove(available);
			tech.getMyJobs().add(available);
			moveJob(available);
			availableJobsListView.setItems(FXCollections.observableArrayList(database.getJobs()));
		} else {
			detailsTextArea.appendText("\n\n\nUnable to add job!!");
		}
		}
	}
	
	public void expressMenuComplete(ActionEvent event) {
		if(expressJobsListView.getSelectionModel().isEmpty()){
			return;
		} 
		
		Job current = expressJobsListView.getSelectionModel().getSelectedItem();
		tech.completedJob(current);
		expressJobsListView.setItems(FXCollections.observableArrayList(tech.getMyJobs()));
		completedJobsListView.setItems(FXCollections.observableArrayList(tech.getCompletedJobs()));
		
	}
	
	public void expressMenuRemove(ActionEvent event) {
		if(expressJobsListView.getSelectionModel().isEmpty()){
			return;
		} 
		
		Job current = expressJobsListView.getSelectionModel().getSelectedItem();
		database.addJob(current);
		tech.removeJob(current);
		expressJobsListView.setItems(FXCollections.observableArrayList(tech.getMyJobs()));
		availableJobsListView.setItems(FXCollections.observableArrayList(database.getJobs()));
		
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
		
	}
	
	public void regularMenuComplete(ActionEvent event) {
		if(regularJobsListView.getSelectionModel().isEmpty()){
			return;
		} 
		
		Job current = regularJobsListView.getSelectionModel().getSelectedItem();
		tech.completedJob(current);
		regularJobsListView.setItems(FXCollections.observableArrayList(tech.getMyJobs()));
		completedJobsListView.setItems(FXCollections.observableArrayList(tech.getCompletedJobs()));
		
	}
	
	public void regularMenuRemove(ActionEvent event) {
		if(regularJobsListView.getSelectionModel().isEmpty()){
			return;
		} 
		
		Job current = regularJobsListView.getSelectionModel().getSelectedItem();
		database.addJob(current);
		tech.removeJob(current);
		regularJobsListView.setItems(FXCollections.observableArrayList(tech.getMyJobs()));
		availableJobsListView.setItems(FXCollections.observableArrayList(database.getJobs()));
		
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
		
	}
	
	public void slowMenuComplete(ActionEvent event) {
		if(slowJobsListView.getSelectionModel().isEmpty()){
			return;
		} 
		
		Job current = slowJobsListView.getSelectionModel().getSelectedItem();
		tech.completedJob(current);
		slowJobsListView.setItems(FXCollections.observableArrayList(tech.getMyJobs()));
		completedJobsListView.setItems(FXCollections.observableArrayList(tech.getCompletedJobs()));
		
	}
	
	public void slowMenuRemove(ActionEvent event) {
		if(slowJobsListView.getSelectionModel().isEmpty()){
			return;
		} 
		
		Job current = slowJobsListView.getSelectionModel().getSelectedItem();
		database.addJob(current);
		tech.removeJob(current);
		slowJobsListView.setItems(FXCollections.observableArrayList(tech.getMyJobs()));
		availableJobsListView.setItems(FXCollections.observableArrayList(database.getJobs()));
		
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
		
	}
	
	public void completedMenuReturn(ActionEvent event) {
		if(completedJobsListView.getSelectionModel().isEmpty()){
			return;
		} 
		
		Job current = completedJobsListView.getSelectionModel().getSelectedItem();
		tech.giveJob(current);
		tech.getCompletedJobs().remove(current);
		database.getJobs().add(current);
		moveJob(current);
		completedJobsListView.setItems(FXCollections.observableArrayList(tech.getCompletedJobs()));
		
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
		// TODO Auto-generated method stub
	}
}
