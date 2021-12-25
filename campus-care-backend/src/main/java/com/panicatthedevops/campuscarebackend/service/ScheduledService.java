package com.panicatthedevops.campuscarebackend.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.panicatthedevops.campuscarebackend.service.NotificationService;
import org.springframework.stereotype.Service;

@Service
@Configuration
@EnableScheduling
public class ScheduledService {
    private final NotificationService notificationServices;
    private final SeatingPlanService seatingPlanService;
    private final CovidService covidService;
    private final InstructorService instructorService;

    @Autowired
    public ScheduledService(NotificationService notificationServices, SeatingPlanService seatingPlanService, CovidService covidService, InstructorService instructorService){
        this.notificationServices = notificationServices;
        this.seatingPlanService = seatingPlanService;
        this.covidService = covidService;
        this.instructorService = instructorService;
    }

    public void notifyInstructors(){

    }
}
