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

public class AdminController implements EventHandler<ActionEvent>, Initializable {
	@FXML
	private ListView statisticsListView, memoListView, jobsListView;
	@FXML
	private MenuButton actionMenuButton;
	@FXML
	private TextField memoTextField;
	
	
	
	
	
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
