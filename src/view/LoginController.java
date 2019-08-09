package view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.Customer;
import model.Equipment;
import model.Job;
import model.Technician;


public class LoginController implements Initializable {
	

	public LoginController() throws IOException {
	}
	
	@FXML
	private TextField usernameTextField, createTextField, createTextField2;
	public static Technician loginTech = null;
	
	@FXML
    void enterAction(ActionEvent event) throws IOException {
		
		System.out.print(usernameTextField.getText());
		if (usernameTextField.getText().equals("Admin")) {
			MainController.switchView(ViewType.Admin);
		}
		
		if (usernameTextField.getText().equals("Tech")) {
			MainController.switchView(ViewType.Technician);
		}
		
		if(usernameTextField.getText().equals(loginTech.getName())) {
			MainController.switchView(ViewType.Technician);
		}
    }

	@FXML
	void createAction(ActionEvent event) {
		String amountJobs = createTextField.getText();
		int j = Integer.parseInt(amountJobs);
		Technician test = new Technician();
		test.setName("John");
		test.setPhoneNumber(123456789);
		test.setStats("Doing Pretty Well");
		test.setStatus("New Hire");
		test.setNotice("Generated from login");
		for(int i = 0; i < j; i++) {
			Customer guy = new Customer("Place"+i, "Person"+i, 123456789, "thisguy@email"+i);
			Equipment tool = new Equipment("Make"+i, "Model"+i, "Serial"+i);
			Job work = new Job(guy, tool, LocalDate.now());
			test.addEquipment(tool);
			test.giveJob(work);
		}
		System.out.println(test);
		
		
		
		loginTech = test;
			
		}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
