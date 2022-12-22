package sg.nus.iss.leavesystem.ca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sg.nus.iss.leavesystem.ca.model.User;
import sg.nus.iss.leavesystem.ca.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Transactional
	@Override
	public User findById(long id) {
		return userRepository.findById(id).orElse(null);
	}

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

	@Override
	public User authenticate(String userName, String password) {
		return userRepository.FindByUserNameAndPassword(userName, password);
	}

	@Transactional
	@Override
	public User findUser(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}

	@Transactional
	@Override
	public User findUserByStaffID(Long staffId) {
		return userRepository.findUserByStaffID(staffId);
	}

	@Transactional
	@Override
	public User findUserByStaffID(String staffId) {
		return userRepository.findUserByStaffID(Long.parseLong(staffId));
	}

	@Transactional
	@Override
	public Boolean deactivateUser(User user) {
		user.setIsActive(false);
		return user.getIsActive();
	}

	@Transactional
	@Override
	public Boolean activateUser(User user) {
		user.setIsActive(true);
		return user.getIsActive();
	}
}