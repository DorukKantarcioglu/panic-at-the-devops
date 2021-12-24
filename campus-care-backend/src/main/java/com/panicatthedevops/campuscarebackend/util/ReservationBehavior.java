package com.panicatthedevops.campuscarebackend.util;

import com.panicatthedevops.campuscarebackend.entity.Reservation;
import com.panicatthedevops.campuscarebackend.entity.User;

public interface ReservationBehavior {
    Reservation reserve(String date, String timeSlot, String place, User user);
}
