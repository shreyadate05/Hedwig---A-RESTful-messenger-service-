package org.sdate.services.Hedwig.resources;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sdate.services.Hedwig.model.Comment;
import org.sdate.services.Hedwig.service.CommentService;

//@Path("/comments")
@Consumes(MediaType.APPLICATION_JSON)            // request  body is JSON
@Produces(MediaType.APPLICATION_JSON)            // response body is JSON
public class CommentResource {
	
	private CommentService commentService = new CommentService();
	
	@GET
	public List<Comment> getAllComments(@PathParam("messageId") long messageId) {
		return commentService.getAllComments(messageId);
	}
	
	@GET
	@Path("/{commentId}")
	public Comment getComment(@PathParam("commentId") long commentId,
							 @PathParam("messageId") long messageId) {
		return commentService.getComment(messageId, commentId);
	}
	
	@POST	   										 // HTTP method maps to the java method
	public Comment addComment(@PathParam("messageId") long messageId,
							  Comment comment) {
		return commentService.addComment(messageId, comment);
	}
	
	@PUT	   										 // HTTP method maps to the java method
	@Path("/{commentId}")
	public Comment updateComment(@PathParam("commentId") long commentId, 
								 @PathParam("messageId") long messageId,
								 Comment comment) {
		comment.setId(commentId);
		return commentService.updateComment(messageId, comment);
	}

	@DELETE	   										 // HTTP method maps to the java method
	@Path("/{commentId}")
	public void deleteComment(@PathParam("commentId") long commentId,
			                  @PathParam("messageId") long messageId) {
		commentService.removeComment(messageId, commentId);
	}
}
