package com.sarcross.issutrack.util;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class StringPropertyAdapter extends XmlAdapter<String, StringProperty>{
	@Override
	public SimpleStringProperty unmarshal(String v) throws Exception {
		return new SimpleStringProperty(v);
	}
	
	@Override
	public String marshal(StringProperty v) throws Exception {
		System.out.println(v.get());
		return v.get().toString();
	}
}
