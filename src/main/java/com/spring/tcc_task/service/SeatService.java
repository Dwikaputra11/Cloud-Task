package com.spring.tcc_task.service;

import com.spring.tcc_task.models.Seat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SeatService {
    Page<Seat> findAll(Pageable pageable);

    Seat findById(int id);

    Seat save(Seat seat);

    Seat update(Seat updatedSeat);

    void delete(int id);
}
