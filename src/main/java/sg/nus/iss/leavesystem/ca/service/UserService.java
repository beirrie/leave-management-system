package sg.nus.iss.leavesystem.ca.service;

import java.util.List;

import sg.nus.iss.leavesystem.ca.model.User;

public interface UserService {

	List<User> findAllUsers();

	User createUser(User user);
	
    User authenticate(String userName,String password);
}