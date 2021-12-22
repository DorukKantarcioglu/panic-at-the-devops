package com.panicatthedevops.campuscarebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

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

    public void incrementLiveCount() {
        liveCount++;
    }

    public void decrementLiveCount() {
        liveCount--;
    }
}
