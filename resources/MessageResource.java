package org.sdate.services.Hedwig.resources;

import org.sdate.services.Hedwig.resources.beans.MessageFilterBean;
import org.sdate.services.Hedwig.exception.DataNotFoundException;
import org.sdate.services.Hedwig.model.Message;
import org.sdate.services.Hedwig.service.MessageService;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlTransient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@Path("/messages") // URL maps to the class
@Consumes(MediaType.APPLICATION_JSON)            // response body is JSON
@Produces(MediaType.APPLICATION_JSON)            // response body is JSON

public class MessageResource {

	private MessageService messageService = new MessageService();
//	private CommentResource commentResource = new CommentResource();
	
	@GET	   									// HTTP method maps to the java method
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {
		if (filterBean.getYear() > 0) {
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		if (filterBean.getStart() >= 0 && filterBean.getSize() > 0) {
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
	}
	
	@POST	   										 // HTTP method maps to the java method
	public Response addMessages(Message message, @Context UriInfo uriInfo) {
		
		Message newMessage = messageService.addMessage(message);
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri)
						.entity(newMessage)
						.build();
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long messageId) {
		Message message =  messageService.getMessage(messageId);
		if (message == null) {
			throw new DataNotFoundException("Message with id " + messageId + " not found");
		}
		return message;
	}

	@PUT	   										 // HTTP method maps to the java method
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		return messageService.updateMessage(message);
	}

	@DELETE	   										 // HTTP method maps to the java method
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long id) {
		messageService.removeMessage(id);
	}
	
	// no HTTP method annotation 'cause it could be POST, GET, DELETE, UPDTE...anything
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
}
