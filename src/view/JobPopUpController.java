package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class JobPopUpController {

    @FXML
    private DatePicker deadlinePicker;

    @FXML
    private TextField equipmentSerialText;

    @FXML
    private TextField equipmentMakeText;

    @FXML
    private TextField jobNumberText;

    @FXML
    private TextField equipmentModelText;

    @FXML
    private TextField customerEmailText;

    @FXML
    private TextField customerNameText;

    @FXML
    private TextField customerPhoneNumberText;

    @FXML
    private TextField customerCompanyText;
    
    public JobPopUpController() {
    	
    }

    @FXML
    void handle(ActionEvent event) {
    	
    }

}
