package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.model.BusConnection;
import com.example.backend.service.BusConnectionService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/bus-connections")
public class BusConnectionController {
    
    private static final Logger logger = LogManager.getLogger(BusConnectionController.class);

    @Autowired
    private BusConnectionService busConnectionService;
    
    @GetMapping
    public ResponseEntity<List<BusConnection>> getAllBusConnections() {
        logger.info("Fetching all bus connections.");
        List<BusConnection> busConnections = busConnectionService.getAllBusConnections();
        return new ResponseEntity<>(busConnections, HttpStatus.OK);
    }

    @GetMapping("/{origin}/{destination}/{departureDate}")
    public ResponseEntity<?> getBusConnectionByOriginAndDestinationAndDepartureDate(@PathVariable String origin, @PathVariable String destination, @PathVariable String departureDate) {
        logger.info("Fetching bus connections by origin {}, destination {}, and departure date {}.", origin, destination, departureDate);
        List<BusConnection> busConnections = busConnectionService.getBusConnectionsByOriginAndDestinationAndDepartureDate(origin, destination, departureDate);
        logger.info(busConnections.size() + " bus connections found.");
        logger.info("Bus connections: " + busConnections);
        return new ResponseEntity<>(busConnections, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusConnection> getBusConnectionById(@PathVariable Long id) {
        logger.info("Fetching bus connection by id {}.", id);
        BusConnection busConnection = busConnectionService.getBusConnectionById(id);
        return new ResponseEntity<>(busConnection, HttpStatus.OK);
    }
    

}
