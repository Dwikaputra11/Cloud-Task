package com.spring.tcc_task.repos;

import com.spring.tcc_task.models.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface StudioRepository extends JpaRepository<Studio, Integer> {
}
