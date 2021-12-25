package com.panicatthedevops.campuscarebackend.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Cafeteria extends Area {
    int capacity;

    public Cafeteria(String name, int liveCount, int capacity) {
        super(name, liveCount);
        this.capacity = capacity;
    }

    public Cafeteria(int capacity) {
        this.capacity = capacity;
    }
}
