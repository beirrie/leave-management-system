package sg.nus.iss.leavesystem.ca.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sg.nus.iss.leavesystem.ca.model.User;

@Service
public interface UserService {
	List<User> findAllUsers();
	User findUser(Long userId);
	User createUser(User user);
	Boolean deactivateUser(User user);

	User findUserByStaffID(Long staffId);
	User findUserByStaffID(String staffId);

}