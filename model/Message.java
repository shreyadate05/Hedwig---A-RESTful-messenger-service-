package org.sdate.services.Hedwig.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.sdate.services.Hedwig.model.Comment;

@XmlRootElement
public class Message {
	
	private long id;
	private String message;
	private String author;
	private Date created;
	private Map<Long, Comment> comments = new HashMap<>();
	
	public Message() {
		// ALWAYS HAVE A NO-OP CONSTRUCTOR
	}
	
	public Message(long id, String message, String author) {
		this.id = id;
		this.message = message;
		this.author = author;
		this.created = new Date();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}

	@XmlTransient    // we want comments to be ignored when message instance is converted to JSON/XML
	public Map<Long, Comment> getComments() {
		return this.comments;
	}

	public void setComments(Map<Long, Comment> comments) {
		this.comments = comments;
	}
}
