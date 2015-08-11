package com.sarcross.issutrack.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sarcross.issutrack.util.IssueAdapter;
import com.sarcross.issutrack.util.MissingFieldException;

@XmlRootElement(name = "issues")
public class IssueListWrapper {
	private List<IssueAdapter> issues = new ArrayList<IssueAdapter>();
	
	@XmlElement(name = "issue")
	public List<IssueAdapter> getIssues() {
		return issues;
	}
	
	public void setIssues(List<IssueAdapter> issues) {
		this.issues = issues;
	}
	
	public void addIssue(Issue iss) {
		try
		{
			issues.add(new IssueAdapter(iss));
		}
		catch(MissingFieldException mfe)
		{
			System.err.println("A field was missing. Could not add the Issue" + iss);
		}
	}
}
