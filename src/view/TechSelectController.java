package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import model.Database;
import model.Technician;

public class TechSelectController implements Initializable{
	@FXML
    private BorderPane borderPane;
	
	@FXML
	private ListView<Technician> techListView;
	
	
	public TechSelectController() {}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Database database = Database.getInstance();
		
		Technician tempTech = new Technician();
		tempTech.setName("Jason");
		
		database.getTechnicians().add(tempTech);
		
		ObservableList<Technician> something = FXCollections.observableArrayList(database.getTechnicians());
		
		techListView.setItems(something);
	}
	
	@FXML
    void selectTechnician(MouseEvent event) {
		if(event.getClickCount() < 2) {
			return;
		}
		
		
    }
	
	@FXML
    void goBack(ActionEvent event) {
		MainController.switchView(ViewType.Login);
    }
}
