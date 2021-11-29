package com.panicatthedevops.pandemicmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id
    private Long id;
    private String name;
    private String email;
    private String hesCode;
    private boolean isAllowedOnCampus;
    private boolean isVaccinated;
    private boolean isTested;
}
