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

/**
 * Strategy design pattern for sports center reservation behavior
 * @version 1.0
 */
public class DiagnovirReservationBehavior implements ReservationBehavior{

    ReservationRepository reservationRepository;
    ReservationService reservationService;


    /**
     * Creates an instance
     * @param reservationRepository reservation repository
     * @param reservationService reservation service
     */
    public DiagnovirReservationBehavior(ReservationRepository reservationRepository, ReservationService reservationService) {
        this.reservationRepository = reservationRepository;
        this.reservationService = reservationService;
    }

    /**
     * @param date     date of reservation
     * @param timeSlot time of reservation
     * @param place    place of reservation
     * @param user     user that is reserving
     * @return reservation object that is created if all input is valid
     */
    @Override

    public Reservation reserve(String date, String timeSlot, String place, User user) {

        if(!ReservationInformation.DIAGNOVIR_PLACES.contains(place))
            throw new PlaceNotFoundForReservationType("Place " + place + " not found for reservation type " + ReservationInformation.DIAGNOVIR_RESERVATION);
        else if(!reservationService.getAvailableTimes(date, place, ReservationInformation.DIAGNOVIR_RESERVATION).contains(timeSlot)){
            throw new InvalidReservationTimeSlotException("Timeslot " + timeSlot + " is either already reserved for the place, date and time or is in an invalid format.");
        }
        else if(reservationRepository.existsByPlaceAndTimeSlotAndDateAndType(place, timeSlot, date, ReservationInformation.DIAGNOVIR_RESERVATION)){
            throw new ReservationAlreadyExistsException("Reservation with place " + place + " time slot " +
                    timeSlot + " date " + date + " type " + ReservationInformation.DIAGNOVIR_RESERVATION + " already exists");
        }
        else{
            try{
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ROOT);
                LocalDate localDateTime = LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e){
                throw new InvalidDateException("Date " + date + " is invalid, it should be Day.Month.Year");
            }
        }
        return reservationRepository.save(new Reservation(0, date, timeSlot, place, ReservationInformation.DIAGNOVIR_RESERVATION, user));
    }
}
