package com.spring.tcc_task.keys;

import com.spring.tcc_task.models.Costumer;
import com.spring.tcc_task.models.Schedule;
import com.spring.tcc_task.models.Staff;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import java.io.Serializable;

@Embeddable
public class PaymentFKeyId implements Serializable {
    @OneToOne
    @JoinColumn(name = "schedule_id", referencedColumnName = "schedule_id")
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "costumer_id", referencedColumnName = "costumer_id")
    private Costumer costumer;

    @ManyToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "staff_id")
    private Staff staff;
}
