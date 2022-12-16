package sg.nus.iss.leavesystem.ca.service_implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sg.nus.iss.leavesystem.ca.model.User;
import sg.nus.iss.leavesystem.ca.repository.UserRepository;
import sg.nus.iss.leavesystem.ca.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Transactional
	@Override
	public User createUser(User user) {
		return userRepository.saveAndFlush(user);
	}

	@Transactional
	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}
}