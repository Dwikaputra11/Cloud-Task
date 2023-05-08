package com.spring.tcc_task.repos;

import com.spring.tcc_task.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
}
