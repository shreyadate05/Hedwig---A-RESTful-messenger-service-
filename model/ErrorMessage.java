package org.sdate.services.Hedwig.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {

	private String errorMessag;
	private int errorCode;
	private String documentation;
	
	
	public ErrorMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ErrorMessage(String errorMessag, int errorCode, String documentation) {
		super();
		this.errorMessag = errorMessag;
		this.errorCode = errorCode;
		this.documentation = documentation;
	}
	
	public String getErrorMessag() {
		return errorMessag;
	}
	
	public void setErrorMessag(String errorMessag) {
		this.errorMessag = errorMessag;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getDocumentation() {
		return documentation;
	}
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
	
	
}
