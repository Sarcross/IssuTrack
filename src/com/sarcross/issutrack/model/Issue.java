package com.sarcross.issutrack.model;

import com.sarcross.issutrack.util.IssueAdapter;
import com.sarcross.issutrack.util.MissingFieldException;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Issue {
	private StringProperty name;
	private StringProperty description;
	private StringProperty creator;
	private StringProperty assignedTo;
	private ObjectProperty<LocalDate> created;
	private ObjectProperty<LocalDate> finished;
	
	public Issue() {
		clear();
	}
	
	public Issue(Issue iss) {
		this();
		name = new SimpleStringProperty(iss.name.get());
		description = new SimpleStringProperty(iss.description.get());
		creator = new SimpleStringProperty(iss.creator.get());
		assignedTo = new SimpleStringProperty(iss.assignedTo.get());
		created = new SimpleObjectProperty<LocalDate>(LocalDate.ofEpochDay(iss.getCreated().toEpochDay()));
		if(iss.finished == null)
			finished = new SimpleObjectProperty<LocalDate>(LocalDate.MIN);
		else
			finished = new SimpleObjectProperty<LocalDate>(LocalDate.ofEpochDay(iss.getFinished().toEpochDay()));
	}
	
	public Issue(IssueAdapter iss) throws MissingFieldException {
		this();
		verify(iss);
		name = new SimpleStringProperty(iss.getName());
		description = new SimpleStringProperty(iss.getDescription());
		creator = new SimpleStringProperty(iss.getCreator());
		assignedTo = new SimpleStringProperty(iss.getAssignedTo());
		created = new SimpleObjectProperty<LocalDate>(LocalDate.ofEpochDay(iss.getCreated().toEpochDay()));
		finished = new SimpleObjectProperty<LocalDate>(LocalDate.ofEpochDay(iss.getFinished().toEpochDay()));
	}
	
	public void clear() {
		name = new SimpleStringProperty();
		description = new SimpleStringProperty();
		creator = new SimpleStringProperty();
		assignedTo = new SimpleStringProperty();
		created = new SimpleObjectProperty<LocalDate>(LocalDate.now());
		finished = new SimpleObjectProperty<LocalDate>(LocalDate.MIN);
	}
	
	private void verify(IssueAdapter iss) throws MissingFieldException {
		if(iss.getName().equals(""))
			throw new MissingFieldException();
		if(iss.getDescription().equals(""))
			throw new MissingFieldException();
		if(iss.getCreator().equals(""))
			throw new MissingFieldException();
	}
	
	public boolean equals(Issue iss) {
		if(!name.get().toUpperCase().equals(iss.name.get().toUpperCase()))
			return false;
		if(!description.get().toUpperCase().equals(iss.description.get().toUpperCase()))
			return false;
		if(!creator.get().toUpperCase().equals(iss.creator.get().toUpperCase()))
			return false;
		if(created.get().toEpochDay() != iss.created.get().toEpochDay())
			return false;
		else
			return true;
	}
	
	public boolean isFinished() {
		return !(finished.get().equals((LocalDate.MIN)));
	}

	public StringProperty getName() {
		return name;
	}

	public void setName(String  n) {
		this.name =  new SimpleStringProperty(n);
	}

	public StringProperty getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = new SimpleStringProperty(description);
	}

	public StringProperty getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = new SimpleStringProperty(creator);
	}

	public StringProperty getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = new SimpleStringProperty(assignedTo);
	}

	public LocalDate getCreated() {
		return created.get();
	}
	
	public ObjectProperty<LocalDate> createdProperty() {
		return created;
	}

	public void setCreated(ObjectProperty<LocalDate> created) {
		this.created = new SimpleObjectProperty<LocalDate>(LocalDate.ofEpochDay(created.get().toEpochDay()));
	}

	public LocalDate getFinished() {
		return finished.get();
	}

	public void setFinished() {
		this.finished = new SimpleObjectProperty<LocalDate>(LocalDate.now());
	}
	
	public void setFinished(ObjectProperty<LocalDate> finished) {
		this.finished = new SimpleObjectProperty<LocalDate>(LocalDate.ofEpochDay(finished.get().toEpochDay()));
	}
}
