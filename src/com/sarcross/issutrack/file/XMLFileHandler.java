package com.sarcross.issutrack.file;

import com.sarcross.issutrack.IssuTrack;
import com.sarcross.issutrack.model.Issue;
import com.sarcross.issutrack.model.IssueListWrapper;
import com.sarcross.issutrack.util.MissingFieldException;

import java.io.File;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public abstract class XMLFileHandler{
	private static IssuTrack mainApp;
	private static ObservableList<Issue> issueData = FXCollections.observableArrayList();
	
	public static void loadIssueDataFromFile(File file) {
		try
		{
			JAXBContext context = JAXBContext.newInstance(IssueListWrapper.class, Issue.class);
			Unmarshaller um = context.createUnmarshaller();
			um.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
			
			IssueListWrapper wrapper = (IssueListWrapper) um.unmarshal(file);
			
			issueData.clear();
			
			for(int ndx = 0; ndx < wrapper.getIssues().size(); ndx ++) {
				try
				{
					issueData.add(new Issue(wrapper.getIssues().get(ndx)));
				}
				catch(MissingFieldException mfe)
				{
					Alert alert = new Alert(AlertType.ERROR);
					
					alert.setTitle("Error");
					alert.setHeaderText("Missing Field Exception");
					alert.setContentText("Issue (" + (ndx + 1) +") could not be loaded. Check the file for a missing field (Name, Creator, Description).\n" + file.getPath());
					
					alert.showAndWait();
					mfe.printStackTrace();
				}
				
			}
		}
		catch(Exception e)
		{
			Alert alert = new Alert(AlertType.ERROR);
			
			alert.setTitle("Error");
			alert.setHeaderText("Could not load data");
			alert.setContentText("Could not load data from file:\n" + file.getPath());
			
			alert.showAndWait();
			e.printStackTrace();
		}
	}
	
	public static void saveIssueDataToFile(File file) {
		try
		{
			JAXBContext context = JAXBContext.newInstance(IssueListWrapper.class, Issue.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			IssueListWrapper wrapper = new IssueListWrapper();
			
			for(int ndx = 0; ndx < issueData.size(); ndx++) {
				wrapper.addIssue(issueData.get(ndx));
			}
			
			m.marshal(wrapper, file);
		}
		catch(Exception e)
		{
			Alert alert= new Alert(AlertType.ERROR);
			
			alert.setTitle("Error");
			alert.setHeaderText("Could not save data");
			alert.setContentText("Could not save data to file:\n" + file.getPath());
			
			alert.showAndWait();
			e.printStackTrace();
		}
	}

	public static void setMainApp(IssuTrack issuTrack) {
		mainApp = issuTrack;
		issueData = mainApp.getIssueData();
		
	}
}
