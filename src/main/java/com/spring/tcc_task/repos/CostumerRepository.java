package com.spring.tcc_task.repos;

import com.spring.tcc_task.models.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostumerRepository extends JpaRepository<Costumer, Integer> {
}
