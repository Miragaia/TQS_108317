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


@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private static final Logger logger = LogManager.getLogger(ReservationController.class);
    
    @Autowired
    private ReservationService reservationService;
    
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        Optional<Reservation> reservation = reservationService.getReservationById(id);
        //appears error because of version, but works
        return reservation.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        logger.info("Saving reservation");
        Reservation savedReservation = reservationService.saveReservation(reservation);
        return new ResponseEntity<>(savedReservation, HttpStatus.CREATED);
    }

    @GetMapping("/bus-connections/{busConId}/reservations")
    public ResponseEntity<List<Reservation>> getReservationsByBusConId(@PathVariable Long busConId) {
        List<Reservation> reservations = reservationService.getReservationsByBusConId(busConId);
        logger.info(reservations.size() + " reservations found for bus connection with id " + busConId);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    
}

