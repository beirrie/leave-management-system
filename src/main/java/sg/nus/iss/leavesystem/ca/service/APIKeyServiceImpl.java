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
        return APIKeyRepo.findById(authKey).isPresent();
	}

}
