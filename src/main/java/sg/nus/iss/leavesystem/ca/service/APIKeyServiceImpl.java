package sg.nus.iss.leavesystem.ca.service;

import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import sg.nus.iss.leavesystem.ca.repository.APIKeyRepository;

@Service
public class APIKeyServiceImpl implements APIKeyService {
	
	@Resource
	APIKeyRepository APIKeyRepo;
	
	@Override
	public Boolean getAPIKeyByID(String authKey) {
		if(APIKeyRepo.findById(authKey).isEmpty()) {
			return false;
		}
		else {
			return true;
		}
	}

}
