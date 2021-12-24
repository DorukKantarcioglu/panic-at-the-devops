package com.panicatthedevops.campuscarebackend.service;

import com.panicatthedevops.campuscarebackend.entity.Reservation;
import com.panicatthedevops.campuscarebackend.entity.User;
import com.panicatthedevops.campuscarebackend.exception.ReservationNotFoundException;
import com.panicatthedevops.campuscarebackend.exception.UserNotFoundException;
import com.panicatthedevops.campuscarebackend.repository.InstructorRepository;
import com.panicatthedevops.campuscarebackend.repository.ReservationRepository;
import com.panicatthedevops.campuscarebackend.repository.StaffRepository;
import com.panicatthedevops.campuscarebackend.repository.StudentRepository;
import com.panicatthedevops.campuscarebackend.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
            throw new UserNotFoundException("User with id " + userId + " does not exist");
        }
        return reservationRepository.findAllByUserId(userId);
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
        if(reservationType.equals(ReservationType.DIAGNOVIR_RESERVATION))
            reservationBehavior = new DiagnovirReservationBehavior(reservationRepository);
        else if(reservationType.equals(ReservationType.LIBRARY_RESERVATION))
            reservationBehavior = new LibraryReservationBehavior(reservationRepository);
        else if(reservationType.equals(ReservationType.SPORTS_CENTER_RESERVATION))
            reservationBehavior = new SportCenterReservationBehavior(reservationRepository);
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
