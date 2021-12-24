package com.panicatthedevops.campuscarebackend.util;

import com.panicatthedevops.campuscarebackend.entity.Reservation;
import com.panicatthedevops.campuscarebackend.entity.User;
import com.panicatthedevops.campuscarebackend.repository.ReservationRepository;

public class SportCenterReservationBehavior implements ReservationBehavior{
    ReservationRepository reservationRepository;

    public SportCenterReservationBehavior(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation reserve(String date, String timeSlot, String place, User user) {
        return reservationRepository.save(new Reservation(0, date, timeSlot, place, ReservationType.SPORTS_CENTER_RESERVATION, user));
    }
}
