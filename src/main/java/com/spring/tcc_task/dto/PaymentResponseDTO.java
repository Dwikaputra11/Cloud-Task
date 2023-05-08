package com.spring.tcc_task.dto;

import com.spring.tcc_task.models.Costumer;
import com.spring.tcc_task.models.Schedule;
import com.spring.tcc_task.models.Seat;
import com.spring.tcc_task.models.Staff;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class PaymentResponseDTO {
    private int paymentId;
    private Date paymentDate;
    private int amount;
    private Schedule schedule;
    private Costumer costumer;
    private Staff staff;
    private List<Seat> seatsReserved;
}


