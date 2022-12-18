package sg.nus.iss.leavesystem.ca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.iss.leavesystem.ca.model.LeaveType;
import sg.nus.iss.leavesystem.ca.repository.LeaveTypeRepository;

@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {

    @Autowired
    LeaveTypeRepository leaveTypeRepository;

    @Override
    public List<LeaveType> GetAll() {
        return leaveTypeRepository.findAll();
    }
    
}