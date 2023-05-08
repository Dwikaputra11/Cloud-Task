package com.spring.tcc_task.controller.views;

import com.spring.tcc_task.dto.ScheduleResponseDTO;
import com.spring.tcc_task.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "schedule")
public class ScheduleMvcController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleMvcController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/list")
    public String findAll(Model model){
        List<ScheduleResponseDTO> schedules;
        schedules = scheduleService.findAll();

        model.addAttribute("schedules", schedules);

        return "schedule/schedule-list";
    }

    @GetMapping("/details/{scheduleId}")
    public String showDetail(@PathVariable("scheduleId") Integer scheduleId, Model model){

        var schedule = scheduleService.findById(scheduleId);

        model.addAttribute("schedule", schedule);

        return "schedule/schedule-details";
    }

    @GetMapping("/update/{scheduleId}")
    public String updateSchedule(@PathVariable("scheduleId") Integer scheduleId, Model model){

        var schedule = scheduleService.findById(scheduleId);

        model.addAttribute("schedule", schedule);

        return "schedule/schedule-form";
    }




}
