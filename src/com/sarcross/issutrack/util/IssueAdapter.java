package com.sarcross.issutrack.util;

import com.sarcross.issutrack.model.Issue;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class IssueAdapter {
	private String name;
	private String description;
	private String creator;
	private String assignedTo;
	private LocalDate created;
	private LocalDate finished;
	
	public IssueAdapter() {
		name = "";
		description = "";
		creator = "";
		assignedTo = "";
		created = LocalDate.now();
		finished = LocalDate.MIN;
	}
	
	public IssueAdapter(Issue iss) throws MissingFieldException {
		name  = iss.getName().get();
		description = iss.getDescription().get();
		creator = iss.getCreator().get();
		assignedTo = iss.getAssignedTo().get();
		created = LocalDate.ofEpochDay(iss.getCreated().toEpochDay());
		finished = LocalDate.ofEpochDay(iss.getFinished().toEpochDay());
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreator() {
		return creator;
	}
	
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	public String getAssignedTo() {
		return assignedTo;
	}
	
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	public LocalDate getCreated() {
		return created;
	}
	
	public void setCreated(LocalDate created) {
		this.created = created;
	}
	
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	public LocalDate getFinished() {
		return finished;
	}
	
	public void setFinished(LocalDate finished) {
		this.finished = finished;
	}
}
