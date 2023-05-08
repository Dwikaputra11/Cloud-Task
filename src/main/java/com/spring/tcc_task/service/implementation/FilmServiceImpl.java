package com.spring.tcc_task.service.implementation;

import com.spring.tcc_task.models.Film;
import com.spring.tcc_task.repos.FilmRepository;
import com.spring.tcc_task.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }
    @Override
    public Page<Film> findAll(Pageable pageable) {
        return filmRepository.findAll(pageable);
    }

    @Override
    public Film findById(int id) {
        var film = filmRepository.findById(id);
        if(film.isEmpty()) throw new RuntimeException("Data film id: " + id + " is not exist.");

        return film.get();
    }

    @Override
    public Film save(Film film) {
        if (film.getTitle() == null || film.getTitle().isEmpty()
                || film.getDescription() == null || film.getDescription().isEmpty()
                || film.getReleaseDate() == null
                || film.getActors() == null
        ) throw new RuntimeException("Data film tidak lengkap");

        film.setFilmId(0);

        return filmRepository.save(film);
    }

    @Override
    public Film update(Film updatedFilm) {
        Optional<Film> result = filmRepository.findById(updatedFilm.getFilmId());
        Film film;

        if (result.isPresent()) {
            film = result.get();
            film.setTitle(updatedFilm.getTitle());
            film.setDescription(updatedFilm.getDescription());
            film.setScoreRating(updatedFilm.getScoreRating());
            film.setReleaseDate(updatedFilm.getReleaseDate());
            film.setActors(updatedFilm.getActors());
            return filmRepository.save(film);
        } else {
            throw new RuntimeException("Data film tidak ditemukan");
        }
    }

    @Override
    public void delete(int id) {
        Optional<Film> result = filmRepository.findById(id);
        if (result.isEmpty()) throw new RuntimeException("Data film tidak ditemukan");
        filmRepository.delete(result.get());
    }
}
