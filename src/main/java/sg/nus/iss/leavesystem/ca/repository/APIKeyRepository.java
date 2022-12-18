package sg.nus.iss.leavesystem.ca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.nus.iss.leavesystem.ca.model.APIKey;

public interface APIKeyRepository extends JpaRepository<APIKey, String>{

}
