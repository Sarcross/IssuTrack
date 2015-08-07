package com.sarcross.issutrack.view;

import java.time.LocalDate;

import com.sarcross.issutrack.model.Issue;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class IssueEditDialogController {
	@FXML
	private TextField nameField;
	@FXML
	private TextField creatorField;
	@FXML
	private TextField assignedToField;
	@FXML
	private TextArea descriptionArea;
	@FXML
	private CheckBox finishedCheckBox;
	
	private Stage dialogStage;
	private Issue issue;
	private boolean okClicked = false;
	
	@FXML
	private void initialize() {
		
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setIssue(Issue iss) {
		issue = iss;
		
		nameField.setText(iss.getName().get());
		creatorField.setText(iss.getCreator().get());
		assignedToField.setText(iss.getAssignedTo().get());
		descriptionArea.setText(iss.getDescription().get());
		finishedCheckBox.setSelected(iss.isFinished());
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
	
	@FXML
	private void handleOk() {
		if(isInputValid()) {
			issue.setName(nameField.getText());
			if(issue.getCreated() == null)
				issue.setCreated(new SimpleObjectProperty<LocalDate>(LocalDate.now()));
			issue.setCreator(creatorField.getText());
			issue.setAssignedTo(assignedToField.getText());
			issue.setDescription(descriptionArea.getText());
			if(finishedCheckBox.isSelected())
				issue.setFinished();
			
			okClicked = true;
			dialogStage.close();
		}
	}
	
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
	
	private boolean isInputValid() {
		String errorMessage = "";
		
		if(nameField.getText() == null || nameField.getText().length() == 0)
			errorMessage += "Not a valid issue name!\n";
		if(creatorField.getText() == null || creatorField.getText().length() == 0)
			errorMessage += "Not a valid creator!\n";
		if(descriptionArea.getText() == null || descriptionArea.getText().length() == 0)
			errorMessage += "Not a valid description!\n";
		
		if(errorMessage.length() == 0)
			return true;
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);
			
			alert.showAndWait();
			return false;
		}
	}
}
