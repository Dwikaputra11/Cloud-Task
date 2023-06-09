package com.spring.tcc_task.service.implementation;

import com.spring.tcc_task.dto.ScheduleRequestDTO;
import com.spring.tcc_task.dto.ScheduleResponseDTO;
import com.spring.tcc_task.models.Schedule;
import com.spring.tcc_task.repos.*;
import com.spring.tcc_task.service.ScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ModelMapper modelMapper;
    private final ScheduleRepository scheduleRepository;
    private final StudioRepository studioRepository;
    private final FilmRepository filmRepository;
    private final SeatRepository seatRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, StudioRepository studioRepository, FilmRepository filmRepository, SeatRepository seatRepository) {
        this.scheduleRepository = scheduleRepository;
        this.studioRepository = studioRepository;
        this.filmRepository = filmRepository;
        this.seatRepository = seatRepository;
    }

    @Override
    public Page<Schedule> findAll(Pageable pageable) {
        return scheduleRepository.findAll(pageable);
    }

    @Override
    public List<ScheduleResponseDTO> findAll() {
        var schedules = scheduleRepository.findAll();
        if(schedules.isEmpty()) return new ArrayList<>();

        return schedules.stream().map(schedule -> {
            var availableSeats = seatRepository.findAvailableSeats(schedule.getScheduleId(), schedule.getStudio().getStudioId());
            System.out.println("Avalable seats" + availableSeats);
            return schedule.convertToResponse(availableSeats);
        }).toList();
    }

    @Override
    public ScheduleResponseDTO findById(int id) {
        var schedule = scheduleRepository.findById(id);

        if(schedule.isEmpty()) throw new RuntimeException("No Schedule found");

        var availableSeats = seatRepository.findAvailableSeats(schedule.get().getScheduleId(), schedule.get().getStudio().getStudioId());

        return schedule.get().convertToResponse(availableSeats);
    }

    @Override
    public ScheduleResponseDTO save(ScheduleRequestDTO request) {

        if(request.getScheduleId() > 0 && (scheduleRepository.findById(request.getScheduleId()).isPresent()))
            throw new RuntimeException("Schedule already exists");

        request.setScheduleId(0);

        if(request.getFromDate() > request.getToDate())
            throw new RuntimeException("Date range not valid");

        var studio = studioRepository.findById(request.getStudioId());
        var film = filmRepository.findById(request.getFilmId());

        if(studio.isEmpty() || film.isEmpty())
            throw new RuntimeException("No studio or film found");

        var schedule = modelMapper.map(request, Schedule.class);
        schedule.setFilm(film.get());
        schedule.setStudio(studio.get());

        var result = scheduleRepository.save(schedule);

        var seats = seatRepository.findByStudioStudioId(result.getStudio().getStudioId());

        return result.convertToResponse(seats);
    }

    /*
    * permasalahan ketika schedule di update studio maka payment yang sudah terjadi perlu overwrite id seat yang ada agar sesuai
    * id seat yang sesuai pada studio.
    * Jadi update hanya dibatasi pada perubahan film, price, dan tanggal
    * */
    @Override
    public ScheduleResponseDTO update(ScheduleRequestDTO updatedSchedule) {
        var request = scheduleRepository.findById(updatedSchedule.getScheduleId());

        if(request.isEmpty())
            throw new RuntimeException("No Schedule found");
        
        var schedule = request.get();

        var film = filmRepository.findById(updatedSchedule.getFilmId());
        if(film.isEmpty())
            throw new RuntimeException("No film found");

        if(updatedSchedule.getFromDate() > updatedSchedule.getToDate())
            throw new RuntimeException("Date range not valid");

        schedule.setPrice(updatedSchedule.getPrice());
        schedule.setFromDate(updatedSchedule.getFromDate());
        schedule.setToDate(updatedSchedule.getToDate());
        schedule.setFilm(film.get());

        var result = scheduleRepository.save(schedule);
        var seats = seatRepository.findByStudioStudioId(result.getStudio().getStudioId());

        return result.convertToResponse(seats);
    }

    @Override
    public void delete(int id) {
        var result = scheduleRepository.findById(id);

        if(result.isEmpty()) throw new RuntimeException("No Schedule found");

        scheduleRepository.delete(result.get());
    }
}
