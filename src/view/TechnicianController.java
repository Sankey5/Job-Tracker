package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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
import model.Job;
import javafx.scene.control.TextArea;

public class TechnicianController implements EventHandler<ActionEvent>, Initializable {

	@FXML
	private Button updateDateButton;
	@FXML
	private ListView<String> currentJobsListView, availableJobsListView, completedJobsListView;
	@FXML
	private DatePicker startDatePicker, endDatePicker;
	@FXML
	private TextArea detailsTextArea, extrasTextArea;
	
	private Database database;
	
	public TechnicianController() {
		this.database = Database.getInstance();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub	
		
		List<String> jobList = LoginController.jobList.stream().map(object -> Objects.toString(object, null)).collect(Collectors.toList());
		ObservableList<String> test = FXCollections.observableArrayList(jobList);
		ObservableList<String> test2 = FXCollections.observableArrayList(LoginController.loginTech.getJobs());
		currentJobsListView.setItems(test2);
		availableJobsListView.setItems(test);
		if(LoginController.loginTech.getJobs().isEmpty()) {
			
		} else {
			detailsTextArea.setText(LoginController.loginTech.getMyJobs().get(0).toDescription());
		}
			
		extrasTextArea.setText(LoginController.loginTech.toExtras());
		
	}
	
	
	public void logoutAction(ActionEvent event) {
		MainController.switchView(ViewType.Login);
	}
	
	@FXML
	public void handleMouseClick(MouseEvent click) {
		System.out.println(currentJobsListView.getSelectionModel().getSelectedItem());
		String data = currentJobsListView.getSelectionModel().getSelectedItem();
		for (int i = 0; i < LoginController.loginTech.getMyJobs().size(); i++) {
			if(data.equals(LoginController.loginTech.getMyJobs().get(i).toString())) {
				detailsTextArea.setText(LoginController.loginTech.getMyJobs().get(i).toDescription());
			}
		}
		
		if(click.getClickCount() == 2) {
			for (int i = 0; i < LoginController.loginTech.getMyJobs().size(); i++) {
				if(data.equals(LoginController.loginTech.getMyJobs().get(i).toString())) {
					LoginController.loginTech.completedJob(LoginController.loginTech.getMyJobs().get(i));
					System.out.println("Completed job");
				}
				currentJobsListView.setItems((ObservableList<String>) FXCollections.observableArrayList(LoginController.loginTech.getJobs()));
				completedJobsListView.setItems((ObservableList<String>) FXCollections.observableArrayList(LoginController.loginTech.getCJobs()));
			}
		}	
		
	}
	
	@FXML
	public void handleMouseClick2(MouseEvent click) {
		System.out.println(availableJobsListView.getSelectionModel().getSelectedItem());
		String data2 = availableJobsListView.getSelectionModel().getSelectedItem();
		for (int i = 0; i < LoginController.jobList.size(); i++) {
			if(data2.equals(LoginController.jobList.get(i).toString())) {
				detailsTextArea.setText(LoginController.jobList.get(i).toDescription());
			}
		}
		
		if(click.getClickCount() == 2) {
			for (int i = 0; i < LoginController.jobList.size(); i++) {
				if(data2.equals(LoginController.jobList.get(i).toString())) {
					if(LoginController.loginTech.giveJob(LoginController.jobList.get(i))) {
						System.out.println("added new job");
						LoginController.jobList.remove(i);
					} 
					currentJobsListView.setItems((ObservableList<String>) FXCollections.observableArrayList(LoginController.loginTech.getJobs()));
					List<String> jobList = LoginController.jobList.stream().map(object -> Objects.toString(object, null)).collect(Collectors.toList());
					availableJobsListView.setItems((ObservableList<String>) FXCollections.observableArrayList(jobList));
				}
			}	
		
		}
	}
	
	@FXML 
	public void handleMouseClick3(MouseEvent click) {
		System.out.println(completedJobsListView.getSelectionModel().getSelectedItem());
		String data3 = completedJobsListView.getSelectionModel().getSelectedItem();
		for (int i = 0; i < LoginController.loginTech.getCompletedJobs().size(); i++) {
			if(data3.equals(LoginController.loginTech.getCompletedJobs().get(i).toString())) {
				detailsTextArea.setText(LoginController.loginTech.getCompletedJobs().get(i).toDescription());
			}
		}
		
		if(click.getClickCount() == 2) {
			for (int i = 0; i < LoginController.loginTech.getCompletedJobs().size(); i++) {
				if(data3.equals(LoginController.loginTech.getCompletedJobs().get(i).toString())) {	
					LoginController.loginTech.giveJob(LoginController.loginTech.getCompletedJobs().get(i));
					System.out.println("readded job");
					LoginController.loginTech.getCompletedJobs().remove(i);
					currentJobsListView.setItems((ObservableList<String>) FXCollections.observableArrayList(LoginController.loginTech.getJobs()));
					completedJobsListView.setItems((ObservableList<String>) FXCollections.observableArrayList(LoginController.loginTech.getCJobs()));
				}
			}	
		}
	}
	
	
	
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
	}
}
