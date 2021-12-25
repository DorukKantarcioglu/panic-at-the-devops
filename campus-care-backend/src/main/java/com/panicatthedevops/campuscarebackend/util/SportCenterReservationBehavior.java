package com.panicatthedevops.campuscarebackend.util;

import com.panicatthedevops.campuscarebackend.entity.Reservation;
import com.panicatthedevops.campuscarebackend.entity.User;
import com.panicatthedevops.campuscarebackend.exception.InvalidDateException;
import com.panicatthedevops.campuscarebackend.exception.InvalidReservationTimeSlotException;
import com.panicatthedevops.campuscarebackend.exception.PlaceNotFoundForReservationType;
import com.panicatthedevops.campuscarebackend.exception.ReservationAlreadyExistsException;
import com.panicatthedevops.campuscarebackend.repository.ReservationRepository;
import com.panicatthedevops.campuscarebackend.service.ReservationService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class SportCenterReservationBehavior implements ReservationBehavior{
    ReservationRepository reservationRepository;
    ReservationService reservationService;

    public SportCenterReservationBehavior(ReservationRepository reservationRepository, ReservationService reservationService) {
        this.reservationRepository = reservationRepository;
        this.reservationService = reservationService;
    }

    @Override
    public Reservation reserve(String date, String timeSlot, String place, User user) {

        if(!ReservationInformation.SPORTS_CENTER_PLACES.contains(place))
            throw new PlaceNotFoundForReservationType("Place " + place + " not found for reservation type " + ReservationInformation.SPORTS_CENTER_RESERVATION);
        else if(!reservationService.getAvailableTimes(date, place, ReservationInformation.SPORTS_CENTER_RESERVATION).contains(timeSlot)){
            throw new InvalidReservationTimeSlotException("Timeslot " + timeSlot + " is either already reserved for the place, date and time or is in an invalid format.");
        }
        else if(reservationRepository.existsByPlaceAndTimeSlotAndDateAndType(place, timeSlot, date, ReservationInformation.SPORTS_CENTER_RESERVATION)){
            throw new ReservationAlreadyExistsException("Reservation with place " + place + " time slot " +
                    timeSlot + " date " + date + " type " + ReservationInformation.SPORTS_CENTER_RESERVATION + " already exists");
        }
        else{
            try{
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ROOT);
                LocalDate localDateTime = LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e){
                throw new InvalidDateException("Date " + date + " is invalid, it should be Day.Month.Year");
            }
        }
        return reservationRepository.save(new Reservation(0, date, timeSlot, place, ReservationInformation.SPORTS_CENTER_RESERVATION, user));
    }
}
