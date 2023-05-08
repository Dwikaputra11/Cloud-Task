package com.spring.tcc_task.dto;

import com.spring.tcc_task.models.Seat;
import lombok.Data;

import java.util.List;

@Data
public class StudioSeatDTO {
    private int studioId;
    private int capacity;
    private String name;
    private List<Seat> seatsAvailable;
}
