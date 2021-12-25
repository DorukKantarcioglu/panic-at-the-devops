package com.panicatthedevops.campuscarebackend.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


/**
 * Represents the cafeterias in the campus.
 * Extends the Area abstract class.
 * @version 1.0
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Cafeteria extends Area {
    int capacity;

    /**
     * @param name Name of the cafeteria
     * @param liveCount Number of people currently in the cafeteria
     * @param capacity Maximum number of people the cafeteria can accommodate
     */
    public Cafeteria(String name, int liveCount, int capacity) {
        super(name, liveCount);
        this.capacity = capacity;
    }

    public Cafeteria(int capacity) {
        this.capacity = capacity;
    }
}
