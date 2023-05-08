package com.spring.tcc_task.service.implementation;

import com.spring.tcc_task.models.SeatReserved;
import com.spring.tcc_task.repos.SeatReservedRepository;
import com.spring.tcc_task.service.SeatReservedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatReservedImpl implements SeatReservedService {

    private final SeatReservedRepository seatReservedRepository;

    @Autowired
    public SeatReservedImpl(SeatReservedRepository seatReservedRepository) {
        this.seatReservedRepository = seatReservedRepository;
    }


    @Override
    public List<SeatReserved> findAll() {
        return null;
    }

    @Override
    public SeatReserved findById(int id) {
        return null;
    }

    @Override
    public SeatReserved save(SeatReserved seatReserved) {
        return null;
    }

    @Override
    public SeatReserved update(SeatReserved updatedSeatReserved) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
