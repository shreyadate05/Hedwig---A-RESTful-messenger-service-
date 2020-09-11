package org.sdate.services.Hedwig.resources;

import org.sdate.services.Hedwig.model.Message;
import org.sdate.services.Hedwig.model.Profile;
import org.sdate.services.Hedwig.service.MessageService;
import org.sdate.services.Hedwig.service.ProfileService;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/profiles") // URL maps to the class
@Consumes(MediaType.APPLICATION_JSON)            // response body is JSON
@Produces(MediaType.APPLICATION_JSON)            // response body is JSON
public class ProfileResource {

	private ProfileService profileService = new ProfileService();
	
	@GET	   										// HTTP method maps to the java method
	public List<Profile> getProfiles() {
		return profileService.getAllProfiles();
	}
	
	@POST	   										 // HTTP method maps to the java method
	public Profile addProfile(Profile profile) {
		return profileService.addProfile(profile);
	}
	
	@GET
	@Path("/{profileName}")
	public Profile getProfile(@PathParam("profileName") String profileName) {
		return profileService.getProfile(profileName);
	}

	@PUT	   										 // HTTP method maps to the java method
	@Path("/{profileName}")
	public Profile updateProfile(@PathParam("profileName") String profileName, Profile profile) {
		profile.setProfileName(profileName);
		return profileService.updateProfile(profile);
	}

	@DELETE	   										 // HTTP method maps to the java method
	@Path("/{profileName}")
	public void deleteProfile(@PathParam("profileName") String profileName) {
		profileService.removeProfile(profileName);
	}
}
