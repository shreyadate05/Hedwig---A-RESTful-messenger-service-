package org.sdate.services.Hedwig.service;


import org.sdate.services.Hedwig.model.Profile;
import org.sdate.services.Hedwig.database.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProfileService {
	private Map<String, Profile> profiles = DatabaseClass.getProfiles();
	
	public ProfileService() {
		profiles.put("sdate", new Profile(1, "sdate", "Shreya", "Date"));
		profiles.put("shreyaaaaadate", new Profile(2, "shreyaaaaadate", "Shreyaaaaa", "Daaaaate"));
	}
	
	public List<Profile> getAllProfiles() {
		return new ArrayList(profiles.values());
	}
	
	public Profile getProfile(String profileName) {
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size()+1);
		profiles.put(profile.getProfileName(), profile);
		return profiles.get(profile.getProfileName());
	}
	
	public Profile updateProfile(Profile profile) {
		if (profile.getProfileName().isEmpty()) {
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profiles.get(profile.getProfileName());
	}
	
	public Profile removeProfile(String profileName) {
		return profiles.remove(profileName);
	}
}

