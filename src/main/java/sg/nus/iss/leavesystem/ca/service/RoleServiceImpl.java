package sg.nus.iss.leavesystem.ca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sg.nus.iss.leavesystem.ca.model.Role;
import sg.nus.iss.leavesystem.ca.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleRepository roleRepository;

	public List<Role> findAllRoles() {
		return roleRepository.findAll();
	}

	@Transactional
	@Override
	public Role findRoleByID(Long roleId) {
		return roleRepository.findById(roleId).orElse(null);
	}

	@Transactional
	@Override
	public Role findRoleByID(String roleId) {
		Long id = Long.parseLong(roleId);
		return roleRepository.findById(id).orElse(null);
	}
}
