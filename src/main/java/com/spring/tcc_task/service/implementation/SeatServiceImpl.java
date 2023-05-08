package com.spring.tcc_task.service.implementation;

import com.spring.tcc_task.models.Seat;
import com.spring.tcc_task.repos.SeatRepository;
import com.spring.tcc_task.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SeatServiceImpl implements SeatService {
    private final SeatRepository seatRepository;
    @Autowired
    public SeatServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }
    @Override
    public Page<Seat> findAll(Pageable pageable) {
        return seatRepository.findAll(pageable);
    }
    @Override
    public Seat findById(int id) {
        var seat = seatRepository.findById(id);
        if(seat.isEmpty()) throw new RuntimeException("Seat id: " + id + " tidak ditemukan.");

        return seat.get();
    }
    @Override
    public Seat save(Seat seat) {
        if (seat.getRow() != ' ' || seat.getNumber() > 0
        ) throw new RuntimeException("Seat tidak valid ");

        seat.setSeatId(0);

        return seatRepository.save(seat);
    }
    @Override
    public Seat update(Seat updatedSeat) {
        Optional<Seat> result = seatRepository.findById(updatedSeat.getSeatId());
        Seat seat;

        if (result.isPresent()) {
            seat = result.get();
            seat.setRow(updatedSeat.getRow());
            seat.setNumber(updatedSeat.getNumber());
            return seatRepository.save(seat);
        } else {
            throw new RuntimeException("Data Seat tidak ditemukan");
        }
    }
    @Override
    public void delete(int id) {
        Optional<Seat> result = seatRepository.findById(id);
        if (result.isEmpty()) throw new RuntimeException("Data seat tidak ditemukan");
        seatRepository.delete(result.get());
    }

}
