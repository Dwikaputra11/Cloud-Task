package com.spring.tcc_task.service;

import com.spring.tcc_task.dto.ScheduleRequestDTO;
import com.spring.tcc_task.dto.ScheduleResponseDTO;
import com.spring.tcc_task.models.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ScheduleService {
    Page<Schedule> findAll(Pageable pageable);

    List<ScheduleResponseDTO> findAll();

    ScheduleResponseDTO findById(int id);

    ScheduleResponseDTO save(ScheduleRequestDTO schedule);

    ScheduleResponseDTO update(ScheduleRequestDTO updatedSchedule);

    void delete(int id);
}
