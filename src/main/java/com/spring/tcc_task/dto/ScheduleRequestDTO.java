package com.spring.tcc_task.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ScheduleRequestDTO implements Serializable {
    private int scheduleId;
    private long fromDate;
    private long toDate;
    private int price;
    private int studioId;
    private int filmId;
}
