package com.spring.tcc_task.repos;

import com.spring.tcc_task.models.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {
}
