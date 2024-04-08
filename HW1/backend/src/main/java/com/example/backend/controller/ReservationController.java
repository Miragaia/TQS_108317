package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.model.Reservation;
import com.example.backend.service.ReservationService;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private static final Logger logger = LogManager.getLogger(ReservationController.class);
    
    @Autowired
    private ReservationService reservationService;
    
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        if (id == null) {
            logger.info("No id provided.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Optional<Reservation> reservation = reservationService.getReservationById(id);
        if (reservation.isEmpty()) {
            logger.info("Reservation with id " + id + " not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //appears error because of version, but works
        return reservation.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        logger.info("Saving reservation");
        if (reservation == null) {
            logger.info("No reservation to save.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Reservation savedReservation = reservationService.saveReservation(reservation);
        if (savedReservation == null) {
            logger.error("Error saving reservation");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(savedReservation, HttpStatus.CREATED);
    }

    @GetMapping("/bus-connections/{busConId}/reservations")
    public ResponseEntity<List<Reservation>> getReservationsByBusConId(@PathVariable Long busConId) {
        List<Reservation> reservations = reservationService.getReservationsByBusConId(busConId);
        if (reservations.isEmpty()) {
            logger.info("No reservations found for bus connection with id " + busConId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info(reservations.size() + " reservations found for bus connection with id " + busConId);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    //new endpoint
    @GetMapping("/all")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        if (reservations.isEmpty()) {
            logger.info("No reservations found.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info(reservations.size() + " reservations found.");
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteReservation(@PathVariable("id") Long id) {
        if (reservationService.getReservationById(id).isEmpty()) {
            logger.info("Reservation with id " + id + " not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        reservationService.deleteReservation(id);
        logger.info("Reservation with id " + id + " deleted");
        if (reservationService.getReservationById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/create/all")
    public ResponseEntity<List<Reservation>> createAllReservations(@RequestBody List<Reservation> reservations) {
        if (reservations.isEmpty()) {
            logger.info("No reservations to save.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<Reservation> savedReservations = reservationService.saveAllReservations(reservations);
        if (savedReservations == null) {
            logger.error("Error saving reservations");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(savedReservations, HttpStatus.CREATED);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        if (reservationService.getReservationById(id).isEmpty()) {
            logger.info("Reservation with id " + id + " not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Reservation updatedReservation = reservationService.updateReservation(id, reservation);
        if (updatedReservation == null) {
            logger.error("Error updating reservation");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("Reservation updated: " + updatedReservation);
        return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
    }

    
}

