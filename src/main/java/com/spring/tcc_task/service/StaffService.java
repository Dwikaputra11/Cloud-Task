package com.spring.tcc_task.service;

import com.spring.tcc_task.models.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StaffService {
    Page<Staff> findAll(Pageable pageable);

    Staff findById(int id);

    Staff save(Staff staff);

    Staff update(Staff updatedStaff);

    void delete(int id);
}
