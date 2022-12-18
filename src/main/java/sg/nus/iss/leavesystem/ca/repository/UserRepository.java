package sg.nus.iss.leavesystem.ca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import sg.nus.iss.leavesystem.ca.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
  @Query("SELECT u FROM User u WHERE u.userName = ?1 AND u.password = ?2")
  User FindByUserNameAndPassword(String userName, String password);

  @Query("SELECT u FROM User u WHERE u.employee.id = :sid")
	User findUserByStaffID(@Param("sid") Long id);
}
