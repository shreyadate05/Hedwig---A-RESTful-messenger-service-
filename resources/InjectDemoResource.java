package org.sdate.services.Hedwig.resources;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import java.util.List;

@Path("/injectdemo")                       // URL maps to the class
@Consumes(MediaType.TEXT_PLAIN)            // response body is JSON
@Produces(MediaType.TEXT_PLAIN)            // response body is JSON

public class InjectDemoResource {

	@GET
	@Path("/annotations")
	public String getInjectDemoUsingAnnotations(@MatrixParam("param") String matrixParam,
										   @HeaderParam("customHeaderValue") String header,
										   @CookieParam("cookiename") String cookie) {
		return "Matrix param: " + matrixParam + "\nHeader param: " + header + "\nCookie param: " + cookie;
	}
	
	// header param used to send metadata about the request like authentication token
	// create own auth session id as param and some token value
	// get hold of token value and access it 	
	// cookie params
	
	@GET
	@Path("/context")
	public String getInjectDemoUsingContext(@Context UriInfo uriInfo,
											@Context HttpHeaders headers) {
		return "Path is: " + uriInfo.getAbsolutePath() + "\nCookies are: " + headers.getCookies().toString();
	}
}
