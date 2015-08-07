package com.sarcross.issutrack.model;

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
		name = new SimpleStringProperty(iss.name.get());
		description = new SimpleStringProperty(iss.description.get());
		creator = new SimpleStringProperty(iss.creator.get());
		assignedTo = new SimpleStringProperty(iss.assignedTo.get());
		created = new SimpleObjectProperty<LocalDate>(LocalDate.of(iss.created.get().getYear(),  iss.created.get().getMonth(),  iss.created.get().getDayOfMonth()));
		if(iss.finished == null)
			finished = null;
		else
			finished = new SimpleObjectProperty<LocalDate>(LocalDate.of(iss.finished.get().getYear(), iss.finished.get().getMonth(), iss.finished.get().getDayOfMonth()));
	}
	
	public Issue(String n, String desc, String cr) {
		name = new SimpleStringProperty(n);
		description = new SimpleStringProperty(desc);
		creator = new SimpleStringProperty(cr);
		assignedTo = null;
		created = new SimpleObjectProperty<LocalDate>(LocalDate.now());
		finished = null;
	}
	
	public Issue(String n, String desc, String cr, String assign) {
		name = new SimpleStringProperty(n);
		description = new SimpleStringProperty(desc);
		creator = new SimpleStringProperty(cr);
		assignedTo = new SimpleStringProperty(assign);
		created = new SimpleObjectProperty<LocalDate>(LocalDate.now());
		finished = null;
	}
	
	public void clear() {
		name = new SimpleStringProperty();
		description = new SimpleStringProperty();
		creator = new SimpleStringProperty();
		assignedTo = new SimpleStringProperty();
		created = new SimpleObjectProperty<LocalDate>(LocalDate.now());
		finished = null;
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
		return (finished != null);
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

	public ObjectProperty<LocalDate> getCreated() {
		return created;
	}

	public void setCreated(ObjectProperty<LocalDate> created) {
		this.created = new SimpleObjectProperty<LocalDate>(LocalDate.ofEpochDay(created.get().toEpochDay()));
	}

	public ObjectProperty<LocalDate> getFinished() {
		return finished;
	}

	public void setFinished() {
		this.finished = new SimpleObjectProperty<LocalDate>(LocalDate.now());
	}
	
	public void setFinished(ObjectProperty<LocalDate> finished) {
		this.finished = new SimpleObjectProperty<LocalDate>(LocalDate.ofEpochDay(finished.get().toEpochDay()));
	}
	
	
}
