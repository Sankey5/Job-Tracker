package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
	private ListView<String> currentJobsListView, availableJobsListView;
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
		
		String listString = LoginController.loginTech.getMyJobs().stream().map(Object::toString)
                .collect(Collectors.joining(""));
		
		ObservableList<String> test = FXCollections.observableArrayList("Jobs", "Job1", "Job2", "Job3");
		ObservableList<String> test2 = FXCollections.observableArrayList(LoginController.loginTech.getJobs());
		currentJobsListView.setItems(test2);
		availableJobsListView.setItems(test);
		detailsTextArea.setText(LoginController.loginTech.getMyJobs().get(0).toDescription());
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
	}
	
	
	
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
	}
}
