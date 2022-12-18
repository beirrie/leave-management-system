package sg.nus.iss.leavesystem.ca.service;

import org.springframework.stereotype.Service;

public interface APIKeyService {
	
	Boolean getAPIKeyByID(String authkey);
}
