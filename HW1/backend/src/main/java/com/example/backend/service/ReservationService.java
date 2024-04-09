package com.example.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.model.Reservation;
import com.example.backend.repository.ReservationRepository;

import java.util.Collections;
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
        if (reservationRepository.findById(resId).isEmpty()) {
            return null;
        }
        return reservationRepository.findById(resId);
    }

    public List<Reservation> getAllReservations() {
        if (reservationRepository.findAll().isEmpty()) {
            return null;
        }
        return reservationRepository.findAll();
    }

    public Reservation saveReservation(Reservation reservation) {
        if (reservation == null) {
            return null;
        }
        Reservation savedReservation = reservationRepository.save(reservation);
        Long reservationId = savedReservation.getResId();
        logger.info("Reservation with id " + reservationId + " saved");
        return savedReservation;
    }

    public List<Reservation> getReservationsByBusConId(Long busConId) {
        if (reservationRepository.findByBusConId(busConId).isEmpty()) {
            return Collections.emptyList();
        }
        return reservationRepository.findByBusConId(busConId);
    }   

    public void deleteReservation(Long resId) {
        Optional<Reservation> reservation = reservationRepository.findById(resId);
        if (reservation.isPresent()) {
            reservationRepository.delete(reservation.get());
        }
    }

    public List<Reservation> saveAllReservations(List<Reservation> reservations) {
        if (reservations.isEmpty()) {
            return null;
        }
        return reservationRepository.saveAll(reservations);
    }
        
    public Reservation updateReservation(Long id, Reservation reservation) {
        Optional<Reservation> optionalExistingReservation = reservationRepository.findById(id);
        
        if (optionalExistingReservation.isPresent()) {
            Reservation existingReservation = optionalExistingReservation.get();
        
            existingReservation.setBusConId(reservation.getBusConId());
            existingReservation.setPassengerName(reservation.getPassengerName());
            existingReservation.setAddress(reservation.getAddress());
            existingReservation.setCity(reservation.getCity());
            existingReservation.setCountry(reservation.getCountry());
            existingReservation.setZipCode(reservation.getZipCode());
            existingReservation.setCreditCardNumber(reservation.getCreditCardNumber());
            existingReservation.setCardHolderName(reservation.getCardHolderName());
            existingReservation.setCardExpirationMonth(reservation.getCardExpirationMonth());
            existingReservation.setCardExpirationYear(reservation.getCardExpirationYear());
            
            return reservationRepository.save(existingReservation);
        } else {
            return null;
        }
    }
    
}

