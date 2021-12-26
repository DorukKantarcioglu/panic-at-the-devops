package com.panicatthedevops.campuscarebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

/**
 * Represents the possible place a student can be located in.
 * Extended by Cafeteria and SmokingArea classes
 * @version 1.0
 */
@Inheritance
@Entity
@Table(name = "area")
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Area {

    @Id
    String name;
    int liveCount;

    /**
     * Increases the count by one.
     */
    public void incrementLiveCount() {
        liveCount++;
    }

    /**
     * Decreases the count by one.
     */
    public void decrementLiveCount() {
        liveCount--;
    }
}
