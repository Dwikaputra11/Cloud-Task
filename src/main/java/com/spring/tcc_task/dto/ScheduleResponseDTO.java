package com.spring.tcc_task.dto;

import com.spring.tcc_task.models.Film;
import com.spring.tcc_task.models.Seat;
import com.spring.tcc_task.models.Studio;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ScheduleResponseDTO {
    private int scheduleId;
    private long fromDate;
    private long toDate;
    private int price;
    private Studio studio;
    private Film film;
    private List<Seat> availableSeats;
}
