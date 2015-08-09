package com.sarcross.issutrack.view;

import com.sarcross.issutrack.IssuTrack;
import com.sarcross.issutrack.file.XMLFileHandler;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;


public class RootLayoutController {
	private IssuTrack mainApp;
	
	public void setMainApp(IssuTrack issuTrack) {
		mainApp = issuTrack;
	}
	
	@FXML
	private void handleNew() {
		mainApp.getIssueData().clear();
	}
	
	@FXML
	private void handleOpen() {
		FileChooser fileChooser = new FileChooser();
		
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML Files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);
		
		File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
		
		if(file != null) {
			XMLFileHandler.setMainApp(mainApp);
			XMLFileHandler.loadIssueDataFromFile(file);
		}	
	}
	
	@FXML
	private void handleSave() {
		handleSaveAs();
	}
	
	@FXML
	private void handleSaveAs() {
		FileChooser fileChooser = new FileChooser();
		
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML Files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            XMLFileHandler.setMainApp(mainApp);
            XMLFileHandler.saveIssueDataToFile(file);
        }
	}
	
	@FXML
	private void handleAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		
		alert.setTitle("IssuTrack");
		alert.setHeaderText("About");
		alert.setContentText("Author: Anthony Haddox\n"
							+"Based on AddressApp created by Marco Jakob: http://code.makery.ch\n"
							+"AddressApp Tutorial: http://code.makery.ch/library/javafx-8-tutorial/\n"
							+"Fork me on GitHub: https://github.com/Sarcross/IssuTrack");
		
		alert.showAndWait();
	}
	
	@FXML
	private void handleExit() {
		System.exit(0);
	}
}
