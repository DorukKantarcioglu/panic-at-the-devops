package com.panicatthedevops.campuscarebackend.service;

import com.panicatthedevops.campuscarebackend.entity.Reservation;
import com.panicatthedevops.campuscarebackend.entity.User;
import com.panicatthedevops.campuscarebackend.exception.PlaceNotFoundForReservationType;
import com.panicatthedevops.campuscarebackend.exception.ReservationNotFoundException;
import com.panicatthedevops.campuscarebackend.exception.UndefinedReservationTypeException;
import com.panicatthedevops.campuscarebackend.exception.UserNotFoundException;
import com.panicatthedevops.campuscarebackend.repository.InstructorRepository;
import com.panicatthedevops.campuscarebackend.repository.ReservationRepository;
import com.panicatthedevops.campuscarebackend.repository.StaffRepository;
import com.panicatthedevops.campuscarebackend.repository.StudentRepository;
import com.panicatthedevops.campuscarebackend.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final StudentRepository studentRepository;
    private final StaffRepository staffRepository;
    private final InstructorRepository instructorRepository;
    private ReservationBehavior reservationBehavior;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, StudentRepository studentRepository, StaffRepository staffRepository, InstructorRepository instructorRepository) {
        this.reservationRepository = reservationRepository;
        this.studentRepository = studentRepository;
        this.staffRepository = staffRepository;
        this.instructorRepository = instructorRepository;
    }

    public Reservation getReservation(Long id){
        if(!reservationRepository.existsById(id)){
            throw new ReservationNotFoundException("Reservation with id " + id + " does not exist");
        }
        else{
            return reservationRepository.findById(id).get();
        }
    }

    public List<Reservation> getReservationsOfUser(Long userId){
        if(!reservationRepository.existsByUserId(userId)){
            if(!studentRepository.existsById(userId) && !instructorRepository.existsById(userId) && !staffRepository.existsById(userId))
                throw new UserNotFoundException("User with id " + userId + " does not exist.");
            List<Reservation> reservations = new ArrayList<>();
            return reservations;
        }
        return reservationRepository.findAllByUserId(userId);
    }

    public List<String> getAvailableTimes(String date, String place, String reservationType){
        if(reservationType.equals(ReservationInformation.DIAGNOVIR_RESERVATION)){
            if(!ReservationInformation.DIAGNOVIR_PLACES.contains(place))
                throw new PlaceNotFoundForReservationType("Place " + place + " not found for reservation type " + reservationType);
            return ReservationInformation.DIAGNOVIR_TIME_SLOTS.stream().filter(t -> !reservationRepository.existsByPlaceAndTimeSlotAndDateAndType(place, t, date, reservationType)).collect(Collectors.toList());
        }
        else if(reservationType.equals(ReservationInformation.LIBRARY_RESERVATION)){
            if(!ReservationInformation.LIBRARY_PLACES.contains(place))
                throw new PlaceNotFoundForReservationType("Place " + place + " not found for reservation type " + reservationType);
            List<String> times = ReservationInformation.LIBRARY_TIME_SLOTS.stream().filter(t -> !reservationRepository.existsByPlaceAndTimeSlotAndDateAndType(place, t, date, reservationType)).collect(Collectors.toList());
            List<Reservation> reservations = reservationRepository.findAllByPlaceAndDateAndType(place, date, reservationType);

            for(Reservation reservation : reservations){
                times = times.stream().filter(t -> !(Math.abs(ChronoUnit.MINUTES.between(LocalTime.of(Integer.parseInt(reservation.getTimeSlot().split(":")[0]), Integer.parseInt(reservation.getTimeSlot().split(":")[1] )),
                        LocalTime.of(Integer.parseInt(t.split(":")[0]), Integer.parseInt(t.split(":")[1] )))) < 180  && ChronoUnit.MINUTES.between(LocalTime.of(Integer.parseInt(reservation.getTimeSlot().split(":")[0]), Integer.parseInt(reservation.getTimeSlot().split(":")[1] )),
                        LocalTime.of(Integer.parseInt(t.split(":")[0]), Integer.parseInt(t.split(":")[1] ))) > 0 )).collect(Collectors.toList());
            }
            return times;
        }
        else if(reservationType.equals(ReservationInformation.SPORTS_CENTER_RESERVATION)){
            if(!ReservationInformation.SPORTS_CENTER_PLACES.contains(place))
                throw new PlaceNotFoundForReservationType("Place " + place + " not found for reservation type " + reservationType);
            return ReservationInformation.SPORTS_CENTER_TIME_SLOTS.stream().filter(t -> !reservationRepository.existsByPlaceAndTimeSlotAndDateAndType(place, t, date, reservationType)).collect(Collectors.toList());
        }
        else
            throw new UndefinedReservationTypeException("Reservation type " + reservationType + " is invalid. It can be: " +
                    ReservationInformation.SPORTS_CENTER_RESERVATION + ", " + ReservationInformation.DIAGNOVIR_RESERVATION + ", " + ReservationInformation.LIBRARY_RESERVATION);
    }

    public List<String> getPlaces(String reservationType){
        if(reservationType.equals(ReservationInformation.DIAGNOVIR_RESERVATION)){
            return ReservationInformation.DIAGNOVIR_PLACES;
        }
        else if(reservationType.equals(ReservationInformation.LIBRARY_RESERVATION)){
            return ReservationInformation.LIBRARY_PLACES;
        }
        else if(reservationType.equals(ReservationInformation.SPORTS_CENTER_RESERVATION)){
            return ReservationInformation.SPORTS_CENTER_PLACES;
        }
        else{
            throw new UndefinedReservationTypeException("Reservation type " + reservationType + " is invalid. It can be: " +
                    ReservationInformation.SPORTS_CENTER_RESERVATION + ", " + ReservationInformation.DIAGNOVIR_RESERVATION + ", " + ReservationInformation.LIBRARY_RESERVATION);
        }
    }

    public List<String> getAllTimeSlots(String reservationType){
        if(reservationType.equals(ReservationInformation.DIAGNOVIR_RESERVATION)){
            return ReservationInformation.DIAGNOVIR_TIME_SLOTS;
        }
        else if(reservationType.equals(ReservationInformation.LIBRARY_RESERVATION)){
            return ReservationInformation.LIBRARY_TIME_SLOTS;
        }
        else if(reservationType.equals(ReservationInformation.SPORTS_CENTER_RESERVATION)){
            return ReservationInformation.SPORTS_CENTER_TIME_SLOTS;
        }
        else
            throw new UndefinedReservationTypeException("Reservation type " + reservationType + " is invalid. It can be: " +
                    ReservationInformation.SPORTS_CENTER_RESERVATION + ", " + ReservationInformation.DIAGNOVIR_RESERVATION + ", " + ReservationInformation.LIBRARY_RESERVATION);
    }

    public List<Reservation> getReservations(){
        return reservationRepository.findAll();
    }

    public void deleteReservation(Long id){
        if(!reservationRepository.existsById(id)){
            throw new ReservationNotFoundException("Reservation with id " + id + " does not exist");
        }
        else{
            Reservation reservation = reservationRepository.findById(id).get();
            User user = reservation.getUser();
            user.getReservations().remove(reservation);
            if(studentRepository.existsById(user.getId()))
                studentRepository.save(studentRepository.getById(user.getId()));
            else if(staffRepository.existsById(user.getId()))
                staffRepository.save(staffRepository.getById(user.getId()));
            else
                instructorRepository.save(instructorRepository.getById(user.getId()));
            reservationRepository.deleteById(id);
        }
    }

    public void setReservationBehavior(String reservationType){
        if(reservationType.equals(ReservationInformation.DIAGNOVIR_RESERVATION))
            reservationBehavior = new DiagnovirReservationBehavior(reservationRepository, this);
        else if(reservationType.equals(ReservationInformation.LIBRARY_RESERVATION))
            reservationBehavior = new LibraryReservationBehavior(reservationRepository, this);
        else if(reservationType.equals(ReservationInformation.SPORTS_CENTER_RESERVATION))
            reservationBehavior = new SportCenterReservationBehavior(reservationRepository, this);
        else
            throw new UndefinedReservationTypeException("Reservation type " + reservationType + " is invalid. It can be: " +
                    ReservationInformation.SPORTS_CENTER_RESERVATION + ", " + ReservationInformation.DIAGNOVIR_RESERVATION + ", " + ReservationInformation.LIBRARY_RESERVATION);
    }

    public Reservation save(Long userId, String date, String timeSlot, String place, String type){
        setReservationBehavior(type);
        boolean studentExists = studentRepository.existsById(userId);
        boolean staffExists = staffRepository.existsById(userId);
        boolean instructorExists = instructorRepository.existsById(userId);

        if(!studentExists && !staffExists && !instructorExists){
            throw new UserNotFoundException("User with id " + userId + " does not exist");
        }
        else {
            User user;
            if(studentExists)
                user = studentRepository.findById(userId).get();
            else if(instructorExists)
                user = instructorRepository.findById(userId).get();
            else
                user = staffRepository.findById(userId).get();
            return reservationBehavior.reserve(date, timeSlot, place, user);
        }
    }
}
