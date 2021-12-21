package com.panicatthedevops.campuscarebackend.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeSlot {
    private String time;
    private String day;
}
