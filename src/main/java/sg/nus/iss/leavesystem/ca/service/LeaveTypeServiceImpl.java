package sg.nus.iss.leavesystem.ca.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.iss.leavesystem.ca.model.LeaveType;
import sg.nus.iss.leavesystem.ca.repository.LeaveTypeRepository;

@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {

    @Autowired
    LeaveTypeRepository leaveTypeRepository;

    @Override
    public List<LeaveType> GetAllWithoutCompensation() {
        return leaveTypeRepository.findAll().stream().filter(x->!x.getLeaveTypeName().equalsIgnoreCase("compensation"))
        .collect(Collectors.toList());
    }

    @Override
    public List<LeaveType> GetAll(){

        return leaveTypeRepository.findAll();

    }

    @Override
    public LeaveType findById(long id) {
        return leaveTypeRepository.findById(id).get();
    }
    
}