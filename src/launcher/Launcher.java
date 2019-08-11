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
package launcher;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.testDatabase;
import view.LoginController;
import view.MainController;
import view.ViewType;

public class Launcher extends Application{
	
	public static Stage stage ;
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		stage = primaryStage;
		MainController controller = MainController.getInstance();
		FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("/view/defaultView.fxml"));
		//loader.setController(controller);
		Parent root = loader.load();
		controller.setRootPane((BorderPane) root);
		MainController.switchView(ViewType.Login);
		Scene scene = new Scene(root);
		stage.setTitle("Welcome!");
		stage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		testDatabase.loadSampleData();
		launch(args);
	}

}
