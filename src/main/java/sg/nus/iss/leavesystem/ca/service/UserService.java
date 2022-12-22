package sg.nus.iss.leavesystem.ca.service;

import java.util.List;

import sg.nus.iss.leavesystem.ca.model.User;

public interface UserService {
	User findById(long id);
	List<User> findAllUsers();
	User findUser(Long userId);
	User createUser(User user);	
  	User authenticate(String userName,String password);
	Boolean deactivateUser(User user);
	Boolean activateUser(User user);
	User findUserByStaffID(Long staffId);
	User findUserByStaffID(String staffId);
}