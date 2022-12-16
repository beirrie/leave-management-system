package sg.nus.iss.leavesystem.ca.service;

import java.util.List;

import sg.nus.iss.leavesystem.ca.model.LeaveScheme;

public interface LeaveSchemeService {
	List<LeaveScheme> findAllLeaveSchemes();

	LeaveScheme findLeaveScheme(Long id);
}
