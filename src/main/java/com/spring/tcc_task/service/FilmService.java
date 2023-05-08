package com.spring.tcc_task.service;

import com.spring.tcc_task.models.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FilmService {

    Page<Film> findAll(Pageable pageable);

    Film findById(int id);

    Film save(Film film);

    Film update(Film updatedFilm);

    void delete(int id);

}
