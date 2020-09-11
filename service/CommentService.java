package org.sdate.services.Hedwig.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.sdate.services.Hedwig.database.DatabaseClass;
import org.sdate.services.Hedwig.model.Comment;
import org.sdate.services.Hedwig.model.ErrorMessage;
import org.sdate.services.Hedwig.model.Message;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;

public class CommentService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public ArrayList<Comment> getAllComments(Long messageId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return new ArrayList(comments.values());
	}

	public Comment getComment(Long messageId, Long commentId) {
		ErrorMessage error = new ErrorMessage("Not found ", 404, "http://localhost:8080/Hedwig/");
		Response response = Response.status(Status.NOT_FOUND)
				.type(MediaType.APPLICATION_JSON)
				.entity(error)
				.build();
		
		Message message = messages.get(messageId);
		if (message == null) {
			throw new NotFoundException(response);
		}
		
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		Comment comment = comments.get(commentId);
		if (comment == null) {
			throw new WebApplicationException(response);
		}
		
		return comment;
	}
	
	public Comment addComment(Long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		comment.setId(comments.size()+1);
		comments.put(comment.getId(), comment);
		messages.get(messageId).setComments(comments);
		return comment;
	}
	
	public Comment updateComment(Long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if (comment.getId() <= 0) {
			return null;
		}
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment removeComment(Long messageId, Long commentId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return comments.remove(commentId);
	}
}
