package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.model.Reservation;
import com.example.backend.service.ReservationService;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private static final Logger logger = LogManager.getLogger(BusConnectionController.class);
    
    @Autowired
    private ReservationService reservationService;
    
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        Optional<Reservation> reservation = reservationService.getReservationById(id);
        //appears error because of version, but works
        return reservation.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<Reservation> createReservationById(@PathVariable Long id, @RequestBody Reservation reservation) {
        // Check if the provided id matches the id in the reservation object
        logger.info("id in path: " + id);
        // Check if a reservation with the provided ID exists
        Optional<Reservation> existingReservation = reservationService.getReservationById(id);
        if (existingReservation.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        // Save the reservation
        logger.info("Saving reservation");
        Reservation savedReservation = reservationService.saveReservation(reservation);
        return new ResponseEntity<>(savedReservation, HttpStatus.CREATED);
    }
}

