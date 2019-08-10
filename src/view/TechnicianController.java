
package view;

import java.io.IOException;
import java.net.URL;
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
import model.Technician;
import javafx.scene.control.TextArea;

public class TechnicianController implements EventHandler<ActionEvent>, Initializable {

	@FXML
	private Button newJob;
	@FXML
	private ListView<Job> availableJobsListView, currentJobsListView, completedJobsListView;
	@FXML
	private TextArea detailsTextArea, extrasTextArea;
	@FXML
	private TextField editField;
	
	private Technician tech;
	private Database database;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tech = MainController.getInstance().getSelectedTech();
		database = Database.getInstance();
		
		ObservableList<Job> availablejobs = FXCollections.observableArrayList(database.getJobs());
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
	
	@FXML
	public void logoutAction(ActionEvent event) {
		MainController.switchView(ViewType.Login);
	}
	
	@FXML
	public void backAction(ActionEvent event) {
		MainController.switchView(ViewType.TechSelect);
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
	
	public void currentMenuDetails(ActionEvent event) {
		if(currentJobsListView.getSelectionModel().isEmpty()){
			return;
		} else {
			Job current = currentJobsListView.getSelectionModel().getSelectedItem();
			if(editField.getText().isEmpty()) {
				return;
			}
			current.setMemo(editField.getText());
			detailsTextArea.setText(current.toDescription());
			editField.clear();
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
