package com.panicatthedevops.campuscarebackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * Represents the smoking areas in the campus.
 * Extends the Area abstract class.
 * @version 1.0
 */

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SmokingArea extends Area{

    /**
     * @param name Name of the cafeteria
     * @param liveCount Number of people currently in the cafeteria
     */
    public SmokingArea(String name, int liveCount) {
        super(name, liveCount);
    }
}
