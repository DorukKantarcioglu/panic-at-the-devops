package com.panicatthedevops.campuscarebackend.util;

import com.panicatthedevops.campuscarebackend.entity.Reservation;
import com.panicatthedevops.campuscarebackend.entity.User;

/**
 * Reservation behavior interface for reservation strategy
 * @version 1.0
 */
public interface ReservationBehavior {
    /**
     * @param date date of reservation
     * @param timeSlot time of reservation
     * @param place place of reservation
     * @param user user that is reserving
     * @return reservation object if it was successfully created
     */
    Reservation reserve(String date, String timeSlot, String place, User user);
}
