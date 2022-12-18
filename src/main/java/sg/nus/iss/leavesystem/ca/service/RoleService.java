package sg.nus.iss.leavesystem.ca.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sg.nus.iss.leavesystem.ca.model.Role;

@Service
public interface RoleService {
	List<Role> findAllRoles();

	Role findRoleByID(Long roleId);

	Role findRoleByID(String roleId);
}
