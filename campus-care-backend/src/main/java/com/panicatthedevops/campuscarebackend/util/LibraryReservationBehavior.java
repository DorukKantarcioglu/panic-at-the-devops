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
import java.util.Locale;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LibraryReservationBehavior implements ReservationBehavior{
    ReservationRepository reservationRepository;
    ReservationService reservationService;

    public LibraryReservationBehavior(ReservationRepository reservationRepository, ReservationService reservationService) {
        this.reservationRepository = reservationRepository;
        this.reservationService = reservationService;
    }

    @Override
    public Reservation reserve(String date, String timeSlot, String place, User user) {

        if(!ReservationInformation.LIBRARY_PLACES.contains(place))
            throw new PlaceNotFoundForReservationType("Place " + place + " not found for reservation type " + ReservationInformation.LIBRARY_RESERVATION);
        else if(!reservationService.getAvailableTimes(date, place, ReservationInformation.LIBRARY_RESERVATION).contains(timeSlot)){
            throw new InvalidReservationTimeSlotException("Timeslot " + timeSlot + " is either already reserved for the place, date and time or is in an invalid format.");
        }
        else if(reservationRepository.existsByPlaceAndTimeSlotAndDateAndType(place, timeSlot, date, ReservationInformation.LIBRARY_RESERVATION)){
            throw new ReservationAlreadyExistsException("Reservation with place " + place + " time slot " +
                    timeSlot + " date " + date + " type " + ReservationInformation.LIBRARY_RESERVATION + " already exists");
        }
        else{
            try{
                DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ROOT);
                LocalDate localDateTime = LocalDate.parse(date, FORMATTER);
            } catch (DateTimeParseException e){
                throw new InvalidDateException("Date " + date + " is invalid, it should be Day.Month.Year");
            }
        }
        return reservationRepository.save(new Reservation(0, date, timeSlot, place, ReservationInformation.LIBRARY_RESERVATION, user));
    }
}
