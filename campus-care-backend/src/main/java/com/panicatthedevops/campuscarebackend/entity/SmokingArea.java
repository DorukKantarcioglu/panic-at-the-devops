package com.panicatthedevops.campuscarebackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SmokingArea extends Area{

    public SmokingArea(String name, int liveCount) {
        super(name, liveCount);
    }


}
