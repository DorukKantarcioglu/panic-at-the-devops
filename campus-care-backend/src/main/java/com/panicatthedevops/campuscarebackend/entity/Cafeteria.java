package com.panicatthedevops.campuscarebackend.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;


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
