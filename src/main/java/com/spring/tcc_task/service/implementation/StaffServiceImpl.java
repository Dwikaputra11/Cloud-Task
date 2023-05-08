package com.spring.tcc_task.service.implementation;

import com.spring.tcc_task.models.Staff;
import com.spring.tcc_task.repos.StaffRepository;
import com.spring.tcc_task.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements StaffService {
    private final StaffRepository staffRepository;
    @Autowired
    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }
    @Override
    public Page<Staff> findAll(Pageable pageable) {
        return staffRepository.findAll(pageable);
    }

    @Override
    public Staff findById(int id) {
        var staff = staffRepository.findById(id);
        if(staff.isEmpty()) throw new RuntimeException("Data staff id: " + id + " is not exist.");
        return staff.get();
    }

    @Override
    public Staff save(Staff staff) {
        if (staff.getName() == null || staff.getName().isEmpty()
                || staff.getContact() == null || staff.getContact().isEmpty()
        )  throw new RuntimeException("Data staff is not valid");

        staff.setStaffId(0);

//        var date = Calendar.getInstance();
//        staff.setLastUpdate(date.getTime());

        return staffRepository.save(staff);
    }

    @Override
    public Staff update(Staff updatedStaff) {
        var result = staffRepository.findById(updatedStaff.getStaffId());

        if(result.isEmpty())
            throw new RuntimeException("Data staff id: " + updatedStaff.getStaffId() + " is not exist.");

        var staff = result.get();
        staff.setContact(updatedStaff.getContact());
        staff.setName(updatedStaff.getName());
        return staffRepository.save(staff);
    }

    @Override
    public void delete(int id) {
        var staff = staffRepository.findById(id);
        if(staff.isEmpty()) throw new RuntimeException("Data staff id: " + id + " is not exist.");
        staffRepository.delete(staff.get());
    }
}
