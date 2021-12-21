package com.panicatthedevops.campuscarebackend.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import java.util.List;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class Staff extends User{
    public Staff(Long id, String name, String password, String email, String hesCode, String phoneNumber, boolean allowedOnCampus, boolean vaccinated, boolean tested, List<Notification> notificationList, Set<Reservation> reservations) {
        super(id, name, password, email, hesCode, phoneNumber, allowedOnCampus, vaccinated, tested, notificationList, reservations);
    }

    public Staff() {
    }
}
