/*******************************************************************************
 * Copyright (c) 2019 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import launcher.Launcher;
import model.Customer;
import model.Equipment;
import model.Job;
import model.Technician;


public class LoginController implements Initializable {
	
	public LoginController() throws IOException {
	}
	
	@FXML
    private Button techButton;

    @FXML
    private Button adminButton;
    
    @FXML
    void switchAdminView(ActionEvent event) {
    	MainController.switchView(ViewType.Admin);
    }

    @FXML
    void techSelect(ActionEvent event) {
    	MainController.switchView(ViewType.TechSelect);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Screen screen = Screen.getPrimary();
		Launcher.stage.setX(0);
		Launcher.stage.setY(0);
		Launcher.stage.setHeight(screen.getBounds().getHeight());
		Launcher.stage.setWidth(screen.getBounds().getWidth());
	}
	

}
