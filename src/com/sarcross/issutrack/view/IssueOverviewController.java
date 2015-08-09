package com.sarcross.issutrack.view;

import com.sarcross.issutrack.IssuTrack;
import com.sarcross.issutrack.model.Issue;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class IssueOverviewController {
	@FXML
	private TableView<Issue> issueTable;
	@FXML
	private TableColumn<Issue, String> issueNameColumn;
	@FXML
	private TableColumn<Issue, LocalDate> creationDateColumn;
	
	@FXML
	private Label issueNameLabel;
	@FXML
	private Label creationDateLabel;
	@FXML
	private Label finishDateLabel;
	@FXML
	private Label descriptionLabel;
	@FXML
	private Label creatorLabel;
	@FXML
	private Label assignedToLabel;
	
	private IssuTrack issuTrack;
	
	public IssueOverviewController() {
		
	}
	
	@FXML
	private void initialize() {
		issueNameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
		creationDateColumn.setCellValueFactory(cellData -> cellData.getValue().createdProperty());
		
		showIssueDetails(null);
		
		issueTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showIssueDetails(newValue));
	}
	
	public void setMainApp(IssuTrack issuTrack) {
		this.issuTrack = issuTrack;
		issueTable.setItems(issuTrack.getIssueData());
	}
	
	private void showIssueDetails(Issue issue) {
		if(issue != null) {
			issueNameLabel.setText(issue.getName().get());
			descriptionLabel.setText(issue.getDescription().get());
			creationDateLabel.setText(issue.getCreated().toString());
			creatorLabel.setText(issue.getCreator().get().toString());
			if(issue.getAssignedTo().get() != null) {
				assignedToLabel.setText(issue.getAssignedTo().get().toString());
			}
			else {
				assignedToLabel.setText("");
			}
			if(issue.isFinished())
				finishDateLabel.setText(issue.getFinished().toString());
			else
				finishDateLabel.setText("");
		}
		else {
			issueNameLabel.setText("");
			descriptionLabel.setText("");
			creationDateLabel.setText("");
			finishDateLabel.setText("");
			creatorLabel.setText("");
			assignedToLabel.setText("");
		}
	}
	
	@FXML
	private void handleDeleteIssue() {
		int selectedIndex = issueTable.getSelectionModel().getSelectedIndex();
		try
		{
			issueTable.getItems().remove(selectedIndex);
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(issuTrack.getPrimaryStage());
			if(issueTable.getItems().isEmpty()) {
				alert.setTitle("No Issues");
				alert.setHeaderText("No Issue Selected");
				alert.setContentText("There are no issues!");
			}
			else {
				alert.setTitle("No Selection");
				alert.setHeaderText("No Issue Selected");
				alert.setContentText("Please select an issue in the table.");
			}
			alert.showAndWait();
		}
	}

	@FXML
	private void handleNewIssue() {
		Issue tempIssue = new Issue();
		boolean okClicked = issuTrack.showIssueEditDialog(tempIssue);
		
		if(okClicked)
			issuTrack.getIssueData().add(tempIssue);
	}
	
	@FXML
	private void handleEditIssue() {
		Issue selectedIssue = issueTable.getSelectionModel().getSelectedItem();
		if(selectedIssue != null) {
			boolean okClicked = issuTrack.showIssueEditDialog(selectedIssue);
			if(okClicked)
				showIssueDetails(selectedIssue);
		}
		else {
			Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(issuTrack.getPrimaryStage());
	        alert.setTitle("No Selection");
	        alert.setHeaderText("No Issue Selected");
	        alert.setContentText("Please select an issue in the table.");

	        alert.showAndWait();
		}
	}
}
