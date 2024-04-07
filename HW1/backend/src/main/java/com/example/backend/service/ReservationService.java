package com.example.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.model.Reservation;
import com.example.backend.repository.ReservationRepository;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class ReservationService {

    private static final Logger logger = LogManager.getLogger(ReservationService.class);
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    public Optional<Reservation> getReservationById(Long resId) {
        return reservationRepository.findById(resId);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation saveReservation(Reservation reservation) {
        Reservation savedReservation = reservationRepository.save(reservation);
        Long reservationId = savedReservation.getResId();
        logger.info("Reservation with id " + reservationId + " saved");
        return savedReservation;
    }


    //extra points (talvez usar, depois de pesquisar por id, fazer delete por id)
    public void deleteReservation(Long resId) {
        Optional<Reservation> reservation = reservationRepository.findById(resId);
        if (reservation.isPresent()) {
            reservationRepository.delete(reservation.get());        //perceber se devo usar .get() ou usar mais tarde um delete por id  
        }
    }
}

