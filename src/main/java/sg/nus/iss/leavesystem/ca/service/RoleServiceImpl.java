package sg.nus.iss.leavesystem.ca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.iss.leavesystem.ca.model.Role;
import sg.nus.iss.leavesystem.ca.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleRepository roleRepository;

	public List<Role> findAllRoles(){
		return roleRepository.findAll();
	}
}
