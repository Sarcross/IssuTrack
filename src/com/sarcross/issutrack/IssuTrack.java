package com.sarcross.issutrack;

import com.sarcross.issutrack.file.XMLFileHandler;
import com.sarcross.issutrack.model.Issue;
import com.sarcross.issutrack.view.IssueEditDialogController;
import com.sarcross.issutrack.view.IssueOverviewController;
import com.sarcross.issutrack.view.RootLayoutController;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class IssuTrack extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;
	private ObservableList<Issue> issueData = FXCollections.observableArrayList();
	
	public ObservableList<Issue> getIssueData() {
		return issueData;
	}
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("IssuTrack");
		
		initRootLayout();
		showIssueOverview();
	}
	
	public void initRootLayout() {
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(IssuTrack.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			
			
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);
			
			XMLFileHandler.setMainApp(this);
			
			primaryStage.show();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void showIssueOverview() {
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(IssuTrack.class.getResource("view/IssueOverview.fxml"));
			AnchorPane issueOverview = (AnchorPane) loader.load();
			
			rootLayout.setCenter(issueOverview);
			
			IssueOverviewController controller = loader.getController();
			controller.setMainApp(this);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean showIssueEditDialog(Issue issue) {
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(IssuTrack.class.getResource("view/IssueEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Issue");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			IssueEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setIssue(issue);
			
			dialogStage.showAndWait();
			
			return controller.isOkClicked();
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
